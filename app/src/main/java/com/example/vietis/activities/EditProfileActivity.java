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

import com.example.vietis.Data.entity.User;
import com.example.vietis.Data.view_model.SettingActivityViewModel;
import com.example.vietis.R;
import com.example.vietis.database.Database;

public class EditProfileActivity extends AppCompatActivity {

    Button btnSave;
    ImageButton ibBack;
    ImageButton ibAvatar;
    ImageButton ibEditPassword;
    EditText edtEmail;
    EditText edtPassword;
    EditText edtName;
    EditText edtPhoneNumber;
    EditText edtAddress;
    LinearLayout llPassword;

    private Boolean isVisible = true;

    private SettingActivityViewModel settingActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mappingUI();
        setupUI();
    }

    private void setupUI() {
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
    }

    private void mappingUI() {
        btnSave = findViewById(R.id.btnSave);
        ibBack = findViewById(R.id.ibBack);
        ibAvatar = findViewById(R.id.ibAvatar);
        ibEditPassword = findViewById(R.id.ibEditPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtName = findViewById(R.id.edtName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtAddress = findViewById(R.id.edtAddress);
        settingActivityViewModel = new SettingActivityViewModel();
        llPassword = findViewById(R.id.llPassword);
    }

    public void getSettingData(){
        settingActivityViewModel.getSettingUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Database db = Database.getInstance(EditProfileActivity.this);
                Intent intent = new Intent();
                db.userDAO().getSettingUser(intent.getIntExtra("userid",0));
                if(db.userDAO().getSettingUser(intent.getIntExtra("userid",0)) != null){
                    edtEmail.setHint(user.getEmail());
                    edtPassword.setHint(user.getHashedPassword());
                    edtName.setHint(user.getName());
                    edtPhoneNumber.setHint(user.getPhoneNumber());
                    edtAddress.setHint(user.getAddress());
                }
            }
        });
    }
}