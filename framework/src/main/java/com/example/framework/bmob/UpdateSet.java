package com.example.framework.bmob;

/**
 * @filename: com.example.framework.bmob
 * @author: 60347
 * @description: 应用更新
 * @time: 2020/5/21 20:15
 */
public class UpdateSet {
    //描述
    private String desc;
    //下载地址
    private String path;
    //版本号
    private int versionCode;


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }
}
