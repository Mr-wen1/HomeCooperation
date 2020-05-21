package com.example.homecooperation.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.framework.base.BaseUIActivity;
import com.example.framework.utils.LogUtils;
import com.example.homecooperation.R;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RegisterActivity extends BaseUIActivity implements View.OnClickListener {

    EditText et_register_username;
    EditText et_register_password;
    EditText et_register_passwordAgain;
    EditText et_phoneCode;

    Button bt_send;
    Button bt_register;

    ImageView im_backLogin;



    protected String permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {

        RadioGroup radioGroupIdentity;

        et_register_username = findViewById(R.id.et_register_username);
        et_register_password = findViewById(R.id.et_register_password);
        et_register_passwordAgain = findViewById(R.id.et_register_passwordAgain);
        et_phoneCode = findViewById(R.id.et_register_phoneCode);

        bt_send = findViewById(R.id.bt_register_send);
        bt_register = findViewById(R.id.bt_register_register);

        im_backLogin = findViewById(R.id.iv_register_back);

        radioGroupIdentity = findViewById(R.id.radioGroup_identity);

        bt_send.setOnClickListener(this);
        bt_register.setOnClickListener(this);
        im_backLogin.setOnClickListener(this);

        radioGroupIdentity.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton chooseIdentity = group.findViewById(checkedId);
            String identity = chooseIdentity.getText().toString();
            Toast.makeText(RegisterActivity.this, "你选择的身份是"+identity
                    , Toast.LENGTH_LONG).show();
            switch (checkedId) {
                case R.id.rb_register_userIdentity_parent:
                        permission = "parent";
                    break;
                case R.id.rb_register_userIdentity_kinderGarden:
                        permission = "kinderGarden";
                    break;
            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_register_send:

                break;
            case R.id.bt_register_register:
                String username = et_register_username.getText().toString();
                String password = et_register_password.getText().toString();
                String passwordAgain = et_register_passwordAgain.getText().toString();
                String authority = permission;
                if (password.equals(passwordAgain)) {
                    new Thread(() -> {
                        try {
                            String json = "{\n" +
                                    "\t\"id\": 10,\n" +
                                    "\t\"username\":\"" + username + "\",\n" +
                                    "\t\"password\": \"" + passwordAgain + "\",\n" +
                                    "\t\"permission\": \""+ authority +"\n" +
                                    "}";

                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url("http://192.168.101.123:8090/login/add2")
                                    .post(RequestBody.create(MediaType.parse("application/json"), json))
                                    .build();
                            //执行发送命令
                            Response response = client.newCall(request).execute();
                            LogUtils.e(json);
                            runOnUiThread(() -> Toast.makeText(RegisterActivity.this,
                                    "发送成功", Toast.LENGTH_SHORT).show());
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(() -> Toast.makeText(RegisterActivity.this,
                                    "网络连接错误", Toast.LENGTH_SHORT).show());
                        }
                    }).start();
                }
                break;
            case R.id.iv_register_back:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
