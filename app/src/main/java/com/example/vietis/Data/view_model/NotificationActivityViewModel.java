package com.example.vietis.Data.view_model;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vietis.Data.entity.Notification;
import com.example.vietis.Data.IRepository.INotiRepository;
import com.example.vietis.Data.IRepository.repository.NotificationRepository;
import com.example.vietis.Data.entity.Order;
import com.example.vietis.activities.Home.ui.notifications.NotificationsFragment;

import java.util.ArrayList;

public class NotificationActivityViewModel extends ViewModel implements INotiRepository {
    NotificationsFragment parent;
    private ArrayList<Notification> listfood = new ArrayList<>();

    public NotificationActivityViewModel(NotificationsFragment in) {
        this.parent = in;
    }

    public void getList() {
        NotificationRepository.getInstance(this).getListNoti();
    }

    @Override
    public void getNotiMessage(String msg, Exception error) {

    }

    @Override
    public void getAllNotiList() {
        parent.setUpData(getNotiData());
    }


    public ArrayList<Notification> getNotiData() {
        listfood.clear();
        for (Object t : MutableArray.getArrayList()) {
            listfood.add((Notification) t);
        }
        return listfood;
    }
}
