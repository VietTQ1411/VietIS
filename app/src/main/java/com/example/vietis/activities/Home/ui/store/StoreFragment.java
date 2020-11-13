package com.example.vietis.activities.Home.ui.store;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Shop;
import com.example.vietis.Data.view_model.ListActivityModel;
import com.example.vietis.Data.view_model.MutableArray;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.SearchAdapter;
import com.example.vietis.Data.inteface.IListView;
import com.example.vietis.Data.inteface.IView;
import com.example.vietis.Utilities.common.AppResources;

import java.util.ArrayList;

public class StoreFragment extends Fragment implements IView, IListView {
    //UI holders
    private SearchView storeSearchViewSearch;
    private static View view;
    private RecyclerView recyclerStoreViewSearch;
    //RecyclerView components
    private SearchAdapter<Shop> storeAdapter;
    //View Model
    private ListActivityModel storeActivityModel;
    private int PAGE = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            View root = inflater.inflate(R.layout.fragment_store, container, false);
            view = root;
            new Thread(new Runnable() {
                public void run() {
                    mappingUI();
                    setupUI();
                }
            }).start();
            return root;
        }else{
            callData();
        }
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        PAGE = 0;
        MutableArray.clearData();
    }

    @Override
    public void mappingUI() {
        storeSearchViewSearch = view.findViewById(R.id.storeSearchViewSearch);
        storeAdapter = new SearchAdapter(this, new ArrayList<Shop>(), Shop.class);
        storeActivityModel = new ListActivityModel(this);
        recyclerStoreViewSearch = view.findViewById(R.id.recyclerStoreViewSearch);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerStoreViewSearch.setLayoutManager(layoutManager);

        recyclerStoreViewSearch.setAdapter(storeAdapter);
    }

    @Override
    public void setupUI() {

        //SearchView action
        storeSearchViewSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                PAGE = 0;
                storeActivityModel.searchStoreFormServerWithPage(query, PAGE);
                PAGE++;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
                    // storeAdapter.setObjectArray(storeActivityModel.getShopData().getValue());
                    storeAdapter.notifyDataSetChanged();
                    recyclerStoreViewSearch.setAdapter(storeAdapter);
                    return false;
                }
                storeAdapter.setObjectArray(storeActivityModel.searchShop(newText));
                storeAdapter.notifyDataSetChanged();
                recyclerStoreViewSearch.setAdapter(storeAdapter);
                return false;
            }
        });
        callData();
    }

    public void callData() {
        storeActivityModel = new ListActivityModel(this);
        storeActivityModel.searchStoreFormServerWithPage("", PAGE++);
    }


    public void setUpData(ArrayList<Shop> list) {
        storeAdapter.setObjectArray(list);
        storeAdapter.notifyItemInserted(list.size());
        storeAdapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToStoreDetail(Integer idStore) {
        Intent intent = new Intent(AppResources.getContext(), StoreDetailActivity.class);
        intent.putExtra("id", idStore + "");
        startActivity(intent);
    }

    @Override
    public void navigateToFoodDetail(Integer idFood) {
        /**Not use Function*/
    }

}