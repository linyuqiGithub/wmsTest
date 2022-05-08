package com._520it.wms.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017/6/2.
 */
@Setter@Getter@ObjectProp("供应商")
public class Supplier extends BaseDomain {
    @ObjectProp("编号")
    private Long id;
    @ObjectProp("供应商名称")
    private String name;
    @ObjectProp("联系电话")
    private String phone;
    @ObjectProp("地址")
    private String address;
}
