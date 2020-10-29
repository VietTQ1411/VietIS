package com.example.vietis.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import com.example.vietis.R;


public class RatingFragment extends Dialog {

    private RatingBar userRatingBar;
    private Button btnRating;

    public RatingFragment(Context context) {
        super(context);
        this.setContentView(R.layout.fragment_rating);
        setupUI();
    }
    private void setupUI() {
        userRatingBar =  findViewById(R.id.userRatingBar);
        btnRating =  findViewById(R.id.btnRating);

        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int noofstars = userRatingBar.getNumStars();
                float getrating = userRatingBar.getRating();
               System.out.println(getrating + " viet dep trai" + noofstars);
            }
        });
    }
}