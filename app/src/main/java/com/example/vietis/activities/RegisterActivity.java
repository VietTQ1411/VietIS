package com.example.vietis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.vietis.Data.entity.User;
import com.example.vietis.Data.inteface.IView;
import com.example.vietis.Data.view_model.RegisterActivityViewModel;
import com.example.vietis.R;

public class RegisterActivity extends AppCompatActivity implements IView {

    private RegisterActivityViewModel registerActivityViewModel;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtConfirmPassword;
    private LinearLayout btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mappingUI();
        setupUI();
    }

    @Override
    public void mappingUI() {
        txtEmail = findViewById(R.id.edtSignupEmail);
        txtPassword=findViewById(R.id.edtSignupPassword);
        txtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnRegister = findViewById(R.id.llSignUp);
        registerActivityViewModel = new RegisterActivityViewModel();
    }

    private String getEmail() {
        return txtEmail.getText().toString().trim();
    }

    private String getPassword() {
        return txtPassword.getText().toString().trim();
    }


    public void register() {
        if(txtPassword.getText().toString().equals(txtConfirmPassword.getText().toString()))
        registerActivityViewModel.register(getEmail(), getPassword(), 1 + "");
        else{
            Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setupUI() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
}
