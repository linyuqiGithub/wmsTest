package com._520it.wms.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;


@Setter@Getter@ObjectProp("品牌")
public class Brand extends BaseDomain{
    @ObjectProp("编号")
    private Long id;
    @ObjectProp("品牌名称")
    private String name;
    @ObjectProp("品牌编号")
    private String sn;
}
