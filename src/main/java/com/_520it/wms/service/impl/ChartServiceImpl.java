package com._520it.wms.service.impl;

import com._520it.wms.domain.Brand;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.mapper.ChartMapper;
import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.service.IChartService;
import com._520it.wms.util.DownloadFileUtil;
import com._520it.wms.util.JxlUtil;
import com._520it.wms.vo.OrderChartVO;
import com._520it.wms.vo.SaleChartVO;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */
public class ChartServiceImpl implements IChartService {
    @Setter
    private ChartMapper chartMapper;
    //订货报表
    @Override
    public List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo) {
        return chartMapper.queryOrderChart(qo);
    }
    //销售报表
    @Override
    public List<SaleChartVO> querySaleChart(SaleChartQueryObject qo) {
        return chartMapper.querySaleChart(qo);
    }
    //导出报表
    @Override
    public void export(HttpServletRequest request, HttpServletResponse response,List<?> list) {
        // 文件新名
        String newFileName = "excel.xls";
        String pathName = "D:/usr/" + newFileName;
        String title[] = new String[0];
        if (list.get(0).getClass() == SaleChartVO.class) {
            title = new String[]{"分组类型", "分组类型", "采购总金额", "毛利润"};
        }else if(list.get(0).getClass() == OrderChartVO.class){
            title = new String[]{"分组类型", "分组类型", "采购总金额"};
        }else {
            title = new String[]{"编号","货品","仓库","品牌","库存价格","库存数量","库存总金额"};
        }
        //文件保存在指定位置
        JxlUtil.exportExcel(pathName, title, list);
        //获取文件流返回给客户端
        DownloadFileUtil.downloadFile(response, request, pathName);
    }
}
