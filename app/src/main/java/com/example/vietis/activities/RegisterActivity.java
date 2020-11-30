package com.example.vietis.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vietis.Data.inteface.IView;
import com.example.vietis.Data.view_model.RegisterActivityViewModel;
import com.example.vietis.R;

public class RegisterActivity extends AppCompatActivity implements IView {

    private RegisterActivityViewModel registerActivityViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //mappingUI();
        //setupUI();
    }

    @Override
    public void mappingUI() {
        //registerActivityViewModel = new RegisterActivityViewModel();
    }

    /*private String getEmail() {
        return txtEmail.getText().toString().trim();
    }

    private String getPassword() {
        return txtPassword.getText().toString().trim();
    }

    private String getName() {
        return txtName.getText().toString().trim();
    }

    public void register() {
        registerActivityViewModel.register(getEmail(), getPassword(), getName(), 1 + "");
        registerActivityViewModel.getUser().observe(this,
                new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        Database db = Database.getInstance(RegisterActivity.this);
                            db.userDAO().insertUser(user);
                            if(db.userDAO().getAll() != null){
                                Log.d("USER","REGISTER SUCCESS");
                            }else{
                                Log.d("USER","REGISTER FAILED");
                            }
                        db.userDAO().insertUser(user);
                        if (db.userDAO().getAll() != null) {
                            Log.d("USER", "REGISTER SUCCESS");
                        } else {
                            Log.d("USER", "REGISTER FAILED");
                        }
                        if (user != null) {
//                            Intent intent = new Intent(RegisterActivity.this, SearchActivity.class);
 //                           RegisterActivity.this.startActivity(intent);
                        }
                    }
                });
    }*/

    @Override
    public void setupUI() {
        //btnRegister.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
           //     register();
            //}
       // });
    }
}
