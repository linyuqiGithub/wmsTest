package com._520it.wms.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com._520it.wms.query.SaleChartQueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.domain.SaleAccount;
import com._520it.wms.mapper.SaleAccountMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.service.ISaleAccountService;
import lombok.Setter;
public class SaleAccountServiceImpl implements ISaleAccountService {
	@Setter
	private SaleAccountMapper saleAccountMapper;
	
	public void  delete(Long id) {
		  saleAccountMapper.delete(id);
	}

	public void save(SaleAccount entity) {
		  saleAccountMapper.save(entity);
	}

	public SaleAccount get(Long id) {
		return saleAccountMapper.get(id);
	}

	public List<SaleAccount> listAll() {
		return saleAccountMapper.listAll();
	}

	public void update(SaleAccount entity) {
		  saleAccountMapper.update(entity);
	}

	@Override
	public PageResult queryByConditionPage(SaleChartQueryObject qo) {
		Long count = saleAccountMapper.queryByConditionCount(qo);
		if(count<=0){
			return new PageResult(1, qo.getPageSize(), 0, Collections.EMPTY_LIST);
		}
		List<SaleAccount> data = saleAccountMapper.queryByCondition(qo);
		PageResult pageResult = new PageResult(qo.getCurrentPage(),qo.getPageSize(), count.intValue(), data);
		return pageResult;
	}
}
