package com.example.vietis.Data.IRepository.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vietis.Data.IRepository.IStoreRepository;
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ShopRepository {
    private String TAG = "StoreRepo";
    private static ShopRepository instance;
    private IStoreRepository iShopRepository;


    private ShopRepository(IStoreRepository iShopRepository) {
        this.iShopRepository = iShopRepository;
    }

    public static ShopRepository getInstance(IStoreRepository iShopRepository) {
        if (instance == null) {
            instance = new ShopRepository(iShopRepository);
        }
        return instance;
    }

    /**
     * Find any shop match with search String in list shop already get form server
     *
     * @param search
     * @return
     */
    public List<Shop> searchShop(ArrayList<Shop> data, String search) {
        if (search.isEmpty()) {
            return new ArrayList<>();
        }
        // ArrayList<Shop> data = mutableLiveDataShop.getValue();
        return data.stream().filter(store -> store.getName().contains(search) ||
                store.getAddress().contains(search) ||
                store.getPhoneNumber().contains(search))
                .collect(Collectors.toList());
    }

    //String request Method.POST
    public void getShopPaging(String query, int page) {
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,
                API.get_URL_STRING(AppResources.getResourses().getString(R.string.GET_STORE_PAGING))
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
                            MutableArray.getArrayList().add(Shop.generateShopFromJSON(jsonShopArray.getJSONObject(i)));
                        }
                        iShopRepository.getShopData();
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
                params.put("page", page + "");
                params.put("pageNumber", AppResources.getResourses().getString(R.string.NUMBER_ITEM_GET_FORM_API));
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
}