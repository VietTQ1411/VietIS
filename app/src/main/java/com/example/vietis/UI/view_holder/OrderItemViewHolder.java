package com.example.vietis.UI.view_holder;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Comment;
import com.example.vietis.Data.entity.Order;
import com.example.vietis.Data.inteface.IView;
import com.example.vietis.R;
import com.example.vietis.Utilities.common.UserApp;
import com.squareup.picasso.Picasso;

public class OrderItemViewHolder extends RecyclerView.ViewHolder implements IView {

    //UI holders
    private ImageView imgOrder;
    private TextView txtDateCreate;
    private TextView txtOrderDescription;
    private TextView txtReason;
    private TextView txtOrderStatus;

    public OrderItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mappingUI();
        setupUI();
    }

    public void setOrderHolder(Order comment) {
        Picasso.get().load(comment.getImageURL())
                .placeholder(R.drawable.ic_launcher_foreground)
                .resize(80, 80)
                .centerCrop()
                .into(imgOrder);
        txtDateCreate.setText(comment.getCreateAt());
        txtOrderDescription.setText(comment.getDescription());
        txtReason.setText(comment.getReason());
        txtOrderStatus.setText(comment.getStatus() == 1 ? "Đang Ship" : comment.getStatus() == 2 ? "Đã Ship" : "Đã hủy");
        txtOrderStatus.setTextColor(comment.getStatus() == 1 ? Color.BLUE : comment.getStatus() == 2 ? Color.GREEN : Color.RED);
    }

    @Override
    public void mappingUI() {
        imgOrder = itemView.findViewById(R.id.imgOrder);
        txtDateCreate = itemView.findViewById(R.id.txtDateCreate);
        txtOrderDescription = itemView.findViewById(R.id.txtOrderDescription);
        txtReason = itemView.findViewById(R.id.txtReason);
        txtOrderStatus = itemView.findViewById(R.id.txtOrderStatus);
    }

    @Override
    public void setupUI() {

    }
}