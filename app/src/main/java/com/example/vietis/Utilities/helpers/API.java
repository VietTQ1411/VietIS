package com.example.vietis.Utilities.helpers;

import com.example.vietis.Data.IRepository.repository.Config;
import com.example.vietis.R;
import com.example.vietis.Utilities.common.AppResources;

public class API {
    /**
     * Return URL String for path with host and port
     * @param path
     * @return
     */
    public static String get_URL_STRING(String path) {
        return "http://" + AppResources.getResourses().getString(R.string.HOST_NAME) + ":" + AppResources.getResourses().getString(R.string.POST) + path;
    }
}
