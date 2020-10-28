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


public class SearchItemViewHolder extends RecyclerView.ViewHolder implements IView {

    //UI holders
    private ShopItemViewHolder shopItemViewHolder;


    public SearchItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.shopItemViewHolder = new ShopItemViewHolder(itemView);
        mappingUI();
        setupUI();
    }

    public ShopItemViewHolder getShopItemViewHolder() {
        return shopItemViewHolder;
    }

    @Override
    public void mappingUI() {
    }

    @Override
    public void setupUI() {
    }
}
