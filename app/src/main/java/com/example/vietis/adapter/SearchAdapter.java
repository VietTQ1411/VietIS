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

public class SearchAdapter extends RecyclerView.Adapter<SearchItemViewHolder> {
    private ArrayList<Shop> arrayListShop;

    public SearchAdapter(ArrayList<Shop> arrayListShop) {
        this.arrayListShop = arrayListShop;
    }

    public void setShopArray(ArrayList<Shop> list) {
        this.arrayListShop = list;
    }

    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.view_search_item, parent, false);
        return new SearchItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, int position) {
        holder.getShopItemViewHolder().setShopItem(this.arrayListShop.get(position), position);
    }

    @Override
    public int getItemCount() {
        return arrayListShop.size();
    }
}
