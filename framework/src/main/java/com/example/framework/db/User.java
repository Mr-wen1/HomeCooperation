package com.example.framework.db;

import org.litepal.crud.LitePalSupport;

import java.util.UUID;

/**
 * @filename: com.example.framework.db
 * @author: 60347
 * @description: 用户
 * @time: 2020/5/23 0:09
 */
public class User extends LitePalSupport {

    private String id;
    private String username;
    private String password;
    private String permission;

    public User(String username, String password) {
        this.username = username;
    }
    public User(){
        super();
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
