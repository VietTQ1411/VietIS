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
import com.example.vietis.adapter.SearchAdapter;
import com.example.vietis.entity.Shop;
import com.example.vietis.inteface.IView;
import com.example.vietis.view_model.ListActivityModel;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity implements IView {

    //UI holders
    private ImageButton imageButtonSearch;
    private SearchView searchViewSearch;
    private RecyclerView recyclerViewSearch;

    //RecyclerView components
    private SearchAdapter searchAdapter;

    //View Model
    private ListActivityModel listActivityModel;

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
        searchAdapter = new SearchAdapter(new ArrayList<Shop>());
        listActivityModel = new ViewModelProvider(this).get(ListActivityModel.class);
    }

    @Override
    public void setupUI() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerViewSearch.setLayoutManager(layoutManager);
        listActivityModel.init(true,true);
        listActivityModel.getShopData().observe(this, new Observer<ArrayList<Shop>>() {
            @Override
            public void onChanged(ArrayList<Shop> arrayList) {
                searchAdapter.setShopArray(arrayList);
                searchAdapter.notifyDataSetChanged();
            }
        });
        recyclerViewSearch.setAdapter(searchAdapter);

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
}