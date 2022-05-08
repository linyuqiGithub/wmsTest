package com._520it.wms.mapper;

import com._520it.wms.domain.StockIncomebill;
import com._520it.wms.domain.StockIncomebillItem;
import com._520it.wms.query.StockIncomebillQueryObject;
import java.util.List;

public interface StockIncomebillMapper {
	void save(StockIncomebill entity);
	void update(StockIncomebill entity);
	void delete(Long id);
    StockIncomebill get(Long id);
	List<StockIncomebill> listAll();
    Long queryByConditionCount(StockIncomebillQueryObject qo);
    List<StockIncomebill> queryByCondition(StockIncomebillQueryObject qo);
	void saveItem(StockIncomebillItem item);
	List<StockIncomebillItem> selectItemsBybillId(Long billId);
	void deleteItemBybillId(Long billId);
	void audit(StockIncomebill entity);
}