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

import com.example.vietis.Data.entity.User;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.SearchAdapter;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.Data.view_model.ListActivityModel;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity implements IView, IListView {

    //UI holders
    private SearchView searchViewSearch;
    private RecyclerView recyclerViewSearch;

    //RecyclerView components
    private SearchAdapter searchAdapter;

    //View Model
    private ListActivityModel listActivityModel;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mappingUI();
        setupUI();
    }

    @Override
    public void mappingUI() {
        user = (User) getIntent().getSerializableExtra("user");

        searchViewSearch = findViewById(R.id.searchViewSearch);
        recyclerViewSearch = findViewById(R.id.recyclerViewSearch);
        searchAdapter = new SearchAdapter(this, new ArrayList<Shop>());
        listActivityModel = new ViewModelProvider(this).get(ListActivityModel.class);
    }

    @Override
    public void setupUI() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerViewSearch.setLayoutManager(layoutManager);
        listActivityModel.init(true, true);
        listActivityModel.getShopData().observe(this, new Observer<ArrayList<Shop>>() {
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