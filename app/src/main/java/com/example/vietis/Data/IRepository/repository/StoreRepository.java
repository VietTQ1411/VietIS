package com.example.vietis.Data.IRepository.repository;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.vietis.Data.IRepository.ICommentRepository;
import com.example.vietis.Data.IRepository.IStoreDetailRepository;
import com.example.vietis.Data.entity.Comment;
import com.example.vietis.Data.entity.Rating;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.Data.view_model.MutableArray;
import com.example.vietis.R;
import com.example.vietis.Utilities.common.AppResources;
import com.example.vietis.Utilities.common.UserApp;
import com.example.vietis.Utilities.helpers.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreRepository {
    private static StoreRepository instance = null;
    private ICommentRepository iCommentRepository;
    private IStoreDetailRepository iStoreDeatilRepository;
    private final String TAG = "Store Deatil Repository";

    /**
     * Constructor
     *
     * @param ICommentRepository
     * @param iStoreDeatilRepository
     */
    private StoreRepository(ICommentRepository ICommentRepository, IStoreDetailRepository iStoreDeatilRepository) {
        this.iCommentRepository = ICommentRepository;
        this.iStoreDeatilRepository = iStoreDeatilRepository;
    }

    public static StoreRepository getInstance(ICommentRepository ICommentRepository, IStoreDetailRepository iStoreDeatilRepository) {
        if (instance == null) {
            instance = new StoreRepository(ICommentRepository, iStoreDeatilRepository);
        }
        return instance;
    }


    /**
     * @param token
     * @param id
     */
    public void getDetailStore(String token, String id) {
        StringRequest jsonObjectRequest = new StringRequest(com.android.volley.Request.Method.POST,
                API.get_URL_STRING(AppResources.getResourses().getString(R.string.GET_DETAIL_STORE))
                , new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String StringResponse) {
                try {
                    JSONObject jsonObject = new JSONObject(StringResponse);
                    String stringResult = jsonObject.getString("result");
                    if (stringResult.equals(AppResources.getResourses().getString(R.string.SUCCESS_REQUEST))) {
                        MutableArray.clearData();
                        JSONObject jsonStoreObject = jsonObject.getJSONObject("data");

                        //detail shop
                        JSONArray storeDetailObject = jsonStoreObject.getJSONArray("foundStore");
                        Shop store = Shop.generateShopFromJSON(storeDetailObject);
                        MutableArray.getArrayList().add(store);

                        //rating star
                        for (int i = 5; i > 0; i--) {
                            JSONObject result = jsonStoreObject.getJSONObject("result" + i);
                            Rating rating = Rating.generateRatingCount(i, result);
                            MutableArray.getArrayList().add(rating);
                        }

                        //3 comments
                        JSONArray CommentObject = jsonStoreObject.getJSONArray("newComment");
                        ArrayList<Comment> listComment = new ArrayList<>();
                        for (int i = 0; i < CommentObject.length(); i++) {
                            Comment com = Comment.generateCommentFroJSon(CommentObject.getJSONObject(i));
                            if (com != null) {
                                listComment.add(com);
                            }
                        }
//                        if (listComment.size() > 0) {
//                            iCommentRepository.getCommentLimit(listComment);
//                        } else {
//                            iCommentRepository.getCommentLimit(null);
//                        }
                        iStoreDeatilRepository.getStoreDetail();
                    }else{

                    }
                } catch (JSONException e) {
                    Log.e(TAG, "JsonObjectRequest onErrorResponse: " + e.getMessage());
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "JsonObjectRequest onErrorResponse: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tokenkey", UserApp.user.getTokenKey());
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        jsonObjectRequest.setTag(TAG);
        VolleySingleton.getInstance(AppResources.getContext()).getRequestQueue().add(jsonObjectRequest);
    }
}
