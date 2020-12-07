package com.example.vietis.activities.Home.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vietis.Data.entity.Food;
import com.example.vietis.Data.view_model.FoodDetailViewModel;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.CommentAdapter;
import com.example.vietis.Data.inteface.IView;
import com.example.vietis.activities.Home.ui.order.OrderActivity;
import com.squareup.picasso.Picasso;

public class FoodDetailActivity extends AppCompatActivity implements IView {

    private ImageView imageStoreDetailIcon;
    private TextView txtDetailFoodName;
    private TextView txtDetailFoodAddress;
    private TextView txtDetailFoodCategory;
    private TextView txtDetailFoodPrice;
    private TextView txtDetailFoodDescription;

    private Button btnOrder;
    private CommentAdapter commentAdapter;
    private RecyclerView recyclerViewComment;
    private FoodDetailViewModel foodDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        mappingUI();
        setupUI();
    }

    @Override
    public void mappingUI() {
        imageStoreDetailIcon = findViewById(R.id.imageStoreDetailIcon);
        txtDetailFoodName = findViewById(R.id.txtDetailFoodName);
        txtDetailFoodAddress = findViewById(R.id.txtDetailFoodAddress);
        txtDetailFoodCategory = findViewById(R.id.txtDetailFoodCategory);
        txtDetailFoodPrice = findViewById(R.id.txtDetailFoodPrice);
        txtDetailFoodDescription = findViewById(R.id.txtDetailFoodDescription);

        btnOrder = findViewById(R.id.btnOrder);

        foodDetailViewModel = new FoodDetailViewModel(this);
    }

    @Override
    public void setupUI() {
        Intent parent = getIntent();
        Bundle b = parent.getExtras();
        String id = null;
        if (b != null) {
            id = b.getString("id");
        }


        foodDetailViewModel = new FoodDetailViewModel(this);
        foodDetailViewModel.getFoodDetail(id);
    }

    public void setupData(Food food) {
        Picasso.get().load(food.getImageURL())
                .placeholder(R.drawable.ic_launcher_foreground)
                .resize(250, 250)
                .centerCrop()
                .into(imageStoreDetailIcon);
        txtDetailFoodName.setText(food.getName());
        txtDetailFoodAddress.setText("Address: " + food.getAddress());
        txtDetailFoodCategory.setText("Category: " + food.getCategory());
        txtDetailFoodPrice.setText("Price:" + food.getPrice());
        txtDetailFoodDescription.setText("Description: " + food.getName());
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent order = new Intent(FoodDetailActivity.this, OrderActivity.class);
                order.putExtra("price",food.getPrice());
                order.putExtra("name",food.getName());
                order.putExtra("imageId",food.getImageID());
                order.putExtra("img",food.getImageURL());
                FoodDetailActivity.this.startActivity(order);
            }
        });
    }
}