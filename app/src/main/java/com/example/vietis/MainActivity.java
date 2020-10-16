package com.example.vietis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public String toString() {
        return "MainActivity{}";
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public String toString2() {
        return "MainActivity{}";

    }
    public String toString4() {
        return "MainActivity{}";
    }
}