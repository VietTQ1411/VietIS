package com.example.vietis.activities.Home.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Notification;
import com.example.vietis.Data.inteface.IView;
import com.example.vietis.Data.view_model.NotificationActivityViewModel;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.NotificationAdapter;
import com.example.vietis.activities.Home.ui.home.FoodDetailActivity;
import com.example.vietis.activities.Home.ui.store.StoreDetailActivity;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment implements IView {
    private View view;
    private RecyclerView notificationRecyclerView;
    private NotificationActivityViewModel notificationActivityViewModel;
    private ArrayList<Notification> notifications;


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        view = root;
        mappingUI();
        setupUI();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        setupUI();
    }

    @Override
    public void mappingUI() {
        notificationRecyclerView = view.findViewById(R.id.notification_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                view.getContext(),
                RecyclerView.VERTICAL,false);
        notificationRecyclerView.setLayoutManager(layoutManager);
        notificationActivityViewModel = new NotificationActivityViewModel();
    }

    @Override
    public void setupUI() {
        notificationActivityViewModel.getListNoti();
        notificationActivityViewModel.getNotifications().observe(getViewLifecycleOwner(), new Observer<ArrayList<Notification>>() {
            @Override
            public void onChanged(ArrayList<Notification> notifications) {
                NotificationsFragment.this.notifications = notifications;
            NotificationAdapter notificationAdapter = new NotificationAdapter(notifications);
            notificationAdapter.setNotificationActivity(NotificationsFragment.this);
            notificationRecyclerView.setAdapter(notificationAdapter);
            }
        });
    }
    public void navigateToDetailActivities(Integer position){
        if(notifications.get(position).getIdType().equals("store")) {
            Intent intent = new Intent(view.getContext(), StoreDetailActivity.class);
            Notification selectedNotification = notifications.get(position);
            intent.putExtra("id", selectedNotification.getStoreId() +"");
            this.startActivity(intent);
        }else{
            Intent intent = new Intent(view.getContext(), FoodDetailActivity.class);
            Notification selectedNotification = notifications.get(position);
            intent.putExtra("id", selectedNotification.getFoodId());
            this.startActivity(intent);
        }
    }
}