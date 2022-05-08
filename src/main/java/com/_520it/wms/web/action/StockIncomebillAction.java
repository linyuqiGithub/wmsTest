package com._520it.wms.web.action;

import com._520it.wms.domain.StockIncomebill;
import com._520it.wms.query.StockIncomebillQueryObject;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IStockIncomebillService;
import com._520it.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class StockIncomebillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IStockIncomebillService stockIncomebillService;

	@Setter
	private IDepotService depotService;
	@Getter
	private StockIncomebillQueryObject qo=new StockIncomebillQueryObject();

	@Getter
	private StockIncomebill stockIncomebill = new StockIncomebill();
	
	@RequiredPermission("采购入库单列表")
	public String execute(){
		try {
			putContext("pageResult", stockIncomebillService.queryByConditionPage(qo));
			putContext("depots",depotService.listAll());
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	@RequiredPermission("采购入库单编辑")
	public String input() {
		try {
			if (stockIncomebill.getId() != null) {
                stockIncomebill = stockIncomebillService.get(stockIncomebill.getId());
            }
            putContext("depots",depotService.listAll());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "input";
	}

	@RequiredPermission("采购入库单保存/更新")
	public String saveOrUpdate() {
		try {
			if (stockIncomebill.getId() == null) {
                stockIncomebillService.save(stockIncomebill);
				addActionMessage("增加成功");
            } else {
                stockIncomebillService.update(stockIncomebill);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("采购入库单删除")
	public String delete()  throws  Exception {
		try {
			if (stockIncomebill.getId() != null) {
                stockIncomebillService.delete(stockIncomebill.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

	@RequiredPermission("采购入库单审核")
	public String audit()  throws  Exception {
		try {
			if (stockIncomebill.getId() != null) {
				stockIncomebillService.audit(stockIncomebill);
				putMsg("审核成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

	@RequiredPermission("采购入库单查看")
	public String show() {
		putContext("depots",depotService.listAll());
		try {
			if (stockIncomebill.getId() != null) {
				stockIncomebill = stockIncomebillService.get(stockIncomebill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "show";
	}
}
