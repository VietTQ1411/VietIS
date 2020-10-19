package com.example.vietis.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vietis.R;
import com.example.vietis.inteface.IView;

public class LoginActivity extends AppCompatActivity implements IView {
    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView forgotPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mappingUI();
        setupUI();
    }

    @Override
    public void mappingUI() {
        txtUsername = findViewById(R.id.login_username);
        txtPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.button_login);
        forgotPassword = findViewById(R.id.forgot_password);
    }

    @Override
    public void setupUI() {

    }
}
