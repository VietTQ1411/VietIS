package com.example.vietis.activities.Home.ui.order;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Food;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.Data.view_model.ListActivityModel;
import com.example.vietis.Data.view_model.MutableArray;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.SearchAdapter;
import com.example.vietis.Data.inteface.IListView;
import com.example.vietis.Data.inteface.IView;

import java.util.ArrayList;

public class OrderFragment extends Fragment implements IView {

    //UI holders
    private SearchView searchViewSearch;
    private static View view;
    private RecyclerView recyclerOrderViewSearch;

    //View Model
    private ListActivityModel foodActivityModel;
    private int PAGE = 0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            View root = inflater.inflate(R.layout.fragment_home, container, false);
            view = root;

            new Thread(new Runnable() {
                public void run() {
                    mappingUI();
                    setupUI();
                    getData();
                }
            }).start();
            return root;
        } else {
            getData();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStop() {
        super.onStop();
        PAGE = 0;
        MutableArray.clearData();
    }


    @Override
    public void mappingUI() {

        recyclerOrderViewSearch = view.findViewById(R.id.recyclerOrderViewSearch);
        foodAdapter = new SearchAdapter(this, new ArrayList<Food>(), Food.class);
        foodActivityModel = new ListActivityModel(com.example.vietis.activities.Home.ui.home.HomeFragment.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerViewSearch.setLayoutManager(layoutManager);
        recyclerViewSearch.setAdapter(foodAdapter);
    }

    @Override
    public void setupUI() {
        searchViewSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                PAGE = 0;
                foodActivityModel.searchFoodFormServerWithPage(query, PAGE);
                PAGE++;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
                    //   foodAdapter.setObjectArray(foodActivityModel.getFoodData().getValue());
                    foodAdapter.notifyDataSetChanged();
                    recyclerViewSearch.setAdapter(foodAdapter);
                    return false;
                }
                foodAdapter.setObjectArray(foodActivityModel.searchFood(newText));
                foodAdapter.notifyDataSetChanged();
                recyclerViewSearch.setAdapter(foodAdapter);
                return false;
            }
        });
        getData();
    }

    public void getData() {
        foodActivityModel = new ListActivityModel(com.example.vietis.activities.Home.ui.home.HomeFragment.this);
        foodActivityModel.searchFoodFormServerWithPage("", PAGE);
    }

    public void setUpData(ArrayList<Food> list) {
        foodAdapter.setObjectArray(list);
        foodAdapter.notifyItemInserted(list.size());
        foodAdapter.notifyDataSetChanged();
    }


}