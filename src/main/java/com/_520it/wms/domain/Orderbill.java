package com._520it.wms.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */
@Setter@Getter@ObjectProp("产品订单")
public class Orderbill extends BaseDomain {

    public static final int NORMAL = 0;
    public static final int AUDIT = 1;

    @ObjectProp("订单编号")
    private String sn;
    @ObjectProp("业务时间")
    private Date vdate;
    @ObjectProp("审核状态")
    private Integer status;//0表示未审核,1表示已审核
    @ObjectProp("采购金额")
    private BigDecimal totalAmount;
    @ObjectProp("采购数量")
    private BigDecimal totalNumber;
    @ObjectProp("审核时间")
    private Date auditTime;
    @ObjectProp("录入时间")
    private Date inputTime;
    @ObjectProp("录入人")
    private Employee inputUser;
    @ObjectProp("审核人")
    private Employee auditor;
    @ObjectProp("供应商")
    private Supplier supplier;
    @ObjectProp("订单明细")
    private List<OrderbillItem> items = new ArrayList<>();
}
