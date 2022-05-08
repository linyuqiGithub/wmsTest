package com._520it.wms.mapper;

import com._520it.wms.domain.Orderbill;
import com._520it.wms.domain.OrderbillItem;
import com._520it.wms.query.OrderbillQueryObject;
import java.util.List;

public interface OrderbillMapper {
	void save(Orderbill entity);
	void update(Orderbill entity);
	void delete(Long id);
    Orderbill get(Long id);
	List<Orderbill> listAll();
    Long queryByConditionCount(OrderbillQueryObject qo);
    List<Orderbill> queryByCondition(OrderbillQueryObject qo);
    void saveItem(OrderbillItem item);
    List<OrderbillItem> selectItemsBybillId(Long billId);
    void deleteItemBybillId(Long id);
    void audit(Orderbill entity);
}