package com.example.vietis.UI.view_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.R;

public class NotificationViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageShop;
    private TextView txtShopName;
    private TextView txtNotification;

    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        imageShop = itemView.findViewById(R.id.shopImage);
        txtShopName = itemView.findViewById(R.id.textShopName);
        txtNotification = itemView.findViewById(R.id.txtNotifications);
    }
    public void setNotification()
}
