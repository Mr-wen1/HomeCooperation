package com.example.framework.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.framework.BuildConfig;

/**
 * @filename: com.example.framework.utils
 * @author: 60347
 * @description: 存储进入APP的状态
 * @time: 2020/5/20 17:16
 */
public class SpUtils {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private volatile static SpUtils mInstance = null;

    private SpUtils() {
    }

    public static SpUtils getInstance() {
        if (mInstance == null) {
            synchronized (SpUtils.class) {
                if (mInstance == null) {
                    mInstance = new SpUtils();
                }
            }
        }
        return mInstance;
    }

    public void initSp(Context mContext) {
        /*
         * MODE_PRIVATE：只限于本应用读写
         * MODE_WORLD_READABLE:支持其他应用读，但是不能写
         * MODE_WORLD_WRITEABLE:其他应用可以写
         */
        sharedPreferences = mContext.getSharedPreferences(BuildConfig.SP_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void putInt(String key, int values) {
        editor.putInt(key, values);
        editor.commit();
    }

    public int getInt(String key, int defValues) {
        return sharedPreferences.getInt(key, defValues);
    }

    public void putString(String key, String values) {
        editor.putString(key, values);
        editor.commit();
    }

    public String getString(String key, String defValues) {
        return sharedPreferences.getString(key, defValues);
    }

    public void putBoolean(String key, boolean values) {
        editor.putBoolean(key, values);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean defValues) {
        return sharedPreferences.getBoolean(key, defValues);
    }

    public void deleteKey(String key) {
        editor.remove(key);
        editor.commit();

    }
}
