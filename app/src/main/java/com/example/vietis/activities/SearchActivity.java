package com.example.vietis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class SearchActivity extends AppCompatActivity implements IView {

    //UI holders
    private SearchView searchViewSearch;
    private RecyclerView recyclerViewSearch;

    //RecyclerView components
    private ShopAdapter shopAdapter;

    //View Model
    private SearchActivityModel searchActivityModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mappingUI();
        setupUI();
    }

    @Override
    public void mappingUI() {
        searchViewSearch = findViewById(R.id.searchViewSearch);
        recyclerViewSearch = findViewById(R.id.recyclerViewSearch);
        shopAdapter = new ShopAdapter(new ArrayList<Shop>());
        searchActivityModel = new ViewModelProvider(this).get(SearchActivityModel.class);
    }

    @Override
    public void setupUI() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerViewSearch.setLayoutManager(layoutManager);
        searchActivityModel.init();
        searchActivityModel.getShopData().observe(this, new Observer<ArrayList<Shop>>() {
            @Override
            public void onChanged(ArrayList<Shop> arrayList) {
                shopAdapter = new ShopAdapter(arrayList);
                shopAdapter.notifyDataSetChanged();
                recyclerViewSearch.setAdapter(shopAdapter);
            }
        });
        //SearchView action
        searchViewSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
}