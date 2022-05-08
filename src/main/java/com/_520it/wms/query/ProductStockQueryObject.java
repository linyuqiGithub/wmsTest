package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ProductStockQueryObject extends QueryObject {
   //货品编码或名称
   private String keyword;
   //仓库
   private Long depotId = -1L;
   //品牌
   private Long brandId = -1L;
   //阈值(最大值)
   private Long limitNum;
}
