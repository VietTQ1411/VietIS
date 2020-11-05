package com.example.vietis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.vietis.Data.entity.User;
import com.example.vietis.Data.view_model.SettingActivityViewModel;
import com.example.vietis.R;
import com.example.vietis.database.Database;

public class EditProfileActivity extends AppCompatActivity implements IView{

    Button btnSave;
    ImageButton ibBack;
    ImageButton ibAvatar;
    ImageButton ibEditPassword;
    ImageButton ibEditName;
    ImageButton ibEditPhoneNumber;
    ImageButton ibEditAddress;
    EditText edtEmail;
    EditText edtPassword;
    EditText edtName;
    EditText edtPhoneNumber;
    EditText edtAddress;
    EditText edtNewPassword;
    EditText edtNewName;
    EditText edtNewPhoneNumber;
    EditText edtNewAddress;
    LinearLayout llPassword;
    LinearLayout llName;
    LinearLayout llPhoneNumber;
    LinearLayout llAddress;
    User user;

    private Boolean isVisible = true;

    private SettingActivityViewModel settingActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mappingUI();
        setupUI();
    }


    @Override
    public void mappingUI() {
        btnSave = findViewById(R.id.btnSave);
        ibBack = findViewById(R.id.ibBack);
        ibAvatar = findViewById(R.id.ibAvatar);
        ibEditPassword = findViewById(R.id.ibEditPassword);
        ibEditName = findViewById(R.id.ibEditName);
        ibEditPhoneNumber = findViewById(R.id.ibEditPhoneNumber);
        ibEditAddress = findViewById(R.id.ibEditAddress);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtName = findViewById(R.id.edtName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtAddress = findViewById(R.id.edtAddress);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtNewName = findViewById(R.id.edtNewName);
        edtNewPhoneNumber = findViewById(R.id.edtNewPhoneNumber);
        edtNewAddress = findViewById(R.id.edtNewAddress);
        settingActivityViewModel = new SettingActivityViewModel();
        llPassword = findViewById(R.id.llPassword);
        llAddress = findViewById(R.id.llAddress);
        llName = findViewById(R.id.llName);
        llPhoneNumber = findViewById(R.id.llPhoneNumber);
    }

    @Override
    public void setupUI(){
        getSettingData();
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileActivity.this, SettingActivity.class));
            }
        });
        ibEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisible = !isVisible;
                if(isVisible){
                    llPassword.setVisibility(View.VISIBLE);
                }else{
                    llPassword.setVisibility(View.GONE);
                }
            }
        });
        ibEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisible = !isVisible;
                if(isVisible){
                    llName.setVisibility(View.VISIBLE);
                }else{
                    llName.setVisibility(View.GONE);
                }
            }
        });
        ibEditPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisible = !isVisible;
                if(isVisible){
                    llPhoneNumber.setVisibility(View.VISIBLE);
                }else{
                    llPhoneNumber.setVisibility(View.GONE);
                }
            }
        });
        ibEditAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisible = !isVisible;
                if(isVisible){
                    llAddress.setVisibility(View.VISIBLE);
                }else{
                    llAddress.setVisibility(View.GONE);
                }
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setPassword(String.valueOf(edtNewPassword.getText()));
                user.setName(String.valueOf(edtNewName.getText()));
                user.setPhoneNumber(String.valueOf(edtNewPhoneNumber.getText()));
                user.setAddress(String.valueOf(edtNewAddress.getText()));
                Toast.makeText(EditProfileActivity.this, "Save thanh cong",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getSettingData(){
        settingActivityViewModel.getSettingUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Database db = Database.getInstance(EditProfileActivity.this);
                Intent intent = new Intent();
                db.userDAO().getSettingUser(intent.getIntExtra("userid",0));
                if(db.userDAO().getSettingUser(intent.getIntExtra("userid",0)) != null){

                    edtEmail.setText(user.getEmail());
                    edtName.setText(user.getName());
                    edtPhoneNumber.setText(user.getPhoneNumber());
                    edtAddress.setText(user.getAddress());

                }
            }
        });
    }
}