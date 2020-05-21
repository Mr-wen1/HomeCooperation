package com.example.framework.utils;

import java.util.List;

/**
 * @filename: com.example.framework.utils
 * @author: 60347
 * @description: 通用方法
 * @time: 2020/5/21 20:08
 */
public class CommonUtils {
    /**
     * 检查List是否可用
     * @param list
     * @return
     */
    public static boolean isEmpty(List list){
        return list != null && list.size() > 0;
    }
}
