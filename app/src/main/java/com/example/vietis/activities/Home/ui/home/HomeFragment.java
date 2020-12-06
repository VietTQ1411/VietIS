package com.example.vietis.activities.Home.ui.home;

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
import com.example.vietis.Utilities.common.AppResources;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements IView, IListView {

    //UI holders
    private SearchView svSearch;
    private static View view;
    private RecyclerView recyclerViewSearch;


    //RecyclerView components
    private static SearchAdapter<Food> foodAdapter;
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
                }
            }).start();
            return root;
        }else{
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
        svSearch = view.findViewById(R.id.svSearch);
        recyclerViewSearch = view.findViewById(R.id.recyclerViewFoodSearch);
        rvTopCategories = view.findViewById(R.id.rvTopCategories);
        foodAdapter = new SearchAdapter(this, new ArrayList<Food>(), Food.class);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.VERTICAL, false);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.HORIZONTAL,false);
        rvTopCategories.setLayoutManager(layoutManager1);
        rvTopCategories.setAdapter(foodAdapter);
        recyclerViewSearch.setLayoutManager(layoutManager);
        recyclerViewSearch.setAdapter(foodAdapter);
    }

    @Override
    public void setupUI() {
        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                PAGE = 0;
                foodActivityModel.searchFoodFormServerWithPage(query, PAGE);
                PAGE++;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    return false;
                }
                foodAdapter.setObjectArray(foodActivityModel.searchFood(newText));
                foodAdapter.notifyDataSetChanged();
                return false;
            }
        });
        getData();
    }

    public void getData() {
        foodActivityModel = new ListActivityModel(HomeFragment.this);
        foodActivityModel.searchFoodFormServerWithPage("", PAGE);
    }

    public void setUpData(ArrayList<Food> list) {
        foodAdapter.setObjectArray(list);
        foodAdapter.notifyItemInserted(list.size());
        foodAdapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToStoreDetail(Integer idStore) {
        /**Not use Function*/
    }

    @Override
    public void navigateToFoodDetail(Integer idFood) {
        Intent intent = new Intent(view.getContext(), FoodDetailActivity.class);
        intent.putExtra("id", idFood + "");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppResources.getContext().startActivity(intent);
    }
}