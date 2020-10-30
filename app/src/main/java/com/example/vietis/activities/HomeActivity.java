package com.example.vietis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.vietis.Data.inteface.repository.Config;
import com.example.vietis.R;

public class HomeActivity extends AppCompatActivity implements IView {

    //UI holders
    private SearchView searchViewSearch;

    //RecyclerView components

    //View Model

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mappingUI();
        setupUI();
    }


    @Override
    public void mappingUI() {
        searchViewSearch = findViewById(R.id.searchViewHome);
    }

    @Override
    public void setupUI() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                HomeActivity.this.startActivity(intent);
            }
        };
        setSearchViewOnClickListener(searchViewSearch, listener);
        Config.setChildViewOnClickListener(searchViewSearch,listener);
    }

    private void setSearchViewOnClickListener(View v, View.OnClickListener listener) {
        if (v instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) v;
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = group.getChildAt(i);
                if (child instanceof LinearLayout || child instanceof RelativeLayout) {
                    setSearchViewOnClickListener(child, listener);
                }

                if (child instanceof TextView) {
                    TextView text = (TextView) child;
                    text.setFocusable(false);
                }
                child.setOnClickListener(listener);
            }
        }
    }
}