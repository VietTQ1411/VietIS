package com.example.vietis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.vietis.Data.entity.Image;
import com.example.vietis.Data.entity.User;
import com.example.vietis.Data.view_model.RegisterActivityViewModel;
import com.example.vietis.Data.view_model.SettingActivityViewModel;
import com.example.vietis.R;
import com.example.vietis.database.Database;

public class SettingActivity extends AppCompatActivity implements IView{

    ImageView imgAvatar;
    TextView txtProfileName;
    TextView txtProfileAccount;
    TextView txtPrivacy;
    TextView txtPolicy;
    TextView txtAppVersion;
    Button btnEdit;
    Switch switchilly;
    ImageButton ibPrivacy;
    ImageButton ibPolicy;
    ImageButton ibAppVersion;
    ImageButton ibSignOut;
    private SettingActivityViewModel settingActivityViewModel;
    private boolean isVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mappingUI();
        setupUI();
    }


    @Override
    public void mappingUI() {
        imgAvatar = findViewById(R.id.imgAvatar);
        txtProfileName = findViewById(R.id.txtProfileName);
        txtProfileAccount = findViewById(R.id.txtProfileAccount);
        txtPrivacy = findViewById(R.id.txtPrivacy);
        txtPolicy = findViewById(R.id.txtPolicy);
        txtAppVersion = findViewById(R.id.txtAppVersion);
        btnEdit = findViewById(R.id.btnEdit);
        switchilly = findViewById(R.id.switchilly);
        ibPrivacy = findViewById(R.id.ibPrivacy);
        ibPolicy = findViewById(R.id.ibPolicy);
        ibAppVersion = findViewById(R.id.ibAppVersion);
        ibSignOut = findViewById(R.id.ibSignOut);
        settingActivityViewModel = new SettingActivityViewModel();
    }

    @Override
    public void setupUI() {
        getSettingData();
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this,EditProfileActivity.class));
            }
        });
        ibSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, LoginActivity.class));
            }
        });

        ibPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisible = !isVisible;
                if(isVisible){
                    txtPrivacy.setVisibility(View.VISIBLE);
                    ibPrivacy.setImageResource(R.drawable.ic_right);
                }else{
                    ibPrivacy.setImageResource(R.drawable.ic_foward);
                    txtPrivacy.setVisibility(View.GONE);
                }
            }
        });

        ibPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisible = !isVisible;
                if(isVisible){
                    txtPolicy.setVisibility(View.VISIBLE);
                    ibPolicy.setImageResource(R.drawable.ic_right);
                }else{
                    ibPolicy.setImageResource(R.drawable.ic_foward);
                    txtPolicy.setVisibility(View.GONE);
                }
            }
        });

        ibAppVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisible = !isVisible;
                if(isVisible){
                    txtAppVersion.setVisibility(View.VISIBLE);
                    ibAppVersion.setImageResource(R.drawable.ic_right);
                }else{
                    ibAppVersion.setImageResource(R.drawable.ic_foward);
                    txtAppVersion.setVisibility(View.GONE);
                }
            }
        });
    }

    public void getSettingData(){
        settingActivityViewModel.getSettingUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Database db = Database.getInstance(SettingActivity.this);
                Intent intent = new Intent();
                db.userDAO().getSettingUser(intent.getIntExtra("userid",0));
                if(db.userDAO().getSettingUser(intent.getIntExtra("userid",0)) != null){
                    txtProfileName.setText(user.getName());
                    txtProfileAccount.setText(user.getEmail());
                    imgAvatar.setImageResource(user.getImageId());
                }
            }
        });
    }
}