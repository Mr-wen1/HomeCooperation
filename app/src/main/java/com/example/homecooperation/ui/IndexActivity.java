package com.example.homecooperation.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.framework.entity.Constants;
import com.example.framework.utils.SpUtils;
import com.example.homecooperation.R;

/**
 * @filename: com.example.homecooperation.ui
 * @author: 60347
 * @description: 启动页
 * @time: 2020/4/16 17:12
 */
public class IndexActivity extends AppCompatActivity {

    /**
     * 1.把启动页全屏
     * 2.延迟进入主页
     * 3.根据具体逻辑是进入主页还是引导页还是登录页
     */

    private static final int SKIP_MAIN = 1000;

    private Handler mHandler =new Handler(message ->{
        switch (message.what){
            case SKIP_MAIN:
                startMain();
                break;
        }
        return false;
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }


    /**
     * 进入主页
     */
    private void startMain() {
        //判断是否第一次启动
        boolean isFirstRun = SpUtils.getInstance()
                .getBoolean(Constants.SP_IS_FIRST_RUN, true);
        Intent intent = new Intent();
        if (isFirstRun){
            intent.setClass()
        }

    }
}
