package com.example.homecooperation.ui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.framework.base.BaseUIActivity;
import com.example.framework.bmob.BmobManager;
import com.example.framework.bmob.InformationUser;
import com.example.framework.utils.LogUtils;
import com.example.homecooperation.MainActivity;
import com.example.homecooperation.R;

import java.util.UUID;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RegisterActivity extends BaseUIActivity implements View.OnClickListener {

    private EditText et_register_username;
    private EditText et_register_password;
    private EditText et_register_passwordAgain;
    private EditText et_phoneCode;

    private Button bt_send;
    private Button bt_register;

    private ImageView im_backLogin;

    protected String permission;

    private static String IS_CODE = "";

    private static final int H_TIME = 1001;
    //60s倒计时
    private static int TIME = 60;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case H_TIME:
                    TIME--;
                    bt_send.setText(TIME + "s");
                    if (TIME > 0) {
                        mHandler.sendEmptyMessageDelayed(H_TIME, 1000);
                    } else {
                        bt_send.setEnabled(true);
                        bt_send.setText("发送");
                    }
                    break;
            }
            return false;
        }
    });

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
            permission = "parent";
            RadioButton chooseIdentity = group.findViewById(checkedId);
            String identity = chooseIdentity.getText().toString();
            Toast.makeText(RegisterActivity.this, "你选择的身份是" + identity
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
//        String phone = SpUtils.getInstance().getString(Constants.SP_PHONE, "");
//        if (!TextUtils.isEmpty(phone)) {
//            et_register_username.setText(phone);
//        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_register_send:
                sendSMS();
                break;
            case R.id.bt_register_register:
                if (isEmpty()) {
                    String id = UUID.randomUUID().toString();
                    String username = et_register_username.getText().toString();
                    String passwordAgain = et_register_passwordAgain.getText().toString();
                    if (isUser(username)) {
                        registerUser(id, username, passwordAgain, permission);
                    }
                }
                break;
            case R.id.iv_register_back:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }


    private void sendSMS() {
        //1.获取手机号码
        String phone = et_register_username.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, getString(R.string.text_phone_null),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        //2.请求短信验证码
        BmobManager.getInstance().requestSMS(phone, new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {
                    bt_send.setEnabled(false);
                    mHandler.sendEmptyMessage(H_TIME);
                    Toast.makeText(RegisterActivity.this, getString(R.string.text_code_request_succeed),
                            Toast.LENGTH_SHORT).show();
                } else {
                    LogUtils.e("SMS:" + e.toString());
                    Toast.makeText(RegisterActivity.this, getString(R.string.text_code_request_failed),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 判断是否为空
     */
    private boolean isEmpty() {
        //1.判断手机号码不为空
        final String phone = et_register_username.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, getString(R.string.text_phone_null),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        //2.判断密码不为空
        String password = et_register_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, getString(R.string.text_password_null),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        //3.判断再次输入的密码不为空和是否两次输入密码一致
        String passwordAgain = et_register_passwordAgain.getText().toString().trim();
        if (TextUtils.isEmpty(passwordAgain)) {
            Toast.makeText(this, getString(R.string.text_password_null),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.equals(passwordAgain)) {
            Toast.makeText(this, getString(R.string.text_password_difference),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        //4.判断验证码不为空
        String code = et_phoneCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, getString(R.string.text_phoneCode_null),
                    Toast.LENGTH_SHORT).show();
            return false;
        } /*else {
            //验证验证码
            BmobManager.getInstance().verifySMSCode(phone, code, new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Toast.makeText(RegisterActivity.this, "验证码验证成功，您可以在此时进行绑定操作！\n",
                                Toast.LENGTH_SHORT).show();
                        IS_CODE = "验证码验证成功";
                        InformationUser user = BmobUser.getCurrentUser(InformationUser.class);
                        user.setMobilePhoneNumber(phone);
                        user.setMobilePhoneNumberVerified(true);
                        user.update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {

                                if (e == null) {
                                    Toast.makeText(RegisterActivity.this, "绑定手机号码成功",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RegisterActivity.this,
                                            "绑定手机号码失败：" + e.getErrorCode() + "-" + e.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                    LogUtils.e(e.getErrorCode() + e.getMessage());
                                }
                            }
                        });
                    } else {
                        Toast.makeText(RegisterActivity.this,
                                "验证码验证失败：" + e.getErrorCode() + "-" + e.getMessage() + "\n",
                                Toast.LENGTH_SHORT).show();
                        LogUtils.e(e.getErrorCode() + e.getMessage());
                        IS_CODE = "验证码验证失败";
                    }
                }
            });
        }*/
        return true;
    }

    /**
     * 判断用户名是否存在
     *
     * @return
     */
    private Boolean isUser(String username) {
        new Thread(() -> {
            try {
                String json = "{\n" +
                        "\t\"username\":\"" + username + "\"\n" +
                        "}";

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.101.123:8090/login/query")
                        .post(RequestBody.create(MediaType.parse("application/json"), json))
                        .build();
                //执行发送命令
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                if (!responseData.equals("")) {
                    Toast.makeText(RegisterActivity.this,
                            R.string.text_username_existed, Toast.LENGTH_SHORT).show();
                    return;
                }
                runOnUiThread(() -> Toast.makeText(RegisterActivity.this,
                        "发送成功", Toast.LENGTH_SHORT).show());
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(RegisterActivity.this,
                        "网络连接错误", Toast.LENGTH_SHORT).show());
            }
        }).start();
        return true;
    }

    /**
     * 注册
     */
    private void registerUser(String id, String username, String passwordAgain, String permission) {

        new Thread(() -> {
            try {
                String json = "{\n" +
                        "\t\"id\": \"" + id + "\",\n" +
                        "\t\"username\":\"" + username + "\",\n" +
                        "\t\"password\": \"" + passwordAgain + "\",\n" +
                        "\t\"permission\": \"" + permission + "\"\n" +
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
                Intent intent  = new Intent(this, MainActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(RegisterActivity.this,
                        "网络连接错误", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}
