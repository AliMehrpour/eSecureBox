package com.volcano.assistant.utils;

import android.os.Build;

import com.volcano.assistant.BuildConfig;

/**
 * Created by alimehrpour on 1/6/15.
 */
public class Utils {

    public static boolean hasHoneyCombApi() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasJellyBeanApi() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean hasLollipopApi() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
