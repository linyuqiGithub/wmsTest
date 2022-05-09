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
@Setter@Getter@ObjectProp("销售出库单")
public class StockOutcomebill extends BaseDomain {
    public static final int NORMAL = 0;//未审核
    public static final int AUDIT = 1;//已审核
    @ObjectProp("出库单编号")
    private String sn;
    @ObjectProp("业务时间")
    private Date vdate;
    @ObjectProp("审核状态")
    private Integer status;
    @ObjectProp("出库总金额")
    private BigDecimal totalAmount;
    @ObjectProp("出库总数量")
    private BigDecimal totalNumber;
    @ObjectProp("审核时间")
    private Date auditTime;
    @ObjectProp("制单时间")
    private Date inputTime;
    @ObjectProp("制单人")
    private Employee auditor;
    @ObjectProp("审核人")
    private Employee inputUser;
    @ObjectProp("仓库")
    private Depot depot;
    @ObjectProp("客户")
    private Client client;
    @ObjectProp("销售出库单明细")
    private List<StockOutcomebillItem> items = new ArrayList<>();
}
