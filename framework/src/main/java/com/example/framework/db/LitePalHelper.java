package com.example.framework.db;

import com.example.framework.utils.LogUtils;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @filename: com.example.framework.db
 * @author: 60347
 * @description: 本地数据库帮助类
 * @time: 2020/5/23 0:11
 */
public class LitePalHelper {

    private static volatile LitePalHelper mInstnce = null;

    private LitePalHelper() {

    }

    public static LitePalHelper getInstance() {
        if (mInstnce == null) {
            synchronized (LitePalHelper.class) {
                if (mInstnce == null) {
                    mInstnce = new LitePalHelper();
                }
            }
        }
        return mInstnce;
    }

    /**
     * 保存基类
     *
     * @param support
     */
    private void baseSave(LitePalSupport support) {
        support.save();
    }

    /**
     * 保存新朋友
     *
     * @param msg
     * @param id
     */
    public void saveNewFriend(String msg, String id) {
        LogUtils.i("saveNewFriend");
        NewFriend newFriend = new NewFriend();
        newFriend.setMsg(msg);
        newFriend.setId(id);
        newFriend.setIsAgree(-1);
        newFriend.setSaveTime(System.currentTimeMillis());
        baseSave(newFriend);
    }

    /**
     * 保存通话记录
     *
     * @param userId
     * @param mediaType
     * @param callStatus
     */
    public void saveCallRecord(String userId, int mediaType, int callStatus) {
        CallRecord callRecord = new CallRecord();
        callRecord.setUserId(userId);
        callRecord.setMediaType(mediaType);
        callRecord.setCallStatus(callStatus);
        callRecord.setCallTime(System.currentTimeMillis());
        baseSave(callRecord);
    }

    public void saveUser(String name, String password){
        User user = new User(name, password);
        baseSave(user);
    }

    /**
     * 查询的基类
     *
     * @param cls
     * @return
     */
    private List<? extends LitePalSupport> baseQuery(Class cls) {
        return LitePal.findAll(cls);
    }

    /**
     * 查询新朋友
     *
     * @return
     */
    public List<NewFriend> queryNewFriend() {
        return (List<NewFriend>) baseQuery(NewFriend.class);
    }

    /**
     * 查询通话记录
     *
     * @return
     */
    public List<CallRecord> queryCallRecord() {
        return (List<CallRecord>) baseQuery(CallRecord.class);
    }

    /**
     * 查询用户和密码
     * @return
     */
    public List<User> queryUser(){
        return (List<User>) baseQuery(User.class);
    }

    /**
     * 更新新朋友的数据库状态
     *
     * @param userId
     * @param agree
     */
    public void updateNewFriend(String userId, int agree) {
        NewFriend newFriend = new NewFriend();
        newFriend.setIsAgree(agree);
        newFriend.updateAll("userId = ?", userId);
    }

    public List<User> list = new ArrayList<User>(){
//        Cursor cursor = mInstnce.queryUser();
    };
}
