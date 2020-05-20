package com.example.homecooperation.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.framework.baseui.BaseUIActivity;
import com.example.homecooperation.R;

import java.security.acl.Group;

public class RegisterActivity extends BaseUIActivity implements View.OnClickListener {

    EditText et_register_username;
    EditText et_register_password;
    EditText et_phoneCode;

    Button bt_send;
    Button bt_register;

    ImageView im_backLogin;

    private RadioGroup radioGroupIdentity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {

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

        radioGroupIdentity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton chooseIdentity=  group.findViewById(checkedId);
                String identity=chooseIdentity.getText().toString();
                Toast.makeText(RegisterActivity.this,identity,Toast.LENGTH_LONG).show();
                switch (checkedId) {
                    case R.id.rb_register_userIdentity_parent:

                        break;
                    case R.id.rb_register_userIdentity_kinderGarden:
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_register_send:

                break;
            case R.id.bt_register_register:
                break;
            case R.id.iv_register_back:
                break;
        }
    }
}
