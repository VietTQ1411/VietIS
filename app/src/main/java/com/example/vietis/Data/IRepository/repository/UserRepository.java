package com.example.vietis.Data.IRepository.repository;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.vietis.Data.IRepository.IUserRepository;
import com.example.vietis.Data.entity.User;
import com.example.vietis.R;
import com.example.vietis.Utilities.common.AppResources;
import com.example.vietis.Utilities.helpers.API;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserRepository {
    private static UserRepository instance = null;
    private IUserRepository iUserRepository;
    private List<User> users;
    private final String TAG = "User Repository";

    private UserRepository(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public static UserRepository getInstance(IUserRepository iUserRepository) {
        if (instance == null) {
            instance = new UserRepository(iUserRepository);
        }
        return instance;
    }

    public void login(String email, String password) {
        StringRequest jsonObjectRequest = new StringRequest(com.android.volley.Request.Method.POST,
                API.get_URL_STRING(AppResources.getResourses().getString(R.string.USER_LOGIN))
                , new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String StringResponse) {
                try {
                    JSONObject jsonObject = new JSONObject(StringResponse);
                    String stringResult = jsonObject.getString("result");
                    if (stringResult.equals(AppResources.getResourses().getString(R.string.SUCCESS_REQUEST))) {
                        JSONObject jsonUserObject = jsonObject.getJSONObject("data");
                        User user = User.createUserFromJSONObject(jsonUserObject);
                        user.setImageURL(jsonUserObject.getJSONObject("Image_model").getString("imageURL"));
                        iUserRepository.afterLogin(user, null);
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
                params.put("email", email);
                params.put("password", password);
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

    public void register(String email, String password, String name, String userType) {
        StringRequest jsonObjectRequest = new StringRequest(com.android.volley.Request.Method.POST,
                API.get_URL_STRING(AppResources.getResourses().getString(R.string.USER_REGISTER))
                , new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String StringResponse) {
                try {
                    JSONObject jsonObject = new JSONObject(StringResponse);
                    String stringResult = jsonObject.getString("result");
                    if (stringResult.equals(AppResources.getResourses().getString(R.string.SUCCESS_REQUEST))) {
                        JSONObject jsonUserObject = jsonObject.getJSONObject("data");
                        User user = User.createUserFromJSONObject(jsonUserObject);

                        iUserRepository.afterRegister(user, null);
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
                params.put("password", password);
                params.put("userType", userType);
                params.put("email", email);
                params.put("name", name);
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

    public void getSettingData(User user) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userid", String.valueOf(user.getId()))
                .build();
        Request request = new Request.Builder()
                .url(API.get_URL_STRING(AppResources.getResourses().getString(R.string.USER_SETTING)))
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                iUserRepository.getSettingData(null, e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    String jsonString = response.body().string();
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONObject jsonUserObject = jsonObject.getJSONObject("data");
                    User user = User.createUserFromJSONObject(jsonUserObject);
                    iUserRepository.getSettingData(user, null);
                } catch (JSONException e) {
                    iUserRepository.getSettingData(null, e);
                }
            }
        });
    }
}