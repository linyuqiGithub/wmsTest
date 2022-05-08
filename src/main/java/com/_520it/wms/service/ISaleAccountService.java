package com._520it.wms.service;
import java.util.List;
import com._520it.wms.domain.SaleAccount;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.SaleChartQueryObject;
public interface ISaleAccountService {
	void delete(Long id);
	void save(SaleAccount entity);
    SaleAccount get(Long id);
    List<SaleAccount> listAll();
	void update(SaleAccount entity);
	PageResult queryByConditionPage(SaleChartQueryObject qo);
}
