package com._520it.wms.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.domain.Supplier;
import com._520it.wms.mapper.SupplierMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.SupplierQueryObject;
import com._520it.wms.service.ISupplierService;
import lombok.Setter;
public class SupplierServiceImpl implements ISupplierService {
	@Setter
	private SupplierMapper supplierMapper;
	
	public void  delete(Long id) {
		  supplierMapper.delete(id);
	}

	public void save(Supplier entity) {
		  supplierMapper.save(entity);
	}

	public Supplier get(Long id) {
		return supplierMapper.get(id);
	}

	public List<Supplier> listAll() {
		return supplierMapper.listAll();
	}

	public void update(Supplier entity) {
		  supplierMapper.update(entity);
	}

	@Override
	public PageResult queryByConditionPage(SupplierQueryObject qo) {
		Long count = supplierMapper.queryByConditionCount(qo);
		if(count<=0){
			return new PageResult(1, qo.getPageSize(), 0, Collections.EMPTY_LIST);
		}
		List<Supplier> data = supplierMapper.queryByCondition(qo);
		PageResult pageResult = new PageResult(qo.getCurrentPage(),qo.getPageSize(), count.intValue(), data);
		return pageResult;
	}
}
