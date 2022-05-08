package com._520it.wms.mapper;

import com._520it.wms.domain.Brand;
import com._520it.wms.query.BrandQueryObject;
import java.util.List;

public interface BrandMapper {
	void save(Brand entity);
	void update(Brand entity);
	void delete(Long id);
    Brand get(Long id);
	List<Brand> listAll();
    Long queryByConditionCount(BrandQueryObject qo);
    List<Brand> queryByCondition(BrandQueryObject qo);
}