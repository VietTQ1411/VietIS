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
import com.squareup.picasso.Picasso;

public class ShopItemViewHolder extends RecyclerView.ViewHolder implements IView {

    //UI holders
    private ImageView imageViewShopIcon;
    private TextView textViewShopName;
    private TextView textViewShopAddress;
    private RatingBar ratingBarShopRating;
    private TextView textViewVoucher;

    public ShopItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mappingUI();
        setupUI();
    }

    public void setShopItem(Shop shop, int pos) {
        Picasso.get().load(shop.getImageURL())
                .placeholder(R.drawable.ic_launcher_foreground)
                .resize(100, 100)
                .centerCrop()
                .into(imageViewShopIcon);
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
        textViewVoucher = itemView.findViewById(R.id.textViewVoucher);
    }

    @Override
    public void setupUI() {

    }
}
