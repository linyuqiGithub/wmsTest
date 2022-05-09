package com._520it.wms.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单模型
 */
@Setter@Getter@ObjectProp("系统菜单")@ToString
public class SystemMenu extends BaseDomain {
    @ObjectProp("菜单编码")
    private String sn;
    @ObjectProp("菜单名称")
    private String name;
    @ObjectProp("URL")
    private String url;//菜单action的地址
    private SystemMenu parent;//父菜单,多对一
    private List<SystemMenu> children;//子菜单,一对多

    //提供一个getter属性用于显示系统父级目录
    public String getParentName(){
        //调用方法才能启动延迟加载,只调用字段不会调用相关sql
        if(getParent() == null){
            return "根目录";
        }
         return parent.getName();
    }

    //返回自定义格式的数据
    public Object toJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",getId());
        map.put("pId",getParent().getId());
        map.put("name",getName());
        map.put("action",getUrl());
        return map;
    }
}
