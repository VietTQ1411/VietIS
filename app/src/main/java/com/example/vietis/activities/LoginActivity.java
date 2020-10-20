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
import androidx.room.Room;

import com.example.vietis.R;
import com.example.vietis.database.Database;
import com.example.vietis.entity.User;
import com.example.vietis.inteface.IView;
import com.example.vietis.view_model.LoginActivityViewModel;

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
        txtPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.button_login);
        forgotPassword = findViewById(R.id.forgot_password);
    }

    public void navigateToOtherActivity(){
        Intent intent = new Intent(LoginActivity.this,SearchActivity.class);
        this.startActivity(intent);

    }
    public String getEmail(){return txtEmail.getText().toString().trim();}
    public String getPassword(){return txtPassword.getText().toString().trim();}
    private void login(){
        loginActivityViewModel.login(getEmail(),getPassword());
        loginActivityViewModel.getUser().observe(this,
                new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        if(user.getName().isEmpty()) return;
                        Database db = Database.getInstance(LoginActivity.this);
                        if(db.userDAO().getLoginUser(user)!= null){
                            LoginActivity.this.navigateToOtherActivity();
                        }
                    }

                });
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
}
