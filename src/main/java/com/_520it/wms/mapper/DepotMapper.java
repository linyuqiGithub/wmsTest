package com._520it.wms.mapper;

import com._520it.wms.domain.Depot;
import com._520it.wms.query.DepotQueryObject;
import java.util.List;

public interface DepotMapper {
	void save(Depot entity);
	void update(Depot entity);
	void delete(Long id);
    Depot get(Long id);
	List<Depot> listAll();
    Long queryByConditionCount(DepotQueryObject qo);
    List<Depot> queryByCondition(DepotQueryObject qo);
}