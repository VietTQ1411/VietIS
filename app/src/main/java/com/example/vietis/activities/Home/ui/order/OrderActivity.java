package com.example.vietis.activities.Home.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Food;
import com.example.vietis.Data.entity.Order;
import com.example.vietis.Data.inteface.IView;
import com.example.vietis.Data.view_model.FoodDetailViewModel;
import com.example.vietis.Data.view_model.ListActivityModel;
import com.example.vietis.Data.view_model.OrderActivityModel;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.CommentAdapter;
import com.example.vietis.Utilities.common.UserApp;
import com.example.vietis.activities.Home.HomeAppActivity;
import com.example.vietis.activities.Home.ui.home.HomeFragment;
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

    private LinearLayout txtIconChange;
    private EditText editTextTextPersonName;
    private boolean flag = false;

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
        txtIconChange = findViewById(R.id.txtIconChange);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
    }
private int imageId = 1;
    @Override
    public void setupUI() {
        Intent parent = getIntent();
        Bundle b = parent.getExtras();
        if (b != null) {
            txtOrderFood.setText(b.getString("name"));
            imageId = b.getInt("imageId");
            price = b.getFloat("price");
            Picasso.get().load(b.getString("img"))
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .resize(350, 350)
                    .centerCrop()
                    .into(img);
        }
        txtOrderAddress.setText("Ship Address: " + UserApp.user.getAddress());
        txtOrderPrice.setText("Total: $" + quantity * price);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtOrderQuantityChange.setText(++quantity + "");
                txtOrderPrice.setText("Total: $" + quantity * price);
            }
        });
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 0) {
                    txtOrderQuantityChange.setText(--quantity + "");
                }

                txtOrderPrice.setText("Total: $" + quantity * price);
            }
        });
        txtIconChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = !flag;
                editTextTextPersonName.setVisibility(flag ? View.VISIBLE : View.GONE);
            }
        });
        editTextTextPersonName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtOrderAddress.setText("Ship to: " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        editTextTextPersonName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    editTextTextPersonName.setVisibility(View.GONE);
                }
            }
        });
        btnSummit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order o = Order.builder()
                        .description(txtOrderAddress.getText().toString()
                                .replace("Ship to: ",""))
                        .imageURL(imageId+"")
                        .total(quantity*price)
                        .quantity(quantity)
                        .build();
                OrderActivityModel orderActivityModel = new OrderActivityModel(OrderActivity.this);
                orderActivityModel.addData(o);
            }
        });
    }

    public void setupData() {
    }

    public void Out() {
      this.finish();
    }
}