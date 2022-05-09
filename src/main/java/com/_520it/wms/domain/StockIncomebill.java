package com._520it.wms.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Setter@Getter@ObjectProp("采购入库单")
public class StockIncomebill extends BaseDomain{
    public static final int NORMAL = 0;//未审核
    public static final int AUDIT = 1;//已审核
    @ObjectProp("入库单编号")
    private String sn;
    @ObjectProp("业务时间")
    private Date vdate;
    @ObjectProp("审核状态")
    private Integer status;
    @ObjectProp("入库总金额")
    private BigDecimal totalAmount;
    @ObjectProp("入库总数量")
    private BigDecimal totalNumber;
    @ObjectProp("审核时间")
    private Date auditTime;
    @ObjectProp("制单时间")
    private Date inputTime;
    @ObjectProp("制单人")
    private Employee inputUser;
    @ObjectProp("审核人")
    private Employee auditor;
    @ObjectProp("仓库")
    private Depot depot;
    @ObjectProp("采购入货单明细")
    List<StockIncomebillItem> items = new ArrayList<>();
}
