package com._520it.wms.web.action;

import com._520it.wms.domain.Depot;
import com._520it.wms.query.DepotQueryObject;
import com._520it.wms.service.IDepotService;
import com._520it.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class DepotAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IDepotService depotService;

	@Getter
	private DepotQueryObject qo=new DepotQueryObject();

	@Getter
	private Depot depot = new Depot();
	
	@RequiredPermission("仓库管理列表")
	public String execute(){
		try {
			putContext("pageResult", depotService.queryByConditionPage(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	@RequiredPermission("仓库管理编辑")
	public String input() {
		try {
			if (depot.getId() != null) {
                depot = depotService.get(depot.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "input";
	}

	@RequiredPermission("仓库管理保存/更新")
	public String saveOrUpdate() {
		try {
			if (depot.getId() == null) {
                depotService.save(depot);
				addActionMessage("增加成功");
            } else {
                depotService.update(depot);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("仓库管理删除")
	public String delete()  throws  Exception {
		try {
			if (depot.getId() != null) {
                depotService.delete(depot.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

}
