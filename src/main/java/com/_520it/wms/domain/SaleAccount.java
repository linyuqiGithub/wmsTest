package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售帐模型
 *
 */
@Setter@Getter
public class SaleAccount extends BaseDomain {
    //业务日期
    private Date vdate;
    //数量
    private BigDecimal number;
    //采购价格
    private BigDecimal costPrice;
    //采购总金额
    private BigDecimal costAmount;
    //销售价格
    private BigDecimal salePrice;
    //销售总金额
    private BigDecimal saleAmount;
    //销售毛利润
    private BigDecimal grossProfit;
    //销售人员
    private Employee saleMan;
    //出库仓库
    private Depot depot;
    //产品
    private Product product;
    //客户
    private Client client;
}
