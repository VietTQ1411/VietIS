package com.example.vietis.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.vietis.R;
import com.example.vietis.database.Database;
import com.example.vietis.entity.User;
import com.example.vietis.inteface.IView;
import com.example.vietis.view_model.RegisterActivityViewModel;

public class RegisterActivity extends AppCompatActivity implements IView {
    private EditText txtEmail;
    private EditText txtName;
    private EditText txtPassword;
    private Button btnRegister;
    private RegisterActivityViewModel registerActivityViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mappingUI();
        setupUI();
    }

    @Override
    public void mappingUI() {
        txtEmail = findViewById(R.id.register_email);
        txtName = findViewById(R.id.register_username);
        txtPassword = findViewById(R.id.register_password);
        btnRegister = findViewById(R.id.btnRegister);
    }
    private String getEmail(){return txtEmail.getText().toString().trim();}
    private String getPassword(){return txtPassword.getText().toString().trim();}
    private String getName(){return txtName.getText().toString().trim();}
    public void register(){
        registerActivityViewModel.register(getEmail(),getPassword(),getName(),"1");
        registerActivityViewModel.getUser().observe(this,
                new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        Database db =Database.getInstance(RegisterActivity.this);
//                        if(db.userDAO().getLoginUser(user)!= null){
//                            db.userDAO().insertUser(user);
//                        }
                    }
                });
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
