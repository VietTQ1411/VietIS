package com.example.vietis.Data.view_model;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vietis.Data.entity.User;
import com.example.vietis.Data.IRepository.IUserRepository;
import com.example.vietis.Data.IRepository.repository.UserRepository;

public class LoginActivityViewModel extends ViewModel implements IUserRepository {
    private MutableLiveData<User> user = new MutableLiveData<>();
    public LiveData<User> getUser(){
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
                LoginActivityViewModel.this.user.setValue(error==null ? user : User.builder().build());
            }
        });
    }

    @Override
    public void afterRegister(User user, Exception error) {

    }
}
