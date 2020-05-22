package com.example.framework.bmob;

import cn.bmob.v3.BmobObject;

/**
 * @filename: com.example.framework.bmob
 * @author: 60347
 * @description: 隐私库
 * @time: 2020/5/21 20:13
 */
public class PrivateSet extends BmobObject {
    //用户ID
    private String userId;
    //用户电话号码
    private String phone;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
