package com.example.framework;

/**
 * @filename: com.example.framework
 * @author: 60347
 * @description: Framework 入口
 * @time: 2020/5/20 0:15
 */
public class Framework {

    private volatile static Framework mFramework;

    private String BUGLY_KEY = "d51bdd38bd";


    public static Framework getFramework(){
        if (mFramework == null){
            synchronized (Framework.class){
                if (mFramework == null){
                    mFramework = new Framework();
                }
            }
        }
        return mFramework;
    }

}
