package com.example.vietis.repository;

import com.example.vietis.entity.User;
import com.example.vietis.inteface.IUserRepository;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

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
    public static final String URL_LOGIN =
            "http://"+ Config.HOST_NAME+":"+Config.PORT+"/users/login";
    private UserRepository(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }
    public static UserRepository getInstance(IUserRepository iUserRepository) {
        if(instance == null) {
            instance = new UserRepository(iUserRepository);
        }
        return instance;
    }
    public void login(String email, String password){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email",email)
                .addFormDataPart("password",password)
                .build();
        Request request = new Request.Builder()
                .url(URL_LOGIN)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException ioException) {
                iUserRepository.afterLogin(null, ioException);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try{
                    String jsonString = response.body().toString();
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONObject jsonUserObject =jsonObject.getJSONObject("data");
                    User user = User.createUserFromJSONObject(jsonUserObject);
                    iUserRepository.afterLogin(user,null);
                }catch (JSONException e){
                    iUserRepository.afterLogin(null, e);
                }
            }
        });
    }
}
