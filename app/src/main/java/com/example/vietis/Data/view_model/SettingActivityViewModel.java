package com.example.vietis.Data.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.vietis.Data.entity.User;
import com.example.vietis.Data.IRepository.IUserRepository;


public class SettingActivityViewModel extends ViewModel implements IUserRepository {
    private MutableLiveData<User> user = new MutableLiveData<>();
    public LiveData<User> getSettingUser(){
        return user;
    }

    @Override
    public void afterLogin(User user, Exception error) {

    }

    @Override
    public void afterRegister(User user, Exception error) {

    }

    @Override
    public void getSettingData(User user, Exception error) {

    }
}