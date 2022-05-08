package com._520it.wms.service;
import java.math.BigDecimal;
import java.util.List;

import com._520it.wms.domain.BaseDomain;
import com._520it.wms.domain.Depot;
import com._520it.wms.domain.Product;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductQueryObject;

public interface IProductService {
	void delete(Long id);
	void save(Product entity);
    Product get(Long id);
    List<Product> listAll();
	void update(Product entity);
	PageResult queryByConditionPage(ProductQueryObject qo);

}
