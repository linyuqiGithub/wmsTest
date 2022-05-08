package com._520it.wms.mapper;

import com._520it.wms.domain.SaleAccount;
import com._520it.wms.query.SaleChartQueryObject;
import java.util.List;

public interface SaleAccountMapper {
	void save(SaleAccount entity);
	void update(SaleAccount entity);
	void delete(Long id);
    SaleAccount get(Long id);
	List<SaleAccount> listAll();
    Long queryByConditionCount(SaleChartQueryObject qo);
    List<SaleAccount> queryByCondition(SaleChartQueryObject qo);
}