package com._520it.wms.test;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * 用于将密码明文进行加密
 * Created by Administrator on 2017/5/29.
 */
public class DruidPassword {
    public static void main(String args[]) throws Exception{
        String key = ConfigTools.encrypt("admin");
        //将加密后的密码粘贴到dhcp.property文件中的密码中去
        System.out.println(key);
    }
}
