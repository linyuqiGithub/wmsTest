package com._520it.wms.domain;


import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Department extends BaseDomain {
   //部门名称
   private String name;
   //部门编码
   private String sn;
}
