package com._520it.wms.web.action;

import com._520it.wms.domain.ProductStock;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductStockQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IChartService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.util.RequiredPermission;

import com._520it.wms.vo.OrderChartVO;
import com._520it.wms.vo.ProductStockChartVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductStockAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IProductStockService productStockService;

	@Setter
	private IDepotService depotService;

	@Setter
	private IBrandService brandService;

	@Setter
	private IChartService chartService;

	@Getter
	private ProductStockQueryObject qo=new ProductStockQueryObject();


	
	@RequiredPermission("产品库存列表")
	public String execute(){
		try {
			putContext("pageResult", productStockService.queryByConditionPage(qo));
            putContext("depots",depotService.listAll());
            putContext("brands",brandService.listAll());
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	/**
	 * 导出库存表
	 */
	public void exportProductStockExcel(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		qo.setCurrentPage(null);
		qo.setPageSize(null);
		PageResult pageResult = productStockService.queryByConditionPage(qo);
		List<ProductStock> data = (List<ProductStock>) pageResult.getData();
		List<ProductStockChartVO> list = productStockService.CastType(data);
		chartService.export(request,response,list);
	}



}
