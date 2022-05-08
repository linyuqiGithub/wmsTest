package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/6/5.
 */
@Setter@Getter
public class StockIncomebillItem extends BaseDomain {
    private BigDecimal costPrice;//入库价格
    private BigDecimal number;//入库数量
    private BigDecimal amount;//金额小计
    private String remark;//备注
    private Product product;//采购商品
    private StockIncomebill bill;//采购订单
}
