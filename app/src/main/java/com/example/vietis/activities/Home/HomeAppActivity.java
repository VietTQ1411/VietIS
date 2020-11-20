package com.example.vietis.activities.Home;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.vietis.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import lombok.NonNull;

/**
 * User Main Screen
 * màn hình chính cho user
 */
public class HomeAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_store, R.id.navigation_order,
                R.id.navigation_setting,R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(HomeAppActivity.this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }

}