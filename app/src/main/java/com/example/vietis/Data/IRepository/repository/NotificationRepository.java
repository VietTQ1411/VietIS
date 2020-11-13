package com.example.vietis.Data.IRepository.repository;

import com.example.vietis.Data.entity.Notification;
import com.example.vietis.Data.IRepository.INotiRepository;
import com.example.vietis.Utilities.helpers.API;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NotificationRepository {
    private static NotificationRepository instance = null;
    private INotiRepository iNotiRepository;
    public static final String URL_NOTI = API.get_URL_STRING("noti/addTokenKey");
    public static final String URL_LIST_NOTI = API.get_URL_STRING("noti/getListNoti");

    private NotificationRepository(INotiRepository iNotiRepository) {
        this.iNotiRepository = iNotiRepository;
    }

    public static NotificationRepository getInstance(INotiRepository iNotiRepository) {
        if (instance == null) {
            instance = new NotificationRepository(iNotiRepository);
        }
        return instance;
    }

    public void deviceRegister(String tokenKey, String userId){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userId", userId)
                .addFormDataPart("tokenKey", tokenKey)
                .build();
        Request request = new Request.Builder()
                .url(API.get_URL_STRING("noti/addTokenKey"))
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException ioException) {
                iNotiRepository.getNotiMessage(null, ioException);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    String jsonString = response.body().string();
                    JSONObject jsonNotiMsg = new JSONObject(jsonString);
                    String msg = jsonNotiMsg.getString("message");
                    iNotiRepository.getNotiMessage(msg, null);
                } catch (JSONException e) {
                    iNotiRepository.getNotiMessage(null, e);
                    System.out.println(e.toString());
                }
            }
        });
    }

    public void getListNoti(){
        ArrayList<Notification> notifications = new ArrayList<>();
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("nothing","")
                .build();
        Request request = new Request.Builder()
                .url(URL_LIST_NOTI)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                iNotiRepository.getNotiList(null,e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try{
                    String jsonString = response.body().string();
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray jsonNotificationObject = jsonObject.getJSONArray("data");
                    for (int i=0;i<jsonNotificationObject.length();i++){
                        notifications.add(Notification.createNotificationFromJSONObject(
                                jsonNotificationObject.getJSONObject(i)
                        ));
                    }
                    iNotiRepository.getNotiList(notifications,null);
                }catch (JSONException jsonException){
                    iNotiRepository.getNotiList(null,jsonException);
                }
            }
        });

    }
}
