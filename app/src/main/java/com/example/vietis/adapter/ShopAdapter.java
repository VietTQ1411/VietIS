package com.example.vietis.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.R;
import com.example.vietis.entity.Shop;
import com.example.vietis.view_holder.SearchItemViewHolder;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<SearchItemViewHolder> {
    private ArrayList<Shop> arrayListShop;

    public ShopAdapter(ArrayList<Shop> arrayListShop) {
        this.arrayListShop = arrayListShop;
    }

    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.search_view_item, parent, false);
        SearchItemViewHolder searchItemViewHolder = new SearchItemViewHolder(view);
        return searchItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, int position) {
        holder.setShopItem(this.arrayListShop.get(position), position);
    }

    @Override
    public int getItemCount() {
        return arrayListShop.size();
    }
}
