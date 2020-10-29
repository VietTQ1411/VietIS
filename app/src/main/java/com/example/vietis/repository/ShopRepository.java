package com.example.vietis.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.vietis.entity.Shop;
import com.example.vietis.inteface.IShopRepository;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShopRepository {

    private static final String URL_SEARCH_SHOP =
            "http://" + Config.HOST_NAME + ":" + Config.PORT + "/store/search";
    private static ShopRepository instance;
    private IShopRepository iShopRepository;

    private ShopRepository(IShopRepository iShopRepository) {
        this.iShopRepository = iShopRepository;
    }

    public static ShopRepository getInstance(IShopRepository iShopRepository) {
        if (instance == null) {
            instance = new ShopRepository(iShopRepository);
        }
        return instance;
    }

    public ArrayList<Shop> searchShopOnServer(String token, String query, int page, int pageNumber) {
        final ArrayList<Shop> list = new ArrayList<>();
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("search", query)
                .build();
        Request request = new Request.Builder()
                .url(URL_SEARCH_SHOP)
                .addHeader("tokenkey", token)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                list.clear();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    String jsonString = response.body().string();
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray jsonShopArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonShopArray.length(); i++) {
                        list.add(Shop.generateShopFromJSON(jsonShopArray.getJSONObject(i)));
                    }
                } catch (JSONException e) {
                    list.clear();
                }
            }
        });
        return list;
    }

    public ArrayList<Shop> searchShopInFakeData(String query) {
        if (query.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<Shop> fakeData = Shop.generateRandomShopArray();
        for (int i = 0; i < fakeData.size(); i++) {
            Shop item = fakeData.get(i);
            if (!item.shopContainQuery(query)) {
                fakeData.remove(i);
                i--;
            }
        }
        return fakeData;
    }

    public ArrayList<Shop> gennerateFakeShopArray(){
        return Shop.generateRandomShopArray();
    }
}
