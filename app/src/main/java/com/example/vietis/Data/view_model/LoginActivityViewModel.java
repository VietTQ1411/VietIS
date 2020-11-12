package com.example.vietis.Data.view_model;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vietis.Data.entity.User;
import com.example.vietis.Data.IRepository.IUserRepository;
import com.example.vietis.Data.IRepository.repository.UserRepository;
import com.example.vietis.activities.LoginActivity;

public class LoginActivityViewModel extends ViewModel implements IUserRepository {
    private MutableLiveData<User> user = new MutableLiveData<>();
    private LoginActivity loginActivity;

    public LoginActivityViewModel(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void login(String email, String password) {
        UserRepository.getInstance(this).login(email, password);
    }

    @Override
    public void afterLogin(final User user, final Exception error) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                loginActivity.navigateToHomeActivity(user);
            }
        });
    }

    @Override
    public void afterRegister(User user, Exception error) {

    }

    @Override
    public void getSettingData(User user, Exception error) {

    }


}
