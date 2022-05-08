package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Getter@Setter@ToString
public class Role extends BaseDomain{
   private Long id;
   //角色编码
   private String sn;
   //角色名称
   private String name;
   //拥有权限的集合
   List<Permission> permissions = new ArrayList<>();
   //可以查看的菜单集合
   List<SystemMenu> menus = new ArrayList<>();
}
