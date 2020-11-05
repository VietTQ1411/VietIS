package com.example.vietis.UI.view_holder;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.inteface.IListView;
import com.example.vietis.Data.inteface.repository.Config;
import com.example.vietis.R;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.activities.HomeActivity;
import com.example.vietis.activities.IView;
import com.example.vietis.activities.SearchActivity;
import com.squareup.picasso.Picasso;

public class ShopItemViewHolder extends RecyclerView.ViewHolder implements IView {

    //UI holders
    private LinearLayout linearLayoutShopItem;
    private ImageView imageViewShopIcon;
    private TextView textViewShopName;
    private TextView textViewShopAddress;
    private RatingBar ratingBarShopRating;
    private TextView textViewVoucher;

    //Parent
    private IListView parent;

    public ShopItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mappingUI();
        setupUI();
    }

    public void setParent(IListView parent) {
        this.parent = parent;
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
        linearLayoutShopItem = itemView.findViewById(R.id.linearShopItem);
        imageViewShopIcon = itemView.findViewById(R.id.imageViewShopIcon);
        textViewShopName = itemView.findViewById(R.id.textViewShopName);
        textViewShopAddress = itemView.findViewById(R.id.textViewShopAddress);
        ratingBarShopRating = itemView.findViewById(R.id.ratingBarShopRating);
        textViewVoucher = itemView.findViewById(R.id.textViewVoucher);
    }

    @Override
    public void setupUI() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent.navigateToShopDetail(getLayoutPosition());
            }
        };
        Config.setChildViewOnClickListener(linearLayoutShopItem, listener);
    }
}
