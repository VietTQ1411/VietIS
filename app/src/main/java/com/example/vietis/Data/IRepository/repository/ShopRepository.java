package com.example.vietis.Data.IRepository.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.vietis.Data.IRepository.IStoreRepository;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.R;
import com.example.vietis.Utilities.common.AppResources;
import com.example.vietis.Utilities.common.UserApp;
import com.example.vietis.Utilities.helpers.API;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShopRepository {
    private MutableLiveData<ArrayList<Shop>> mutableLiveDataShop = null;
    private static ShopRepository instance;
    private IStoreRepository iShopRepository;

    private ShopRepository(IStoreRepository iShopRepository,MutableLiveData<ArrayList<Shop>> list) {
        this.iShopRepository = iShopRepository;
        mutableLiveDataShop = list;
    }
    public static ShopRepository getInstance(IStoreRepository iShopRepository,MutableLiveData<ArrayList<Shop>> list) {
        if (instance == null) {
            instance = new ShopRepository(iShopRepository,list);
        }
        return instance;
    }

    public void getShopPaging(String query, int page) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("search", query)
                .addFormDataPart("page", page + "")
                .addFormDataPart("pageNumber", AppResources.getResourses().getString(R.string.NUMBER_ITEM_GET_FORM_API))
                .build();
        Request request = new Request.Builder()
                .url(API.get_URL_STRING(AppResources.getResourses().getString(R.string.GET_STORE_PAGING)))
                .addHeader("tokenkey", UserApp.user.getTokenKey())
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("Store Ex",e.getMessage());
                //move Error
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    String jsonString = response.body().string();
                    JSONObject jsonObject = new JSONObject(jsonString);
                    String stringResult = jsonObject.getString("result");
                    if (stringResult.equals(AppResources.getResourses().getString(R.string.SUCCESS_REQUEST))) {
                        JSONArray jsonShopArray = jsonObject.getJSONArray("data");
                        ArrayList<Shop> list = mutableLiveDataShop.getValue();
                        for (int i = 0; i < jsonShopArray.length(); i++) {
                            list.add(Shop.generateShopFromJSON(jsonShopArray.getJSONObject(i)));
                        }
                        iShopRepository.getShopData(list,null);
                    } else {
                        //move error
                    }
                } catch (JSONException e) {
                    Log.d("Store Ex",e.getMessage());
                    //move error
                }
            }
        });
    }

    /**
     * Find any shop match with search String in list shop already get form server
     * @param search
     * @return
     */
    public List<Shop> searchShop(String search) {
        if (search.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<Shop> data = mutableLiveDataShop.getValue();
       return data.stream().filter(store -> store.getName().contains(search) ||
                store.getAddress().contains(search) ||
                store.getPhoneNumber().contains(search))
                .collect(Collectors.toList());

    }
}
