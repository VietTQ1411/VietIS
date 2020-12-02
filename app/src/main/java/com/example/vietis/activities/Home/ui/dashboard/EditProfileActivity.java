package com.example.vietis.activities.Home.ui.dashboard;

import android.os.Bundle;
import android.view.View;
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
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtName;
    private EditText edtPhoneNumber;
    private EditText edtNewPassword;
    private EditText edtNewName;
    private EditText edtNewPhoneNumber;
    private EditText edtCurrentPassword;
    private EditText edtConfirmPassword;
    private TextView txtWhy;
    private TextView txtWhy1;
    private LinearLayout llPassword;
    private LinearLayout llName;
    private LinearLayout llPhoneNumber;

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
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtName = findViewById(R.id.edtName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtNewName = findViewById(R.id.edtNewName);
        edtNewPhoneNumber = findViewById(R.id.edtNewPhoneNumber);
        edtCurrentPassword = findViewById(R.id.edtCurrentPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        txtWhy = findViewById(R.id.txtWhy);
        txtWhy1 = findViewById(R.id.txtWhy1);
        settingActivityViewModel = new SettingActivityViewModel();
        llPassword = findViewById(R.id.llPassword);
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
    }
}