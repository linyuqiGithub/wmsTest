package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ProductQueryObject extends QueryObject {
     //编码或名称
     private String keyword;
     //品牌
     private Long brandId = -1L;
}
