package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/6/7.
 * 销售出库明细
 */
@Setter@Getter
public class StockOutcomebillItem extends BaseDomain{
    private BigDecimal salePrice;//销售价格
    private BigDecimal number;//出库数量
    private BigDecimal amount;//金额小计
    private String remark;//备注
    private Product product;//采购商品
    private StockOutcomebill bill;//采购订单
}
