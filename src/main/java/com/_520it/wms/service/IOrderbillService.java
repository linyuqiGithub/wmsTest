package com._520it.wms.service;
import java.util.List;
import com._520it.wms.domain.Orderbill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.OrderbillQueryObject;

public interface IOrderbillService {
	void delete(Long id);
	void save(Orderbill entity);
    Orderbill get(Long id);
    List<Orderbill> listAll();
	void update(Orderbill entity);
	PageResult queryByConditionPage(OrderbillQueryObject qo);
	void audit(Orderbill entity);
}
