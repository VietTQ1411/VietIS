package com.example.vietis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.vietis.Data.IRepository.repository.Config;
import com.example.vietis.Data.entity.User;
import com.example.vietis.R;

public class Home2Activity extends AppCompatActivity implements IView {

    //UI holders
    private SearchView searchViewSearch;

    //RecyclerView components

    //View Model
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        mappingUI();
        setupUI();
    }

    public String getUserTokenKey(){
        return this.user.getTokenKey();
    }


    @Override
    public void mappingUI() {
        user = (User) getIntent().getSerializableExtra("user");
        searchViewSearch = findViewById(R.id.searchViewHome);
    }

    @Override
    public void setupUI() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home2Activity.this, SearchActivity.class);
                intent.putExtra("user",user);
                Home2Activity.this.startActivity(intent);
            }
        };
        Config.setChildViewOnClickListener(searchViewSearch,listener);
    }
}