package com._520it.wms.vo;

import com._520it.wms.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter@Setter
public class ProductStockChartVO extends BaseDomain {
    private Long id;
    private String productName;
    private String DepotName;
    private String BrandName;
    private BigDecimal price;
    private BigDecimal number;
    private BigDecimal Amount;
}
