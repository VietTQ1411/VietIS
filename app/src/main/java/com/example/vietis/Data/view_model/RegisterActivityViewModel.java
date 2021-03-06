package com.example.vietis.Data.view_model;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vietis.Data.IRepository.IUserRepository;
import com.example.vietis.Data.IRepository.repository.UserRepository;
import com.example.vietis.Data.entity.User;

public class RegisterActivityViewModel  extends ViewModel implements IUserRepository {
    private final MutableLiveData<User> user = new MutableLiveData<>();
    public LiveData<User> getUser(){
        return user;
    }
    public void register(String email, String password, String userType) {
        UserRepository.getInstance(this).register(email, password,userType);
    }
    @Override
    public void afterLogin(final User user, final Exception error) {

    }

    @Override
    public void afterRegister(final User user, final Exception error) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                RegisterActivityViewModel.this.user.setValue(error==null ? user : User.builder().build());
            }
        });
    }

    @Override
    public void getSettingData(User user, Exception error) {

    }
}