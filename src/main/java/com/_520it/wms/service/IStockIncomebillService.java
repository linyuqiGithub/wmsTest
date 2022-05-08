package com._520it.wms.service;
import java.util.List;
import com._520it.wms.domain.StockIncomebill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.StockIncomebillQueryObject;

public interface IStockIncomebillService {
	void delete(Long id);
	void save(StockIncomebill entity);
    StockIncomebill get(Long id);
    List<StockIncomebill> listAll();
	void update(StockIncomebill entity);
	PageResult queryByConditionPage(StockIncomebillQueryObject qo);
	void audit(StockIncomebill entity);
}
