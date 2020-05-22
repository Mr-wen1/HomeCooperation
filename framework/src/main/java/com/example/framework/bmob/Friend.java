package com.example.framework.bmob;

import cn.bmob.v3.BmobObject;

/**
 * @filename: com.example.framework.bmob
 * @author: 60347
 * @description: 朋友列表
 * @time: 2020/5/21 20:11
 */
public class Friend extends BmobObject {
    //我自己
    private InformationUser user;
    //好友
    private InformationUser friendUser;

    public InformationUser getUser() {
        return user;
    }

    public void setUser(InformationUser user) {
        this.user = user;
    }

    public InformationUser getFriendUser() { return friendUser; }

    public void setFriendUser(InformationUser friendUser) {
        this.friendUser = friendUser;
    }
}
