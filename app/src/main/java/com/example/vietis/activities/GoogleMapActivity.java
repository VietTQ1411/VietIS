package com.example.vietis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.vietis.Fragment.MapsFragment;
import com.example.vietis.R;

public class GoogleMapActivity extends AppCompatActivity {

    private MapsFragment mapsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.mapsFragment = (MapsFragment) fragmentManager.findFragmentById(R.id.fMap);
    }
}