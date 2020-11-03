package com.example.vietis.Data.view_model;

import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vietis.Data.inteface.INotiRepository;
import com.example.vietis.Data.inteface.repository.NotificationRepository;

import android.os.Handler;

public class SplashActivityViewModel extends ViewModel implements INotiRepository {
    private MutableLiveData<String> msg= new MutableLiveData<>();
    public LiveData<String> getMsg(){return msg;}
    public void deviceRegister (String tokenKey, String userId){
        NotificationRepository.getInstance(this).deviceRegister(tokenKey,userId);
    }
    @Override
    public void getNotiMessage(final String msg, final Exception error) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                SplashActivityViewModel.this.msg.setValue(error==null ? msg : "");
            }
        });
    }
}