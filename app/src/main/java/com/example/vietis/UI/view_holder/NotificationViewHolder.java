package com.example.vietis.UI.view_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.IRepository.repository.Config;
import com.example.vietis.Data.entity.Notification;
import com.example.vietis.Data.inteface.IView;
import com.example.vietis.R;
import com.example.vietis.activities.Home.ui.notifications.NotificationsFragment;
import com.squareup.picasso.Picasso;

public class NotificationViewHolder extends RecyclerView.ViewHolder implements IView {
    private ImageView imageShop;
    private TextView txtShopName;
    private TextView txtNotification;
    private NotificationsFragment notificationActivity;
    private LinearLayout linearLayoutShopItem;

    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        mappingUI();
        setupUI();

    }
    public void setNotification(Notification notification){
//        Picasso.get().load(notification.getImageUrl())
//                .placeholder(R.drawable.ic_notification)
//                .into(imageShop);
        txtShopName.setText(notification.getTitle());
        txtNotification.setText(notification.getContent());

    }

    @Override
    public void mappingUI() {
        linearLayoutShopItem = itemView.findViewById(R.id.notification_layout);
        imageShop = itemView.findViewById(R.id.shopImage);
        txtShopName = itemView.findViewById(R.id.textShopName);
        txtNotification = itemView.findViewById(R.id.textNotification);
    }

    @Override
    public void setupUI() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationViewHolder.this.notificationActivity.navigateToDetailActivities(getLayoutPosition());
            }
        };
        Config.setChildViewOnClickListener(linearLayoutShopItem, listener);
    }
}
