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
import com.example.vietis.Data.view_model.ListActivityModel;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.SearchAdapter;
import com.example.vietis.activities.Home.ui.store.StoreDetailActivity;
import com.example.vietis.activities.IListView;
import com.example.vietis.activities.IView;
import com.example.vietis.activities.MainActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements IView, IListView {

    //UI holders
    private View root;
    private SearchView searchViewSearch;
    private View view;
    private RecyclerView recyclerViewSearch;

    //RecyclerView components
    private SearchAdapter<Food> foodAdapter;
    //View Model
    private ListActivityModel foodActivityModel;
    private int PAGE = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        view = root;
        mappingUI();
        Log.d("Home", "Start");
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        setupUI();
    }

    @Override
    public void onStop() {
        super.onStop();
        PAGE = 0;
        foodActivityModel.clearDataFood();
    }

    @Override
    public void mappingUI() {
        searchViewSearch = view.findViewById(R.id.searchFoodViewSearch);
        recyclerViewSearch = view.findViewById(R.id.recyclerViewFoodSearch);
        foodAdapter = new SearchAdapter(this, new ArrayList<Food>(), Food.class);
        foodActivityModel = new ViewModelProvider(this).get(ListActivityModel.class);
    }

    @Override
    public void setupUI() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerViewSearch.setLayoutManager(layoutManager);
        foodActivityModel.searchFoodFormServerWithPage("", PAGE);
        foodActivityModel.getFoodData().observe(this, new Observer<ArrayList<Food>>() {
            @Override
            public void onChanged(ArrayList<Food> arrayList) {
                foodAdapter.setObjectArray(arrayList);
                foodAdapter.notifyDataSetChanged();
                recyclerViewSearch.setAdapter(foodAdapter);
            }
        });


        //SearchView action
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
                    foodAdapter.setObjectArray(foodActivityModel.getFoodData().getValue());
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
    }

    @Override
    public void navigateToStoreDetail(Integer idStore) {
        /**Not use Function*/
    }

    @Override
    public void navigateToFoodDetail(Integer idFood) {
        Intent intent = new Intent(view.getContext(), FoodDetailActivity.class);
        intent.putExtra("id", idFood + "");
        startActivity(intent);
    }
}