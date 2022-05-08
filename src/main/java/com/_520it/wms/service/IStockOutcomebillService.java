package com._520it.wms.service;

import com._520it.wms.domain.StockIncomebill;
import com._520it.wms.domain.StockOutcomebill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.StockIncomebillQueryObject;
import com._520it.wms.query.StockOutcomebillQueryObject;

import java.util.List;

public interface IStockOutcomebillService {
	void delete(Long id);
	void save(StockOutcomebill entity);
    StockOutcomebill get(Long id);
    List<StockOutcomebill> listAll();
	void update(StockOutcomebill entity);
	PageResult queryByConditionPage(StockOutcomebillQueryObject qo);
	void audit(StockOutcomebill entity);
}
