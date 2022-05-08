package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Permission extends BaseDomain{
  private Long id;
  //权限表达式
  private String expression;
  //权限名称
  private String name;
}
