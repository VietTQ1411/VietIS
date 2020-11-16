package com.example.vietis.Data.IRepository.repository;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.vietis.Data.IRepository.IOrderRepository;
import com.example.vietis.Data.entity.Order;
import com.example.vietis.Data.view_model.MutableArray;
import com.example.vietis.R;
import com.example.vietis.Utilities.common.AppResources;
import com.example.vietis.Utilities.common.UserApp;
import com.example.vietis.Utilities.helpers.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository {
    private String TAG = "OrderRepo";
    private static OrderRepository instance;
    private IOrderRepository iOrderRepository;


    private OrderRepository(IOrderRepository iShopRepository) {
        this.iOrderRepository = iShopRepository;
    }

    public static OrderRepository getInstance(IOrderRepository iShopRepository) {
        if (instance == null) {
            instance = new OrderRepository(iShopRepository);
        }
        return instance;
    }


    //String request Method.POST
    public void getAll() {
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,
                API.get_URL_STRING(AppResources.getResourses().getString(R.string.GET_Order))
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
                            MutableArray.getArrayList().add(Order.generateOrderFromJSON(jsonShopArray.getJSONObject(i)));
                        }
                        iOrderRepository.getData();
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
                params.put("userId", UserApp.user.getId() + "");
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

    public void addData(Order order) {
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,
                API.get_URL_STRING(AppResources.getResourses().getString(R.string.ADD_Order))
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String StringResponse) {
                Log.v("LOG_VOLLEY", StringResponse);
                iOrderRepository.addDataDone();
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
                params.put("userId", UserApp.user.getId() + "");
                params.put("description", order.getDescription());
                params.put("quantity", order.getQuantity() + "");
                params.put("total", order.getTotal() + "");
                params.put("imageId", order.getImageURL());
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
