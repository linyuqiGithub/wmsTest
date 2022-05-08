package com._520it.wms.service;
import java.util.List;
import com._520it.wms.domain.Client;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ClientQueryObject;

public interface IClientService {
	void delete(Long id);
	void save(Client entity);
    Client get(Long id);
    List<Client> listAll();
	void update(Client entity);
	PageResult queryByConditionPage(ClientQueryObject qo);
}
