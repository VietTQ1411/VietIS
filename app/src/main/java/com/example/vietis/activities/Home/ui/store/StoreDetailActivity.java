package com.example.vietis.activities.Home.ui.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Comment;
import com.example.vietis.Data.entity.Rating;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.Data.view_model.ListActivityModel;
import com.example.vietis.Data.view_model.StoreDeatilActivityModel;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.CommentAdapter;
import com.example.vietis.UI.adapter.SearchAdapter;
import com.example.vietis.UI.dialog.RatingFragment;
import com.example.vietis.Utilities.common.UserApp;
import com.example.vietis.activities.IView;

import java.util.ArrayList;
import java.util.List;

// To display a message in the log (logcat)

/**
 *
 */
public class StoreDetailActivity extends AppCompatActivity implements IView {
    /**
     * Local param
     */
    private NestedScrollView nsvStoreView;
    private String TAG = "Store Detail";
    private boolean isVisible = true;

    /**
     * About for store
     */
    private ImageView imageStoreDetailIcon;
    private TextView txtStoreName;
    private TextView txtStoreAddress;
    private TextView txtStorePhone;
    private Button btnOrder;
    private Button btnGGMap;

    /**
     * Rating for store
     */
    private LinearLayout rate;
    private RatingBar ratingBar1;
    private TextView txtRating1;
    private RatingBar ratingBar2;
    private TextView txtRating2;
    private RatingBar ratingBar3;
    private TextView txtRating3;
    private RatingBar ratingBar4;
    private TextView txtRating4;
    private RatingBar ratingBar5;
    private TextView txtRating5;

    /**
     * Comment for store
     */
    private RecyclerView CommentRecyclerView;
    private CommentAdapter commentAdapter;

    /**
     * Description for store
     */
    private TextView txtStoreDescription;
    private TextView txtDescriptionVisible;

    /**
     * Food of store
     */
    //UI holders
    private RecyclerView FoodRecyclerView;
    //RecyclerView components
    private SearchAdapter foodAdapter;
    //View Model
    private ListActivityModel foodActivityModel;

    /**
     * Store Repository of store
     */
    private Shop store = null;
    private List<Rating> listRate = null;
    private StoreDeatilActivityModel storeDeatilActivityModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        mappingUI();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpData();
        setupUI();
    }

    @Override
    public void mappingUI() {
        nsvStoreView = findViewById(R.id.nsvStoreView);

        /**
         * About for store
         */
        imageStoreDetailIcon = findViewById(R.id.imageStoreDetailIcon);
        txtStoreName = findViewById(R.id.txtStoreName);
        txtStoreAddress = findViewById(R.id.txtStoreAddress);
        txtStorePhone = findViewById(R.id.txtStorePhone);
        btnOrder = findViewById(R.id.btnOrder);
        btnGGMap = findViewById(R.id.btnGGMap);

        /**
         * Rating for store
         */
        rate = findViewById(R.id.rate);
        ratingBar1 = findViewById(R.id.ratingBar1);
        txtRating1 = findViewById(R.id.txtRating1);
        ratingBar2 = findViewById(R.id.ratingBar2);
        txtRating2 = findViewById(R.id.txtRating2);
        ratingBar3 = findViewById(R.id.ratingBar3);
        txtRating3 = findViewById(R.id.txtRating3);
        ratingBar4 = findViewById(R.id.ratingBar4);
        txtRating4 = findViewById(R.id.txtRating4);
        ratingBar5 = findViewById(R.id.ratingBar5);
        txtRating5 = findViewById(R.id.txtRating5);

        /**
         * Comment for store
         */
        CommentRecyclerView = findViewById(R.id.CommentRecyclerView);
        commentAdapter = new CommentAdapter(new ArrayList<Comment>());


        /**
         * Description for store
         */
        txtStoreDescription = findViewById(R.id.txtStoreDescription);
        txtDescriptionVisible = findViewById(R.id.txtDescriptionVisible);

        /**
         * Food of store
         */
        FoodRecyclerView = findViewById(R.id.FoodRecyclerView);
//        foodAdapter = new SearchAdapter(new ArrayList<Shop>());
        foodActivityModel = new ViewModelProvider(this).get(ListActivityModel.class);

        /**
         * Layout RecyclerView
         */
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        FoodRecyclerView.setLayoutManager(layoutManager);
        CommentRecyclerView.setLayoutManager(layoutManager2);


        storeDeatilActivityModel = new ViewModelProvider(this).get(StoreDeatilActivityModel.class);
    }

    /**
     * format description
     *
     * @param description
     * @return
     */
    public String createIndentedText(String description) {
        description = "\t" + description.replaceAll("\n", "\n\t");
        return description;
    }

    @Override
    public void setupUI() {
        txtDescriptionVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisible = !isVisible;
                if (isVisible) {
                    txtDescriptionVisible.setText("Description ▼");
                    txtStoreDescription.setVisibility(View.VISIBLE);
                } else {
                    txtDescriptionVisible.setText("Description ►");
                    txtStoreDescription.setVisibility(View.GONE);
                }
            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingFragment dialog = new RatingFragment(StoreDetailActivity.this);
                int width = (int) (getResources().getDisplayMetrics().widthPixels);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.5);
                dialog.getWindow().setLayout(width, height);
                dialog.show();
            }
        });
    }

    /**
     *
     */
    public void setUpData() {
        /**
         *  get store detail
         */
        Intent parent = getIntent();
        Bundle b = parent.getExtras();
        String id = null;
        if (b != null) {
            id = b.getString("id");

        }
        UserApp.user.setTokenKey("m8`q9v(`eS1uR.=|");
        storeDeatilActivityModel.getStoreDetail(UserApp.user.getTokenKey(), id);
        storeDeatilActivityModel.getData().observe(this, new Observer<List<Object>>() {
            @Override
            public void onChanged(List<Object> objects) {
                store = (Shop) objects.get(0);
                listRate = new ArrayList<>();
                for (int i = 1; i < objects.size(); i++) {
                    listRate.add((Rating) objects.get(i));
                }
                setUpStoreDetail();
            }
        });
        storeDeatilActivityModel.getCommentData().observe(this, new Observer<ArrayList<Comment>>() {
            @Override
            public void onChanged(ArrayList<Comment> comments) {
                commentAdapter = new CommentAdapter(comments);
                commentAdapter.notifyDataSetChanged();
                CommentRecyclerView.setAdapter(commentAdapter);
                nsvStoreView.scrollTo(0, 0);
            }
        });
        /**
         *  input data
         */

        foodActivityModel.getShopData().observe(this, new Observer<ArrayList<Shop>>() {
            @Override
            public void onChanged(ArrayList<Shop> arrayList) {
//                foodAdapter = new SearchAdapter(arrayList);
                foodAdapter.notifyDataSetChanged();
                FoodRecyclerView.setAdapter(foodAdapter);
                nsvStoreView.scrollTo(0, 0);
            }
        });
    }

    public void fillDetailStore(Shop store) {
        imageStoreDetailIcon = findViewById(R.id.imageStoreDetailIcon);
        txtStoreName.setText(store.getName());
        txtStoreAddress.setText(store.getAddress());
        txtStorePhone.setText("Hotline: " + store.getPhoneNumber());
        txtStoreDescription.setText(createIndentedText(store.getDescription()));
    }

    private void setUpStoreDetail() {
        //Store description
        txtStoreName.setText(store.getName());
        txtStoreAddress.setText(store.getAddress());
        txtStorePhone.setText("Hotline: " + store.getPhoneNumber());
        txtStoreDescription.setText(store.getDescription());

        //Rating section
        txtRating1.setText(listRate.get(0).getVoteCount() + " lượt đánh giá");
        txtRating2.setText(listRate.get(1).getVoteCount() + " lượt đánh giá");
        txtRating3.setText(listRate.get(2).getVoteCount() + " lượt đánh giá");
        txtRating4.setText(listRate.get(3).getVoteCount() + " lượt đánh giá");
        txtRating5.setText(listRate.get(4).getVoteCount() + " lượt đánh giá");
    }
}