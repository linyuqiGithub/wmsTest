package com._520it.wms.web.action;

import com._520it.wms.domain.Brand;
import com._520it.wms.domain.Client;
import com._520it.wms.domain.Supplier;
import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IChartService;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.vo.OrderChartVO;
import com._520it.wms.vo.SaleChartVO;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */
public class ChartAction extends BaseAction {
    @Setter
    private IChartService chartService;

    @Setter
    private IBrandService brandService;

    @Setter
    private IClientService clientService;

    @Setter
    private ISupplierService supplierService;
    @Getter
    private OrderChartQueryObject oqo = new OrderChartQueryObject();

    @Getter
    private SaleChartQueryObject  sqo = new SaleChartQueryObject();

    public String orderChart(){
        List<OrderChartVO> list = chartService.queryOrderChart(oqo);
        List<Brand> brands = brandService.listAll();
        List<Supplier> suppliers = supplierService.listAll();
        putContext("list",list);
        putContext("brands",brands);
        putContext("suppliers",suppliers);
        putContext("types",oqo.mapType);
        return "orderChart";
    }
    public String saleChart(){
        List<SaleChartVO> list = chartService.querySaleChart(sqo);
        List<Brand> brands = brandService.listAll();
        List<Client> clients = clientService.listAll();
        putContext("list",list);
        putContext("brands",brands);
        putContext("clients",clients);
        putContext("types",sqo.mapType);
        return "saleChart";
    }
    //线性图
    public String saleChartByLine(){
        //获取所有销售表VO数据,只有三个字段groupBy,totalNumber,totalAmount
        List<SaleChartVO> list = chartService.querySaleChart(sqo);
        //用于存放分组名称（x轴的数据）
        List<String> groupByList = new ArrayList<>();
        //用于存放每个分组对应的总金额(y轴的数据)
        List<BigDecimal> totalAmountList = new ArrayList<>();
        //遍历每个VO数据将数据放入相应集合中
        for (SaleChartVO vo : list) {
            //x轴的数据(分组条件)
            groupByList.add(vo.getGroupBy());
            //y轴的数据(总销售额)
            totalAmountList.add(vo.getTotalAmount());
        }
        //获取查询条件中的sqo的Map中的key对应的值放入context中,其实也就是高级查询下拉列表中显示的数据,也就是按什么类型进行分组
        putContext("groupByName",sqo.mapType.get(sqo.getGroupBy()));
        //将分组条件的数据放入Context中
        putContext("groupByList", JSON.toJSONString(groupByList));
        //将分组对应的总金额放入Context中
        putContext("totalAmount", JSON.toJSONString(totalAmountList));
        return "saleChartByLine";
    }
    //饼状图
    public String saleChartByPie(){
        List<SaleChartVO> list = chartService.querySaleChart(sqo);
        //返回一个数组集合对象
        List<Object[]> dataList = new ArrayList<>();
        for (SaleChartVO vo : list) {
            //饼状图需要的数据类型是数组[分组条件,销售总金额]
            //集合里面的每一个数据都是一个[分组条件,销售总金额]这样的数组(每个数组放两个值)
            dataList.add(new Object[]{vo.getGroupBy(),vo.getTotalAmount()});
        }
        //按什么类型进行分组
        putContext("groupByName",sqo.mapType.get(sqo.getGroupBy()));
        putContext("dataList", JSON.toJSONString(dataList));
        return "saleChartByPie";
    }

    /**
     * 导出销售表
     */
    public void exportSaleExcel(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        sqo.setCurrentPage(null);
        sqo.setPageSize(null);
        List<SaleChartVO> list = chartService.querySaleChart(sqo);
        chartService.export(request,response,list);
    }

    /**
     * 导出订单表
     */
    public void exportOrderExcel(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        sqo.setCurrentPage(null);
        sqo.setPageSize(null);
        List<OrderChartVO> list = chartService.queryOrderChart(oqo);
        chartService.export(request,response,list);
    }
}
