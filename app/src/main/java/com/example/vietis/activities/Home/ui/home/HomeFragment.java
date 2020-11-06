package com.example.vietis.activities.Home.ui.home;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.vietis.Data.IRepository.repository.Config;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.Data.entity.User;
import com.example.vietis.Data.view_model.ListActivityModel;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.SearchAdapter;
import com.example.vietis.activities.IListView;
import com.example.vietis.activities.IView;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements IView, IListView {


    //UI holders
    private View root;
    private SearchView searchViewSearch;
    private RecyclerView recyclerViewSearch;

    //RecyclerView components
    private SearchAdapter searchAdapter;

    //View Model
    private ListActivityModel listActivityModel;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_home, container, false);
        mappingUI();
        setupUI();
        return root;
    }

    @Override
    public void mappingUI() {
        searchViewSearch = root.findViewById(R.id.searchViewSearch);
        recyclerViewSearch = root.findViewById(R.id.recyclerViewSearch);
        searchAdapter = new SearchAdapter(this, new ArrayList<Shop>());
        listActivityModel = new ViewModelProvider(this).get(ListActivityModel.class);
    }

    @Override
    public void setupUI() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerViewSearch.setLayoutManager(layoutManager);
        listActivityModel.init(false, true);
        listActivityModel.getShopData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Shop>>() {
            @Override
            public void onChanged(ArrayList<Shop> arrayList) {
                searchAdapter.setShopArray(arrayList);
                searchAdapter.notifyDataSetChanged();
            }
        });
        recyclerViewSearch.setAdapter(searchAdapter);

        //SearchView action
        searchViewSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                listActivityModel.searchShopFromFakeData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
                    this.onQueryTextSubmit("");
                    return false;
                }
                listActivityModel.searchShopFromFakeData(newText);
                return false;
            }
        });

    }

    @Override
    public void navigateToShopDetail(Integer position) {

    }

    @Override
    public void navigateToFoodDetail() {

    }
}