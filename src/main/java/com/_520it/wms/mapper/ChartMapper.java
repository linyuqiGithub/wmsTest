package com._520it.wms.mapper;

import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.vo.OrderChartVO;
import com._520it.wms.vo.SaleChartVO;

import java.util.List;

/**
 * 处理报表的Mapper
 * Created by Administrator on 2017/6/7.
 */
public interface ChartMapper {
    List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo);

    List<SaleChartVO> querySaleChart(SaleChartQueryObject qo);
}
