package com.example.vietis.UI.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Notification;
import com.example.vietis.R;
import com.example.vietis.UI.view_holder.NotificationViewHolder;
import com.example.vietis.activities.Home.ui.notifications.NotificationsFragment;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder> {
    private ArrayList<Notification> notifications;
    private NotificationsFragment notificationActivity;

    public NotificationAdapter(ArrayList<Notification> notifications){
        this.notifications=notifications;
    }

    public void setNotificationActivity(NotificationsFragment notificationActivity) {
        this.notificationActivity = notificationActivity;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup itemView, int viewType) {
        View view = LayoutInflater.from(itemView.getContext())
                .inflate(R.layout.view_notification_item,itemView,false);
        NotificationViewHolder notificationViewHolder = new NotificationViewHolder(view);
        notificationViewHolder.setNotificationActivity(this.notificationActivity);
        return notificationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.setNotification(this.notifications.get(position));
    }

    @Override
    public int getItemCount() {
        return this.notifications.size();
    }
}
