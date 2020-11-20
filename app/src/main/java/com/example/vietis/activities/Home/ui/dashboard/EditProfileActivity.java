package com.example.vietis.activities.Home.ui.dashboard;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vietis.Data.inteface.IView;
import com.example.vietis.Data.view_model.SettingActivityViewModel;
import com.example.vietis.R;
import com.example.vietis.Utilities.common.UserApp;
import com.squareup.picasso.Picasso;

public class EditProfileActivity extends AppCompatActivity implements IView {

    private ImageButton ibAvatar;
    private ImageButton ibEditPassword;
    private ImageButton ibEditName;
    private ImageButton ibEditPhoneNumber;
    private ImageButton ibEditAddress;
    private Button btnSavePassword;
    private Button btnSaveName;
    private Button btnSavePhoneNumber;
    private Button btnSaveAddress;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtName;
    private EditText edtPhoneNumber;
    private EditText edtAddress;
    private EditText edtNewPassword;
    private EditText edtNewName;
    private EditText edtNewPhoneNumber;
    private EditText edtNewAddress;
    private EditText edtCurrentPassword;
    private EditText edtConfirmPassword;
    private TextView txtWhy;
    private TextView txtWhy1;
    private LinearLayout llPassword;
    private LinearLayout llName;
    private LinearLayout llPhoneNumber;
    private LinearLayout llAddress;

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
        ibAvatar = findViewById(R.id.ibAvatar);
        ibEditPassword = findViewById(R.id.ibEditPassword);
        ibEditName = findViewById(R.id.ibEditName);
        ibEditPhoneNumber = findViewById(R.id.ibEditPhoneNumber);
        ibEditAddress = findViewById(R.id.ibEditAddress);
        btnSavePassword = findViewById(R.id.btnSavePassword);
        btnSaveName = findViewById(R.id.btnSaveName);
        btnSavePhoneNumber = findViewById(R.id.btnSavePhoneNumber);
        btnSaveAddress = findViewById(R.id.btnSaveAddress);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtName = findViewById(R.id.edtName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtAddress = findViewById(R.id.edtAddress);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtNewName = findViewById(R.id.edtNewName);
        edtNewPhoneNumber = findViewById(R.id.edtNewPhoneNumber);
        edtNewAddress = findViewById(R.id.edtNewAddress);
        edtCurrentPassword = findViewById(R.id.edtCurrentPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        txtWhy = findViewById(R.id.txtWhy);
        txtWhy1 = findViewById(R.id.txtWhy1);
        settingActivityViewModel = new SettingActivityViewModel();
        llPassword = findViewById(R.id.llPassword);
        llAddress = findViewById(R.id.llAddress);
        llName = findViewById(R.id.llName);
        llPhoneNumber = findViewById(R.id.llPhoneNumber);
    }

    @Override
    public void setupUI(){
        getSettingData();
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
        if(!edtNewPassword.getText().equals(edtConfirmPassword.getText())){
            txtWhy1.setText("New Password doesnt match. Please try again");
            txtWhy1.setVisibility(View.VISIBLE);
        }else{
            txtWhy1.setVisibility(View.GONE);
        }
        if(!edtCurrentPassword.getText().equals(UserApp.user.getPassword())){
            txtWhy.setText("Current Password is not correct. Please try again");
            txtWhy.setVisibility(View.VISIBLE);
        }else{
            txtWhy.setVisibility(View.GONE);
        }
        btnSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApp.user.setPassword(String.valueOf(edtNewPassword.getText()));
                edtPassword.setHint(UserApp.user.getPassword());
            }
        });
        btnSaveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApp.user.setName(String.valueOf(edtNewName.getText()));
                edtName.setHint(UserApp.user.getName());
            }
        });
        btnSavePhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApp.user.setPhoneNumber(String.valueOf(edtNewPhoneNumber.getText()));
                edtPhoneNumber.setHint(UserApp.user.getPhoneNumber());
            }
        });
        btnSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApp.user.setAddress(String.valueOf(edtNewAddress.getText()));
                edtAddress.setHint(UserApp.user.getAddress());
            }
        });
    }

    public void getSettingData(){
        if(UserApp.user.getImageURL() != null && !UserApp.user.getImageURL().isEmpty()) {
            Picasso.get().load(UserApp.user.getImageURL())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .resize(100, 100)
                    .centerCrop()
                    .into(ibAvatar);
        }
        edtEmail.setHint(UserApp.user.getEmail());
        edtPassword.setHint(UserApp.user.getHashedPassword());
        edtName.setHint(UserApp.user.getName());
        edtPhoneNumber.setHint(UserApp.user.getPhoneNumber());
        edtAddress.setHint(UserApp.user.getAddress());
    }
}