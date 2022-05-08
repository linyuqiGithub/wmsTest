package com._520it.wms.web.action;

import com._520it.wms.domain.StockOutcomebill;
import com._520it.wms.query.StockOutcomebillQueryObject;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IStockOutcomebillService;
import com._520it.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class StockOutcomebillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IStockOutcomebillService stockOutcomebillService;

	@Setter
	private IDepotService depotService;

	@Setter
	private IClientService clientService;
	@Getter
	private StockOutcomebillQueryObject qo=new StockOutcomebillQueryObject();

	@Getter
	private StockOutcomebill stockOutcomebill = new StockOutcomebill();
	
	@RequiredPermission("销售出库单列表")
	public String execute(){
		try {
			putContext("pageResult", stockOutcomebillService.queryByConditionPage(qo));
			putContext("depots",depotService.listAll());
			putContext("clients",clientService.listAll());
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	@RequiredPermission("销售出库单编辑")
	public String input() {
		try {
			if (stockOutcomebill.getId() != null) {
                stockOutcomebill = stockOutcomebillService.get(stockOutcomebill.getId());
            }
            putContext("depots",depotService.listAll());
			putContext("clients",clientService.listAll());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "input";
	}

	@RequiredPermission("销售出库单保存/更新")
	public String saveOrUpdate() {
		try {
			if (stockOutcomebill.getId() == null) {
                stockOutcomebillService.save(stockOutcomebill);
				addActionMessage("增加成功");
            } else {
                stockOutcomebillService.update(stockOutcomebill);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("销售出库单删除")
	public String delete()  throws  Exception {
		try {
			if (stockOutcomebill.getId() != null) {
                stockOutcomebillService.delete(stockOutcomebill.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

	@RequiredPermission("销售出库单审核")
	public String audit()  throws  Exception {
		try {
			if (stockOutcomebill.getId() != null) {
				stockOutcomebillService.audit(stockOutcomebill);
				putMsg("审核成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

	@RequiredPermission("销售出库单查看")
	public String show() {
		putContext("depots",depotService.listAll());
		try {
			if (stockOutcomebill.getId() != null) {
				stockOutcomebill = stockOutcomebillService.get(stockOutcomebill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "show";
	}
}
