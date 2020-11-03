package com.example.vietis.UI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Notification;
import com.example.vietis.R;
import com.example.vietis.UI.view_holder.NotificationViewHolder;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder> {
    private ArrayList<Notification> notifications;
    private Context context;

    public NotificationAdapter(Context context,ArrayList<Notification> notifications){
        this.context=context;
        this.notifications=notifications;
    }
    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_notification_item,parent,false);
        return new NotificationViewHolder(view);
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
