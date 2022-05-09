package com._520it.wms.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;


@Setter@Getter@ObjectProp("客户管理")
public class Client extends BaseDomain {
    @ObjectProp("客户编码")
    private String sn;
    @ObjectProp("客户姓名")
    private String name;
    @ObjectProp("联系电话")
    private String phone;
}
