package com._520it.wms.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.domain.Depot;
import com._520it.wms.mapper.DepotMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.DepotQueryObject;
import com._520it.wms.service.IDepotService;
import lombok.Setter;
public class DepotServiceImpl implements IDepotService {
	@Setter
	private DepotMapper depotMapper;
	
	public void  delete(Long id) {
		  depotMapper.delete(id);
	}

	public void save(Depot entity) {
		  depotMapper.save(entity);
	}

	public Depot get(Long id) {
		return depotMapper.get(id);
	}

	public List<Depot> listAll() {
		return depotMapper.listAll();
	}

	public void update(Depot entity) {
		  depotMapper.update(entity);
	}

	@Override
	public PageResult queryByConditionPage(DepotQueryObject qo) {
		Long count = depotMapper.queryByConditionCount(qo);
		if(count<=0){
			return new PageResult(1, qo.getPageSize(), 0, Collections.EMPTY_LIST);
		}
		List<Depot> data = depotMapper.queryByCondition(qo);
		PageResult pageResult = new PageResult(qo.getCurrentPage(),qo.getPageSize(), count.intValue(), data);
		return pageResult;
	}
}
