package com.example.vietis.activities.Home.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Food;
import com.example.vietis.Data.inteface.IView;
import com.example.vietis.Data.view_model.FoodDetailViewModel;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.CommentAdapter;
import com.example.vietis.Utilities.common.UserApp;
import com.squareup.picasso.Picasso;

public class OrderActivity extends AppCompatActivity implements IView {

    private ImageView img;
    private TextView txtOrderFood;
    private TextView txtOrderAddress;
    private TextView txtOrderPrice;
    private ImageButton btnDown;
    private TextView txtOrderQuantityChange;
    private ImageButton btnUp;
    private int quantity = 1;
    private double price = 0;
    private Button btnSummit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_layout_2);
        mappingUI();
        setupUI();
    }

    @Override
    public void mappingUI() {
        img = findViewById(R.id.img);
        txtOrderFood = findViewById(R.id.txtOrderFood);
        txtOrderAddress = findViewById(R.id.txtOrderAddress);
        txtOrderPrice = findViewById(R.id.txtOrderPrice);
        btnDown = findViewById(R.id.btnDown);
        txtOrderQuantityChange = findViewById(R.id.txtOrderQuantityChange);
        btnUp = findViewById(R.id.btnUp);
        btnSummit = findViewById(R.id.btnSummit);
    }

    @Override
    public void setupUI() {
        Intent parent = getIntent();
        Bundle b = parent.getExtras();
        if (b != null) {
            txtOrderFood.setText(b.getString("name"));
            price = b.getFloat("price");
            Picasso.get().load(b.getString("img"))
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .resize(350, 350)
                    .centerCrop()
                    .into(img);
        }
        txtOrderAddress.setText("Ship Address: "+ UserApp.user.getAddress());
        txtOrderPrice.setText("Total: $" + quantity * price);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtOrderQuantityChange.setText(++quantity+"");
                txtOrderPrice.setText("Total: $" + quantity * price);
            }
        });
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity>0){
                    txtOrderQuantityChange.setText(--quantity+"");
                }

                txtOrderPrice.setText("Total: $" + quantity * price);
            }
        });
        btnSummit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //summit
            }
        });
    }

    public void setupData() {
    }
}