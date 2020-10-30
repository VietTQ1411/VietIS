package com.example.vietis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.vietis.R;
import com.example.vietis.UI.adapter.ShopAdapter;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.Data.view_model.ListActivityModel;

import java.util.ArrayList;

public class ShopListActivity extends AppCompatActivity implements IView {

    //UI holders
    private ImageButton imageBtnShopList;
    private TextView textViewShopField;
    private RecyclerView recyclerViewShopList;

    //RecyclerView components
    private String foodField;
    private ShopAdapter shopAdapter;

    //View Model
    private ListActivityModel listActivityModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        mappingUI();
        setupUI();
    }

    @Override
    public void mappingUI() {
        imageBtnShopList = findViewById(R.id.imageBtnFoodList);
        textViewShopField = findViewById(R.id.textViewFoodField);
        recyclerViewShopList = findViewById(R.id.recyclerViewFoodList);
        shopAdapter = new ShopAdapter(new ArrayList<Shop>());
        listActivityModel = new ViewModelProvider(this).get(ListActivityModel.class);
    }

    @Override
    public void setupUI() {
        Intent intent = getIntent();
        this.foodField = intent.getStringExtra("food_field");
        textViewShopField.setText(foodField == null ? "Food" : foodField);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerViewShopList.setLayoutManager(layoutManager);
        listActivityModel.init(false, true);
        listActivityModel.getShopData().observe(this, new Observer<ArrayList<Shop>>() {
            @Override
            public void onChanged(ArrayList<Shop> arrayList) {
                shopAdapter.setShopArray(arrayList);
                shopAdapter.notifyDataSetChanged();
            }
        });
        recyclerViewShopList.setAdapter(shopAdapter);
        listActivityModel.searchShopFromFakeData("Bun");

        //ImageButton action
        imageBtnShopList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopListActivity.this.finish();
            }
        });
    }
}