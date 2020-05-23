package com.example.homecooperation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.framework.base.BaseUIActivity;
import com.example.framework.utils.LogUtils;
import com.example.homecooperation.MainActivity;
import com.example.homecooperation.R;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends BaseUIActivity implements View.OnClickListener {

    EditText et_username;
    EditText et_password;

    Button bt_login;

    TextView tv_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        et_username = findViewById(R.id.et_login_username);
        et_password = findViewById(R.id.et_login_password);

        bt_login = findViewById(R.id.bt_login_login);
        tv_register = findViewById(R.id.tv_login_register);

        bt_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login_login:
                login();
                break;
            case R.id.tv_login_register:
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);
                break;
        }
    }

    /**
     * 登陆
     */
    private void login() {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    String json = "{\n" +
                            "\t\"username\":\"" + username + "\",\n" +
                            "\t\"password\": \"" + password + "\",\n" +
                            "}";

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://192.168.101.123:8090/login/login2")
                            .post(RequestBody.create(MediaType.parse("application/json"), json))
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    if (responseData.isEmpty()) {
                        return;
                    } else {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    LogUtils.e(responseData);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
