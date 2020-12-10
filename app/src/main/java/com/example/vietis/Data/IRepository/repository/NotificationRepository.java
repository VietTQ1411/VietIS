package com.example.vietis.Data.IRepository.repository;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.vietis.Data.entity.Notification;
import com.example.vietis.Data.IRepository.INotiRepository;
import com.example.vietis.Data.entity.Order;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NotificationRepository {
    private static NotificationRepository instance = null;
    private final INotiRepository iNotiRepository;
    private final String TAG = "Noti Repo";
    private NotificationRepository(INotiRepository iNotiRepository) {
        this.iNotiRepository = iNotiRepository;
    }

    public static NotificationRepository getInstance(INotiRepository iNotiRepository) {
            if(instance==null){
                instance = new NotificationRepository(iNotiRepository);
            }
        return instance;
    }

    public void deviceRegister(String tokenKey){
        StringRequest jsonObjectRequest = new StringRequest(com.android.volley.Request.Method.POST,
                API.get_URL_STRING(AppResources.getResourses().getString(R.string.ADD_TOKEN_KEY))
                , new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String StringResponse) {
                Log.v("LOG_VOLLEY", StringResponse);
                MutableArray.clearData();
                        try {
                            JSONObject response = new JSONObject(StringResponse);
                            String msg = response.getString("result");
                            iNotiRepository.getNotiMessage(msg, null);
                        } catch (JSONException e) {
                            iNotiRepository.getNotiMessage(null, e);

                        }
                    }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iNotiRepository.getNotiMessage(null, error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tokenKey", tokenKey);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        jsonObjectRequest.setTag(TAG);
        VolleySingleton.getInstance(AppResources.getContext()).getRequestQueue().add(jsonObjectRequest);


}
    public void getListNoti() {
        StringRequest jsonObjectRequest = new StringRequest(com.android.volley.Request.Method.POST,
                API.get_URL_STRING(AppResources.getResourses().getString(R.string.NOTI_GETALL))
                , new com.android.volley.Response.Listener<String>() {
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
                            MutableArray.getArrayList().add(Notification.createNotificationFromJSONObject(jsonShopArray.getJSONObject(i)));
                        }
                        iNotiRepository.getAllNotiList();
                    } else {
                        //move error
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "JsonObjectRequest onErrorResponse: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userId", UserApp.user.getId()+"");
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
