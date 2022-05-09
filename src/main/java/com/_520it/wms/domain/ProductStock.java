package com._520it.wms.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Setter@Getter@ObjectProp("产品库存")
public class ProductStock extends BaseDomain{
    @ObjectProp("库存价格")
    private BigDecimal price;
    @ObjectProp("库存数量")
    private BigDecimal storeNumber;
    @ObjectProp("库存总金额")
    private BigDecimal amount;
    @ObjectProp("库存入库时间")
    private Date incomeDate;
    @ObjectProp("库存出库时间")
    private Date outcomeDate;
    @ObjectProp("货品")
    private Product product;
    @ObjectProp("仓库")
    private Depot depot;
}
