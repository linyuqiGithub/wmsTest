package com._520it.wms.web.action;

import com._520it.wms.domain.Orderbill;
import com._520it.wms.query.OrderbillQueryObject;
import com._520it.wms.service.IOrderbillService;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class OrderbillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IOrderbillService orderbillService;

	@Setter
	private ISupplierService supplierService;

	@Getter
	private OrderbillQueryObject qo=new OrderbillQueryObject();

	@Getter
	private Orderbill orderbill = new Orderbill();
	
	@RequiredPermission("产品订单列表")
	public String execute(){
		try {
			putContext("pageResult", orderbillService.queryByConditionPage(qo));
			putContext("suppliers",supplierService.listAll());
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	@RequiredPermission("产品订单编辑")
	public String input() {
		putContext("suppliers",supplierService.listAll());
		try {
			if (orderbill.getId() != null) {
                orderbill = orderbillService.get(orderbill.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "input";
	}

	@RequiredPermission("产品订单保存/更新")
	public String saveOrUpdate() {
		try {
			if (orderbill.getId() == null) {
                orderbillService.save(orderbill);
				addActionMessage("增加成功");
            } else {
                orderbillService.update(orderbill);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("产品订单删除")
	public String delete()  throws  Exception {
		try {
			if (orderbill.getId() != null) {
                orderbillService.delete(orderbill.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

	@RequiredPermission("产品订单审核")
	public String audit()  throws  Exception {
		try {
			if (orderbill.getId() != null) {
				orderbillService.audit(orderbill);
				putMsg("审核成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

	@RequiredPermission("产品订单查看")
	public String show() {
		try {
			if (orderbill.getId() != null) {
				orderbill = orderbillService.get(orderbill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "show";
	}

}
