package com._520it.wms.vo;

import com._520it.wms.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 订单报表vo对象
 */
@Setter@Getter
public class OrderChartVO extends BaseDomain {
    private String groupBy;//分组类型
    private BigDecimal totalNumber;//采购总数量
    private BigDecimal totalAmount;//采购总金额
}
