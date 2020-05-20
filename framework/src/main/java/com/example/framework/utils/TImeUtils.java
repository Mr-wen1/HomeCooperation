package com.example.framework.utils;

import java.text.SimpleDateFormat;

/**
 * @filename: com.example.framework.utils
 * @author: 60347
 * @description: 时间转换类
 * @time: 2020/5/20 0:40
 */
public class TImeUtils {

    /**
     * 转换毫秒格式 hh:mm:ss
     * 1s = 1000ms
     * 1m = 60s
     * 1h = 60m
     * 1d = 24h
     * @param ms
     * @return
     */

    public static String formatDuring(Long ms) {

        long hours = ((ms + 2880000) % (1000 * 60 * 60)) / (1000 * 60 * 60);
        long minutes = (ms % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (ms % (1000 * 60)) / 1000;

        String h = hours + "";
        if (hours < 10) {
            h = "0" + h;
        }

        String m = minutes + "";
        if (minutes < 10) {
            m = "0" + m;
        }

        String s = seconds + "";
        if (seconds < 10) {
            s = "0" + s;
        }

        return h + ":" + m + ":" + s;
    }

    public static String formatTime(long time){
        SimpleDateFormat sd = new SimpleDateFormat("yyyy年mm月dd日 hh:mm:ss");
        return sd.format(time);
    }
}
