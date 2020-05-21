package com.example.homecooperation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.framework.base.BaseUIActivity;
import com.example.homecooperation.MainActivity;
import com.example.homecooperation.R;

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
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_register:
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
