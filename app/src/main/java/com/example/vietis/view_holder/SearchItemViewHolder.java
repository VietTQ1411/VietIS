package com.example.vietis.view_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.R;
import com.example.vietis.entity.Shop;
import com.example.vietis.inteface.IView;


public class SearchItemViewHolder extends RecyclerView.ViewHolder implements IView {
    //UI holders
    private ImageView imageViewShopIcon;
    private TextView textViewShopName;
    private TextView textViewShopAddress;
    private RatingBar ratingBarShopRating;
    private TextView textViewShopVoucher;

    public SearchItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mappingUI();
        setupUI();
    }

    public void setShopItem(Shop shop, int pos) {
        textViewShopName.setText(shop.getName());
        textViewShopAddress.setText(shop.getAddress());
        ratingBarShopRating.setRating(shop.getRating());
    }

    @Override
    public void mappingUI() {
        imageViewShopIcon = itemView.findViewById(R.id.imageViewShopIcon);
        textViewShopName = itemView.findViewById(R.id.textViewShopName);
        textViewShopAddress = itemView.findViewById(R.id.textViewShopAddress);
        ratingBarShopRating = itemView.findViewById(R.id.ratingBarShopRating);
        textViewShopVoucher = itemView.findViewById(R.id.textViewShopVoucher);
    }

    @Override
    public void setupUI() {

    }
}
