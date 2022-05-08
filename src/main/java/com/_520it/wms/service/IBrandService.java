package com._520it.wms.service;
import java.util.List;
import com._520it.wms.domain.Brand;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.BrandQueryObject;

public interface IBrandService {
	void delete(Long id);
	void save(Brand entity);
    Brand get(Long id);
    List<Brand> listAll();
	void update(Brand entity);
	PageResult queryByConditionPage(BrandQueryObject qo);
}
