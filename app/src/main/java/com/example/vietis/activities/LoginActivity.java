package com.example.vietis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.vietis.R;
import com.example.vietis.Data.entity.User;
import com.example.vietis.Data.view_model.LoginActivityViewModel;

public class LoginActivity extends AppCompatActivity implements IView {
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView forgotPassword;
    private LoginActivityViewModel loginActivityViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mappingUI();
        setupUI();
    }

    @Override
    public void mappingUI() {
        txtEmail = findViewById(R.id.login_username);
        txtEmail.setText("vettq@gmail.com");
        txtPassword = findViewById(R.id.login_password);
        txtPassword.setText("123456");
        btnLogin = findViewById(R.id.button_login);
        forgotPassword = findViewById(R.id.forgot_password);
        loginActivityViewModel = new LoginActivityViewModel();
    }

    @Override
    public void setupUI() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }


    public void navigateToHomeActivity(User user){
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        intent.putExtra("userid", user.getId());
        this.startActivity(intent);
    }

    public String getEmail() {
        return txtEmail.getText().toString().trim();
    }

    public String getPassword() {
        return txtPassword.getText().toString().trim();
    }

    private void login() {
        loginActivityViewModel.login(getEmail(), getPassword());
        loginActivityViewModel.getUser().observe(this,
                new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        if(user!= null){
                            LoginActivity.this.navigateToHomeActivity(user);
                        if (user != null) {
                            LoginActivity.this.navigateToHomeActivity(user);
                        }
                    }
                }
    });
}
}