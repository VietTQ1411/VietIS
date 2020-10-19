package com.example.vietis.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.vietis.R;
import com.example.vietis.entity.Food;
import com.example.vietis.inteface.IView;

import java.io.Console;
import java.util.logging.Logger;

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