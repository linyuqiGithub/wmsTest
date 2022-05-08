package com._520it.wms.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.domain.Client;
import com._520it.wms.mapper.ClientMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ClientQueryObject;
import com._520it.wms.service.IClientService;
import lombok.Setter;
public class ClientServiceImpl implements IClientService {
	@Setter
	private ClientMapper clientMapper;
	
	public void  delete(Long id) {
		  clientMapper.delete(id);
	}

	public void save(Client entity) {
		  clientMapper.save(entity);
	}

	public Client get(Long id) {
		return clientMapper.get(id);
	}

	public List<Client> listAll() {
		return clientMapper.listAll();
	}

	public void update(Client entity) {
		  clientMapper.update(entity);
	}

	@Override
	public PageResult queryByConditionPage(ClientQueryObject qo) {
		Long count = clientMapper.queryByConditionCount(qo);
		if(count<=0){
			return new PageResult(1, qo.getPageSize(), 0, Collections.EMPTY_LIST);
		}
		List<Client> data = clientMapper.queryByCondition(qo);
		PageResult pageResult = new PageResult(qo.getCurrentPage(),qo.getPageSize(), count.intValue(), data);
		return pageResult;
	}
}
