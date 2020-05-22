package com.example.homecooperation.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.example.framework.entity.Constants;
import com.example.framework.utils.LogUtils;
import com.example.framework.utils.SpUtils;
import com.example.homecooperation.MainActivity;
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

    private Handler mHandler = new Handler(message -> {
        if (message.what == SKIP_MAIN) {
            startMain();
        }
        return false;
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mHandler.sendEmptyMessageDelayed(SKIP_MAIN, 2 * 1000);
    }


    /**
     * 进入主页
     */
    private void startMain() {
        //判断是否第一次启动
        boolean isFirstRun = SpUtils.getInstance()
                .getBoolean(Constants.SP_IS_FIRST_RUN, true);
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
//        if (isFirstRun) {
//            //跳转到引导页
//            intent.setClass(this, GuideActivity.class);
//            //设置不是第一次启动
//            SpUtils.getInstance().putBoolean(Constants.SP_IS_FIRST_RUN, false);
//        } else {
//
//            intent.setClass(this, LoginActivity.class);
//            //非第一次启动，判断是否登录过
//            String token = SpUtils.getInstance().getString(Constants.SP_TOKEN, "");
//            if (TextUtils.isEmpty(token)) {
//                intent.setClass(this, MainActivity.class);
//            } else {
//                intent.setClass(this, LoginActivity.class);
//            }
//        }
    }
}
