package com.example.vietis.Utilities.helpers;

import com.android.volley.Response;
import com.example.vietis.R;
import com.example.vietis.Utilities.common.AppResources;

import org.json.JSONObject;

public class API {
    /**
     * Return URL String for path with host and port
     *
     * @param path
     * @return
     */
    public static String get_URL_STRING(String path) {
        return "http://" + AppResources.getResourses().getString(R.string.HOST_NAME) + ":" + AppResources.getResourses().getString(R.string.POST) +"/"+ path;
    }


}
