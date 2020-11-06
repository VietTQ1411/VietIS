package com.example.vietis.activities.Home.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.vietis.R;
import com.example.vietis.activities.IView;

public class HomeFragment extends Fragment implements IView {



    //UI holders
    private SearchView searchViewSearch;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void mappingUI() {
     //   searchViewSearch = view.findViewById(R.id.searchViewHome);
    }

    @Override
    public void setupUI() {
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(view, SearchActivity.class);
//                startActivity(intent);
//            }
//        };
    //    Config.setChildViewOnClickListener(searchViewSearch, listener);
    }
}