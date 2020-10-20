package com.example.vietis.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.vietis.R;
import com.example.vietis.inteface.IView;

public class SearchActivity extends AppCompatActivity implements IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mappingUI();
        setupUI();
    }

    @Override
    public void mappingUI() {

    }

    @Override
    public void setupUI() {

    }
}