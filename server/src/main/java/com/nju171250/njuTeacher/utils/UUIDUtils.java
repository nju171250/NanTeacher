package com.nju171250.njuTeacher.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Junhui Chen
 * @Title: UUIDUtils
 * @ProjectName server
 * @Description: TODO
 * @date 2019/1/16 9:38
 */
@Component
public class UUIDUtils {
    public String getUUID32(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
