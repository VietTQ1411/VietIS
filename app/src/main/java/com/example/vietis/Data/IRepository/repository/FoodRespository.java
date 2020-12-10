package com.example.vietis.Data.IRepository.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.vietis.Data.IRepository.IFoodRespository;
import com.example.vietis.Data.entity.Food;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.Data.view_model.MutableArray;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class FoodRespository {
    private static final String TAG = "FoodRepo";
    private static FoodRespository instance;
    private IFoodRespository iFoodRespository;

    private FoodRespository(IFoodRespository iFoodRespository) {
        this.iFoodRespository = iFoodRespository;
    }

    public static FoodRespository getInstance(IFoodRespository iFoodRespo) {
        if (instance == null) {
            instance = new FoodRespository(iFoodRespo);
        }
        instance.iFoodRespository = iFoodRespo;
        return instance;
    }

    public void getFoodPaging(String query, int offset) {
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,
                API.get_URL_STRING(AppResources.getResourses().getString(R.string.GET_FOOD_PAGING))
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String StringResponse) {
                Log.v("LOG_VOLLEY", StringResponse);

                try {
                    JSONObject response = new JSONObject(StringResponse);
                    String stringResult = response.getString("result");
                    if (stringResult.equals(AppResources.getResourses().getString(R.string.SUCCESS_REQUEST))) {
                        JSONArray jsonShopArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonShopArray.length(); i++) {
                            MutableArray.getArrayList().add(Food.generateFoodFromJSON(jsonShopArray.getJSONObject(i)));
                        }
                        iFoodRespository.getFoodData();
                    } else {
                        //move error
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "JsonObjectRequest onErrorResponse: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("search", query);
                params.put("offset", offset + "");
                params.put("limit", AppResources.getResourses().getString(R.string.NUMBER_ITEM_GET_FORM_API));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                params.put("tokenkey", UserApp.user.getTokenKey());
                return params;
            }
        };
        jsonObjectRequest.setTag(TAG);
        VolleySingleton.getInstance(AppResources.getContext()).getRequestQueue().add(jsonObjectRequest);
    }

    public void getFoodDetail(String query) {
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,
                API.get_URL_STRING(AppResources.getResourses().getString(R.string.GET_FOOD_DETAIL))
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String StringResponse) {
                Log.v("LOG_VOLLEY", StringResponse);
                MutableArray.clearData();
                try {
                    JSONObject response = new JSONObject(StringResponse);
                    String stringResult = response.getString("result");
                    if (stringResult.equals(AppResources.getResourses().getString(R.string.SUCCESS_REQUEST))) {
                        JSONArray jsonShopArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonShopArray.length(); i++) {
                            MutableArray.getArrayList().add(Food.generateFoodFromJSON(jsonShopArray.getJSONObject(i)));
                        }
                        iFoodRespository.getFoodData();
                    } else {
                        //move error
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "JsonObjectRequest onErrorResponse: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", query);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                params.put("tokenkey", UserApp.user.getTokenKey());
                return params;
            }
        };
        jsonObjectRequest.setTag(TAG);
        VolleySingleton.getInstance(AppResources.getContext()).getRequestQueue().add(jsonObjectRequest);
    }

    /**
     * Find any Food match with search String in list shop already get form server
     *
     * @param search
     * @return
     */
    public List<Food> searchFood(ArrayList<Food> data, String search) {
        if (search.isEmpty()) {
            return new ArrayList<>();
        }
        return data.stream().filter(food -> food.getName().contains(search))
                .collect(Collectors.toList());

    }
}

