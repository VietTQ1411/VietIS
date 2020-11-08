package com.example.vietis.UI.view_holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.activities.IListView;
import com.example.vietis.activities.IView;


public class SearchItemViewHolder<T> extends RecyclerView.ViewHolder implements IView {

    //UI holders
    private ShopItemViewHolder shopItemViewHolder;
    private FoodItemViewHolder foodItemViewHolder;


    public SearchItemViewHolder(@NonNull View itemView, IListView parent, boolean isStore) {
        super(itemView);
        if (isStore) {
            this.shopItemViewHolder = new ShopItemViewHolder(itemView);
            this.shopItemViewHolder.setParent(parent);
        } else {
            this.foodItemViewHolder = new FoodItemViewHolder(itemView);
            this.foodItemViewHolder.setParent(parent);
        }
    }

    public ShopItemViewHolder getShopItemViewHolder() {
        return shopItemViewHolder;
    }

    public FoodItemViewHolder getFoodItemViewHolder() {
        return foodItemViewHolder;
    }

    @Override
    public void mappingUI() {
    }

    @Override
    public void setupUI() {
    }

}
