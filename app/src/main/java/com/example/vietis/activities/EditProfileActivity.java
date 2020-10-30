package com.example.vietis.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.vietis.R;

public class EditProfileActivity extends AppCompatActivity {

    Button btnSave;
    ImageButton ibBack;
    ImageButton ibAvatar;
    EditText edtEmail;
    EditText edtPassword;
    EditText edtName;
    EditText edtPhoneNumber;
    EditText edtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mappingUI();
        setupUI();
    }

    private void setupUI() {
        btnSave = findViewById(R.id.btnSave);
        ibBack = findViewById(R.id.ibBack);
        ibAvatar = findViewById(R.id.ibAvatar);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtName = findViewById(R.id.edtName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtAddress = findViewById(R.id.edtAddress);
    }

    private void mappingUI() {
    }
}