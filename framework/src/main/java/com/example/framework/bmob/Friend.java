package com.example.framework.bmob;

/**
 * @filename: com.example.framework.bmob
 * @author: 60347
 * @description: 朋友列表
 * @time: 2020/5/21 20:11
 */
public class Friend {
    //我自己
    private User user;
    //好友
    private User friendUser;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriendUser() {
        return friendUser;
    }

    public void setFriendUser(User friendUser) {
        this.friendUser = friendUser;
    }
}
