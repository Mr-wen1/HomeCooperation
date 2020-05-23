package com.example.framework.db;

import org.litepal.crud.LitePalSupport;

/**
 * @filename: com.example.framework.db
 * @author: 60347
 * @description: 新朋友类
 * @time: 2020/5/23 0:12
 */
public class NewFriend extends LitePalSupport {

    //留言
    private String msg;
    //对方id
    private String userId;
    //时间
    private long saveTime;
    //状态 -1：待确认 0：同意 1：拒绝
    private int isAgree = -1;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getId() {
        return userId;
    }

    public void setId(String id) {
        this.userId = id;
    }

    public long getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(long saveTime) {
        this.saveTime = saveTime;
    }

    public int getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(int isAgree) {
        this.isAgree = isAgree;
    }
}
