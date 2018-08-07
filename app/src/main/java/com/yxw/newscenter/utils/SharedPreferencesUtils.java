package com.yxw.newscenter.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
    private static final String NAME = "config";
    private static SharedPreferences sp;

    public static boolean getBoolean(Context context, String key, boolean value) {
        sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, value);
    }

    public static void setBoolean(Context context, String key, boolean value) {
        sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }

    public static String getString(Context context, String key, String value) {
        sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getString(key, value);
    }

    public static void setString(Context context, String key, String value) {
        sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }
}
