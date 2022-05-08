package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter@ToString
public class Employee extends BaseDomain {
  private String name;//员工姓名
  private String password;//员工密码
  private String email;//员工邮件
  private Integer age;//员工年龄
  private boolean admin;//是否为管理员
  private Department dept;//员工所在的部门
  List<Role> roles = new ArrayList<>();//员工扮演的角色
}
