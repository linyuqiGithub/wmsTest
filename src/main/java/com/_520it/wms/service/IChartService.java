package com._520it.wms.service;

import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.vo.OrderChartVO;
import com._520it.wms.vo.SaleChartVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IChartService {
    List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo);
    List<SaleChartVO> querySaleChart(SaleChartQueryObject qo);

    void export(HttpServletRequest request, HttpServletResponse response,List<?> list);

}
