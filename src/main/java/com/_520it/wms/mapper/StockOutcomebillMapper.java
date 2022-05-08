package com._520it.wms.mapper;

import com._520it.wms.domain.StockIncomebill;
import com._520it.wms.domain.StockIncomebillItem;
import com._520it.wms.domain.StockOutcomebill;
import com._520it.wms.domain.StockOutcomebillItem;
import com._520it.wms.query.StockIncomebillQueryObject;
import com._520it.wms.query.StockOutcomebillQueryObject;

import java.util.List;

public interface StockOutcomebillMapper {
	void save(StockOutcomebill entity);
	void update(StockOutcomebill entity);
	void delete(Long id);
    StockOutcomebill get(Long id);
	List<StockOutcomebill> listAll();
    Long queryByConditionCount(StockOutcomebillQueryObject qo);
    List<StockOutcomebill> queryByCondition(StockOutcomebillQueryObject qo);
	void saveItem(StockOutcomebillItem item);
	List<StockOutcomebillItem> selectItemsBybillId(Long billId);
	void deleteItemBybillId(Long billId);
	void audit(StockOutcomebill entity);
}