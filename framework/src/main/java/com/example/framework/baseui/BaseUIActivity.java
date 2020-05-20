package com.example.framework.baseui;

import android.os.Bundle;

import com.example.framework.utils.SystemUI;

/**
 * @filename: com.example.framework.baseui
 * @author: 60347
 * @description: 基布局
 * @time: 2020/5/20 0:48
 */
public class BaseUIActivity extends BaseActivity{

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SystemUI.fixSystemUI(this);
    }
}
