package com.example.vietis.Data.IRepository.repository;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static final String TAG = "VolleySingleton";
    private static VolleySingleton volley;
    private RequestQueue Request;

    private VolleySingleton(Context context) {
        if (Request == null) {
            Request = Volley.newRequestQueue(context.getApplicationContext());
        }
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (volley == null)
            volley = new VolleySingleton(context);
        return volley;
    }

    public RequestQueue getRequestQueue() {
        return Request;
    }
}
