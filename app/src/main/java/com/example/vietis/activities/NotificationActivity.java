package com.example.vietis.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Notification;
import com.example.vietis.Data.view_model.NotificationActivityViewModel;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.NotificationAdapter;

import java.util.ArrayList;


public class NotificationActivity extends AppCompatActivity implements IView {
    private RecyclerView notiRecyclerView;
    private NotificationActivityViewModel notificationActivityViewModel = new NotificationActivityViewModel();
    private static ArrayList<Notification> notifications = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mappingUI();
        setupUI();
    }

    @Override
    public void mappingUI() {
        notiRecyclerView = findViewById(R.id.notification_recycler_view);
        notifications = Notification.fakeNoti();

        NotificationAdapter notiAdapter = new NotificationAdapter(
                NotificationActivity.this,
                notifications);
        notiAdapter.setNotificationActivity(NotificationActivity.this);
        notiRecyclerView.setAdapter(notiAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                getApplicationContext(),
                RecyclerView.VERTICAL,false
        );
        notiRecyclerView.setLayoutManager(layoutManager);

//        notificationActivityViewModel.getListNoti();
//        notificationActivityViewModel.getNotifications().observe(this, new Observer<ArrayList<Notification>>() {
//            @Override
//            public void onChanged(ArrayList<Notification> notifications) {
//                NotificationActivity.notifications = notifications;
//
//            }
//        });

    }

    @Override
    public void setupUI() {

    }
    public void navigateToDetailActivities(Integer position){
//        if(notifications.get(position).getIdType().equals("store")) {
            Intent intent = new Intent(NotificationActivity.this, StoreDetailActivity.class);
            Notification selectedNotification = notifications.get(position);
            intent.putExtra("id", selectedNotification.getStoreId() +"");
            this.startActivity(intent);
//        }else{
//            Intent intent = new Intent(NotificationActivity.this, FoodDetailActivity.class);
//            Notification selectedNotification = notifications.get(position);
//            intent.putExtra("id", selectedNotification.getFoodId());
//            this.startActivity(intent);
//        }
    }
}
