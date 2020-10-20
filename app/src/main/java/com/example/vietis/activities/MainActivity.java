package com.example.vietis.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vietis.R;
import com.example.vietis.inteface.IView;

public class MainActivity extends AppCompatActivity implements IView {

    private Button btnFacebook;
    private Button btnGoogle;
    private Button btnLogin;
    private TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mappingUI();
        setupUI();
    }

    @Override
    public void mappingUI() {
        btnFacebook = findViewById(R.id.main_facebook);
        btnGoogle = findViewById(R.id.main_google);
        btnLogin = findViewById(R.id.main_login);
        signUp = findViewById(R.id.main_sign_up);
    }

    @Override
    public void setupUI() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}