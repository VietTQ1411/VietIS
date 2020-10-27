package com.example.vietis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vietis.inteface.IView;

public class FoodListActivity extends AppCompatActivity implements IView {

    private ImageButton imageBtnFoodList;
    private TextView textViewFoodField;
    private RecyclerView recyclerViewFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        mappingUI();
        setupUI();
    }

    @Override
    public void mappingUI() {
        imageBtnFoodList = findViewById(R.id.imageBtnFoodList);
        textViewFoodField = findViewById(R.id.textViewFoodField);
        recyclerViewFoodList = findViewById(R.id.recyclerViewFoodList);
    }

    @Override
    public void setupUI() {

    }
}