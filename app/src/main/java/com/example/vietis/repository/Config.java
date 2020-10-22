package com.example.vietis.repository;

import java.util.regex.Pattern;

public class Config {
    //test nhớ sửa file config
    public static final String HOST_NAME = "10.1.35.170";
    //public static final String HOST_NAME = "192.168.1.78";
    public static final String PORT = "3000";

    // Không biết để method này ở đâu nên để tạm ở đây
    public static boolean containIgnoreCase(String s1, String s2){
        return Pattern.compile(Pattern.quote(s2), Pattern.CASE_INSENSITIVE).matcher(s1).find();
    }
}
