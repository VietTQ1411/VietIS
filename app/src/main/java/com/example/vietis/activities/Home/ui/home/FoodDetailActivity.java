package com.example.vietis.activities.Home.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vietis.Data.entity.Comment;
import com.example.vietis.Data.view_model.FoodDetailViewModel;
import com.example.vietis.Data.view_model.ListActivityModel;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.CommentAdapter;
import com.example.vietis.activities.IView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FoodDetailActivity extends AppCompatActivity implements IView {

    private ImageView imageViewFoodImage;
    private TextView textViewFoodName;
    private TextView textViewFoodDescription;
    private TextView textViewFoodPrice;

    private CommentAdapter commentAdapter;
    private RecyclerView recyclerViewComment;
    private FoodDetailViewModel foodDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
    }

    @Override
    public void mappingUI() {
        imageViewFoodImage = findViewById(R.id.imageViewFoodImage);
        textViewFoodName = findViewById(R.id.textViewFoodName);
        textViewFoodDescription = findViewById(R.id.textViewFoodDescription);
        textViewFoodPrice = findViewById(R.id.textViewFoodPrice);
        commentAdapter = new CommentAdapter(new ArrayList<Comment>());
        foodDetailViewModel = new ViewModelProvider(this).get(FoodDetailViewModel.class);
    }

    @Override
    public void setupUI() {
        Intent intent = getIntent();
        int foodID = intent.getIntExtra("foodID",0);

    }
}