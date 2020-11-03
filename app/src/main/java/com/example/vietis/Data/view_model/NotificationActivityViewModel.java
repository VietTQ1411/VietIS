package com.example.vietis.Data.view_model;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vietis.Data.entity.Notification;
import com.example.vietis.Data.inteface.INotiRepository;
import com.example.vietis.Data.inteface.repository.NotificationRepository;

import java.util.ArrayList;

public class NotificationActivityViewModel extends ViewModel implements INotiRepository {
    private MutableLiveData<ArrayList<Notification>> notifications = new MutableLiveData<>();
    public LiveData<ArrayList<Notification>> getNotifications(){return notifications;}
    public void getListNoti(){
        NotificationRepository.getInstance(this).getListNoti();
    }

    @Override
    public void getNotiMessage(String msg, Exception error) {

    }

    @Override
    public void getNotiList(final ArrayList<Notification> notifications,final Exception error) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                NotificationActivityViewModel.this.notifications.setValue(error==null ? notifications : new ArrayList<Notification>());
            }
        });
    }
}
