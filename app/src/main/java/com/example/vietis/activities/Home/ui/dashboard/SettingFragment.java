package com.example.vietis.activities.Home.ui.dashboard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.vietis.Data.entity.User;
import com.example.vietis.Data.view_model.SettingActivityViewModel;
import com.example.vietis.R;
import com.example.vietis.activities.IView;
import com.example.vietis.activities.LoginActivity;
import com.example.vietis.database.Database;

import static androidx.core.app.ActivityCompat.finishAffinity;

public class SettingFragment extends Fragment implements IView {

    private ImageView imgAvatar;
    private TextView txtProfileName;
    private TextView txtProfileAccount;
    private TextView txtPrivacy;
    private TextView txtPolicy;
    private TextView txtAppVersion;
    private  Button btnEdit;
    private  Switch switchilly;
    private ImageButton ibPrivacy;
    private  ImageButton ibPolicy;
    private  ImageButton ibAppVersion;
    private  ImageButton ibSignOut;
    private LinearLayout isNotification;
    private LinearLayout isPrivacy;
    private LinearLayout isPolicy;
    private LinearLayout isApp;
    private LinearLayout isOut;
    private SettingActivityViewModel settingActivityViewModel;
    private boolean isVisiblePrivacy = false;
    private boolean isVisiblePolicy = false;
    private boolean isVisibleApp = false;

    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        view = root;
        mappingUI();
        setupUI();
        setData();
        return root;
    }

    @Override
    public void mappingUI() {
        imgAvatar = view.findViewById(R.id.imgAvatar);
        txtProfileName = view.findViewById(R.id.txtProfileName);
        txtProfileAccount = view.findViewById(R.id.txtProfileAccount);
        txtPrivacy = view.findViewById(R.id.txtPrivacy);
        txtPolicy = view.findViewById(R.id.txtPolicy);
        txtAppVersion = view.findViewById(R.id.txtAppVersion);
        btnEdit = view.findViewById(R.id.btnEdit);
        switchilly = view.findViewById(R.id.switchilly);
        ibPrivacy = view.findViewById(R.id.ibPrivacy);
        ibPolicy = view.findViewById(R.id.ibPolicy);
        ibAppVersion = view.findViewById(R.id.ibAppVersion);
        ibSignOut = view.findViewById(R.id.ibSignOut);
        isNotification = view.findViewById(R.id.isNotification);
        isPrivacy = view.findViewById(R.id.isPrivacy);
        isPolicy = view.findViewById(R.id.isPolicy);
        isApp = view.findViewById(R.id.isApp);
        isOut = view.findViewById(R.id.isOut);
        settingActivityViewModel = new SettingActivityViewModel();
    }

    @Override
    public void setupUI() {
        getSettingData();
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EditProfileActivity.class));
            }
        });
        isOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        isPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisiblePrivacy = !isVisiblePrivacy;
                if (isVisiblePrivacy) {
                    txtPrivacy.setVisibility(View.VISIBLE);
                    ibPrivacy.setImageResource(R.drawable.ic_foward);
                } else {
                    ibPrivacy.setImageResource(R.drawable.ic_right);
                    txtPrivacy.setVisibility(View.GONE);
                }
            }
        });

        isPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisiblePolicy = !isVisiblePolicy;
                if (isVisiblePolicy) {
                    txtPolicy.setVisibility(View.VISIBLE);
                    ibPolicy.setImageResource(R.drawable.ic_foward);
                } else {
                    ibPolicy.setImageResource(R.drawable.ic_right);
                    txtPolicy.setVisibility(View.GONE);
                }
            }
        });

        isApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisibleApp = !isVisibleApp;
                if (isVisibleApp) {
                    txtAppVersion.setVisibility(View.VISIBLE);
                    ibAppVersion.setImageResource(R.drawable.ic_foward);
                } else {
                    ibAppVersion.setImageResource(R.drawable.ic_right);
                    txtAppVersion.setVisibility(View.GONE);
                }
            }
        });
    }


    public void getSettingData(){
        settingActivityViewModel.getSettingUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Database db = Database.getInstance(getContext());
                Intent intent = new Intent();
                db.userDAO().getSettingUser(intent.getIntExtra("userid",user.getId()));
                if(db.userDAO().getSettingUser(intent.getIntExtra("userid",user.getId())) != null){
                    txtProfileName.setText(user.getName());
                    txtProfileAccount.setText(user.getEmail());
                    imgAvatar.setImageResource(user.getImageId());
                }
            }
        });
    }

    public void setData(){
        txtAppVersion.setText("Version: 1.0");
        txtPolicy.setText("Developers of Fuddy apps:\n + Trần Quang Việt\n + ...");
        txtPrivacy.setText("Do not irresponsibly cancel your order because it affects the others behind you");
    }
}