package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 货品明细(不需要单独的生成一套CRUD页面，因为订单明细是关联在订单上的)
 *
 */
@Setter@Getter@ToString
public class OrderbillItem extends BaseDomain {
    private Long id;
    //采购价格
    private BigDecimal costPrice;
    //采购数量
    private BigDecimal number;
    //金额小计
    private BigDecimal amount;
    //备注说明
    private String remark;
    //采购商品
    private Product product;
    //采购订单
    private Orderbill orderbill;
}
