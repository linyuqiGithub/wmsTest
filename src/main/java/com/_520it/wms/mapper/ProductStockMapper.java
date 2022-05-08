package com._520it.wms.mapper;

import com._520it.wms.domain.ProductStock;
import com._520it.wms.query.ProductStockQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStockMapper {
	void save(ProductStock entity);
	void update(ProductStock entity);
	void delete(Long id);
    ProductStock get(Long id);
	List<ProductStock> listAll();
    Long queryByConditionCount(ProductStockQueryObject qo);
    List<ProductStock> queryByCondition(ProductStockQueryObject qo);
    ProductStock queryBydepotAndProduct(@Param("depotId")Long depotId,@Param("productId")Long productId);
}