package com.example.vietis.Data.IRepository.repository;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.regex.Pattern;

public class Config {
    //test nhớ sửa file config
    public static final String HOST_NAME = "10.1.35.170";
    //public static final String HOST_NAME = "192.168.1.78";
    public static final String PORT = "3000";

    // Không biết để method này ở đâu nên để tạm ở đây - anhnt
    public static boolean containIgnoreCase(String s1, String s2) {
        return Pattern.compile(Pattern.quote(s2), Pattern.CASE_INSENSITIVE).matcher(s1).find();
    }

    //Picasso không biết vứt đâu nên vứt vào đây - anhnt
    public static void loadImage(String url, ImageView imageViewParent) {
        final ImageView imageView = imageViewParent;
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                bitmap = scaleDown(bitmap, 525, true);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        Picasso.get().load(url).into(target);
    }

    public static Bitmap scaleDown(Bitmap bitmapOrigin, int wantedSize, boolean filter) {
        float ratio = bitmapOrigin.getWidth() / (float) bitmapOrigin.getHeight();
        int width = wantedSize;
        int height = Math.round(width / ratio);
        return Bitmap.createScaledBitmap(bitmapOrigin, width, height, filter);
    }

    //add onClickEvent cho toàn bộ phần tử con nè, để tạm ở đây.
    public static void setChildViewOnClickListener(View v, View.OnClickListener listener) {
        if (v instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) v;
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = group.getChildAt(i);
                if (child instanceof LinearLayout || child instanceof RelativeLayout) {
                    setChildViewOnClickListener(child, listener);
                }
                if (child instanceof TextView) {
                    TextView text = (TextView) child;
                    text.setFocusable(false);
                }
                child.setOnClickListener(listener);
            }
        }
    }
}
