package com.example.vietis.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.vietis.R;

public class SettingActivity extends AppCompatActivity {

    ImageView imgAvatar;
    TextView txtProfileName;
    TextView txtProfileAccount;
    Button btnEdit;
    Switch switchilly;
    ImageButton ibPrivacy;
    ImageButton ibPolicy;
    ImageButton ibSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mappingUI();
        setupUI();
    }

    private void setupUI() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this,EditProfileActivity.class));
            }
        });
        ibSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, LoginActivity.class));
            }
        });
    }

    private void mappingUI() {
        imgAvatar = findViewById(R.id.imgAvatar);
        txtProfileName = findViewById(R.id.txtProfileName);
        txtProfileAccount = findViewById(R.id.txtProfileAccount);
        btnEdit = findViewById(R.id.btnEdit);
        switchilly = findViewById(R.id.switchilly);
        ibPrivacy = findViewById(R.id.ibPrivacy);
        ibPolicy = findViewById(R.id.ibPolicy);
        ibSignOut = findViewById(R.id.ibSignOut);
    }
}