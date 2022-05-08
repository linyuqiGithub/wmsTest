package com._520it.wms.web.action;

import com._520it.wms.domain.Supplier;
import com._520it.wms.query.SupplierQueryObject;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class SupplierAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private ISupplierService supplierService;

	@Getter
	private SupplierQueryObject qo=new SupplierQueryObject();

	@Getter
	private Supplier supplier = new Supplier();
	
	@RequiredPermission("供应商列表")
	public String execute(){
		try {
			putContext("pageResult", supplierService.queryByConditionPage(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	@RequiredPermission("供应商编辑")
	public String input() {
		try {
			if (supplier.getId() != null) {
                supplier = supplierService.get(supplier.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "input";
	}

	@RequiredPermission("供应商保存/更新")
	public String saveOrUpdate() {
		try {
			if (supplier.getId() == null) {
                supplierService.save(supplier);
				addActionMessage("增加成功");
            } else {
                supplierService.update(supplier);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("供应商删除")
	public String delete()  throws  Exception {
		try {
			if (supplier.getId() != null) {
                supplierService.delete(supplier.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

}
