package com.example.framework.bmob;

/**
 * @filename: com.example.framework.bmob
 * @author: 60347
 * @description: 用户类
 * @time: 2020/5/21 19:58
 */
public class User {

    //Token属性

    //获取Token的头像地址
    private String tokenPhoto;
    //获取Token的昵称
    private String tokenNickName;

    //基本属性

    //昵称
    private String nickName;
    //头像
    private String photo;

    //其他属性

    //性别 true = 男 false = 女
    private boolean sex = true;
    //简介
    private String desc;
    //年龄
    private int age = 0;

    //爱好
    private String hobby;


    public String getTokenPhoto() {
        return tokenPhoto;
    }

    public void setTokenPhoto(String tokenPhoto) {
        this.tokenPhoto = tokenPhoto;
    }

    public String getTokenNickName() {
        return tokenNickName;
    }

    public void setTokenNickName(String tokenNickName) {
        this.tokenNickName = tokenNickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }


    @Override
    public String toString() {
        return "IMUser{" +
                "tokenPhoto='" + tokenPhoto + '\'' +
                ", tokenNickName='" + tokenNickName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", photo='" + photo + '\'' +
                ", sex=" + sex +
                ", desc='" + desc + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
