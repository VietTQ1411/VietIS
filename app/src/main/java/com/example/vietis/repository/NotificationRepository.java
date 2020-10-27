package com.example.vietis.repository;

import com.example.vietis.inteface.INotiRepository;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
    private String msg;
    public static final String URL_NOTI ="http://"+ Config.HOST_NAME+":"+Config.PORT+"/noti/addTokenKey";
    private NotificationRepository (INotiRepository iNotiRepository){
        this.iNotiRepository=iNotiRepository;
    }
    public static NotificationRepository getInstance(INotiRepository iNotiRepository){
        if(instance==null){
            instance= new NotificationRepository(iNotiRepository);
        }
        return instance;
    }
    public void deviceRegister(String tokenKey, String userId){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userId",userId)
                .addFormDataPart("tokenKey",tokenKey)
                .build();
        Request request = new Request.Builder()
                .url(URL_NOTI)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException ioException) {
                iNotiRepository.getNotiMessage(null,ioException);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try{
                    String jsonString = response.body().string();
                    JSONObject jsonNotiMsg = new JSONObject(jsonString);
                    String msg = jsonNotiMsg.getString("message");
                    iNotiRepository.getNotiMessage(msg,null);
                }catch (JSONException e){
                    iNotiRepository.getNotiMessage(null,e);
                    System.out.println(e.toString());
                }
            }
        });
    }
}
