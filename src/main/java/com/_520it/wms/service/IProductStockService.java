package com._520it.wms.service;
import com._520it.wms.domain.Depot;
import com._520it.wms.domain.Product;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductStockQueryObject;
import com._520it.wms.vo.ProductStockChartVO;

import java.math.BigDecimal;
import java.util.List;

public interface IProductStockService {
	void delete(Long id);
	void save(ProductStock entity);
    ProductStock get(Long id);
    List<ProductStock> listAll();
	void update(ProductStock entity);
	PageResult queryByConditionPage(ProductStockQueryObject qo);
	void income(Depot depot, Product product, BigDecimal number, BigDecimal amount);
	BigDecimal outcome(Depot depot, Product product, BigDecimal number);

	List<ProductStockChartVO> CastType(List<ProductStock> data);
}
