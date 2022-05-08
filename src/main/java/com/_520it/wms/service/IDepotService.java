package com._520it.wms.service;
import java.util.List;
import com._520it.wms.domain.Depot;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.DepotQueryObject;

public interface IDepotService {
	void delete(Long id);
	void save(Depot entity);
    Depot get(Long id);
    List<Depot> listAll();
	void update(Depot entity);
	PageResult queryByConditionPage(DepotQueryObject qo);
}
