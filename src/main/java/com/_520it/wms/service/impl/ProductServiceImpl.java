package com._520it.wms.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com._520it.wms.domain.Depot;
import com._520it.wms.domain.ProductStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.domain.Product;
import com._520it.wms.mapper.ProductMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.service.IProductService;
import lombok.Setter;
public class ProductServiceImpl implements IProductService {
	@Setter
	private ProductMapper productMapper;
	
	public void  delete(Long id) {
		  productMapper.delete(id);
	}

	public void save(Product entity) {
		  productMapper.save(entity);
	}

	public Product get(Long id) {
		return productMapper.get(id);
	}

	public List<Product> listAll() {
		return productMapper.listAll();
	}

	public void update(Product entity) {
		  productMapper.update(entity);
	}


	@Override
	public PageResult queryByConditionPage(ProductQueryObject qo) {
		Long count = productMapper.queryByConditionCount(qo);
		if(count<=0){
			return new PageResult(1, qo.getPageSize(), 0, Collections.EMPTY_LIST);
		}
		List<Product> data = productMapper.queryByCondition(qo);
		PageResult pageResult = new PageResult(qo.getCurrentPage(),qo.getPageSize(), count.intValue(), data);
		return pageResult;
	}


}
