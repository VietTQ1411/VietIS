package com.example.vietis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.vietis.R;
import com.example.vietis.adapter.ShopAdapter;
import com.example.vietis.entity.Shop;
import com.example.vietis.inteface.IView;
import com.example.vietis.view_model.SearchActivityModel;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity implements IView {

    //UI holders
    private ImageButton imageButtonSearch;
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
        imageButtonSearch = findViewById(R.id.imageBtnSearch);
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
                shopAdapter.setShopArray(arrayList);
                shopAdapter.notifyDataSetChanged();
            }
        });
        recyclerViewSearch.setAdapter(shopAdapter);
        //ImageButton action
        imageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.this.finish();
            }
        });

        //SearchView action
        searchViewSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchActivityModel.searchShopFromFakeData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
                    this.onQueryTextSubmit("");
                    return false;
                }
                searchActivityModel.searchShopFromFakeData(newText);
                return false;
            }
        });

    }
}