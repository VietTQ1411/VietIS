package com.example.vietis.Data.IRepository.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.vietis.Data.IRepository.IFoodRespository;
import com.example.vietis.Data.entity.Food;
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

public class FoodRespository {
    private static FoodRespository instance;
    private IFoodRespository iFoodRespository;
    private MutableLiveData<ArrayList<Food>> mutableLiveDataFood = null;

    private FoodRespository(IFoodRespository iFoodRespository, MutableLiveData<ArrayList<Food>> list) {
        this.iFoodRespository = iFoodRespository;
        mutableLiveDataFood = list;
    }

    public static FoodRespository getInstance(IFoodRespository iFoodRespository, MutableLiveData<ArrayList<Food>> list) {
        if (instance == null) {
            instance = new FoodRespository(iFoodRespository, list);
        }
        return instance;
    }

    public void getFoodPaging(String query, int page) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("search", query)
                .addFormDataPart("page", page + "")
                .addFormDataPart("pageNumber", AppResources.getResourses().getString(R.string.NUMBER_ITEM_GET_FORM_API))
                .build();
        Request request = new Request.Builder()
                .url(API.get_URL_STRING(AppResources.getResourses().getString(R.string.GET_FOOD_PAGING)))
                .addHeader("tokenkey", UserApp.user.getTokenKey())
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("Store Ex", e.getMessage());
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
                        ArrayList<Food> list = mutableLiveDataFood.getValue();
                        for (int i = 0; i < jsonShopArray.length(); i++) {
                            list.add(Food.generateFoodFromJSON(jsonShopArray.getJSONObject(i)));
                        }
                        iFoodRespository.getFoodData(list, null);
                    } else {
                        //move error
                    }
                } catch (JSONException e) {
                    Log.d("Store Ex", e.getMessage());
                    //move error
                }
            }
        });
    }

    /**
     * Find any Food match with search String in list shop already get form server
     *
     * @param search
     * @return
     */
    public List<Food> searchFood(String search) {
        if (search.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<Food> data = mutableLiveDataFood.getValue();
        return data.stream().filter(food -> food.getName().contains(search))
                .collect(Collectors.toList());

    }
}

