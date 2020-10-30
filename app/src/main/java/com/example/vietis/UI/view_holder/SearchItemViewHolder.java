package com.example.vietis.UI.view_holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.activities.IView;


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
