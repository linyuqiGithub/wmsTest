package com._520it.wms.web.action;


import com._520it.wms.domain.Permission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.PermissionQueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class PermissionAction extends BaseAction {
	private static final long serialVersionUID = 1L;
    @Setter
    IPermissionService permissionService;
	@Getter
	Permission permission = new Permission();
    @Getter
    PermissionQueryObject qo = new PermissionQueryObject();

	@Setter
	List<Long> ids = new ArrayList<>();

	@Override
	@RequiredPermission("权限查询")
	public String execute() throws Exception {
		PageResult pageResult = permissionService.queryByCondition(qo);
		putContext("pageResult", pageResult);
		return "list";
	}
	@RequiredPermission("权限加载")
	public String reload() throws Exception {
		try{
		permissionService.reload();
			putMsg("加载成功");
		}catch(Exception e){
			e.printStackTrace();
			putMsg("加载失败，请联系管理员");
		}
		return NONE;
	}
	@RequiredPermission("权限删除")
	public String delete() throws Exception {
		try {
			permissionService.delete(permission.getId());
			String msg = "删除成功";
			putMsg(msg);
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "删除失败,请联系管理员";
			putMsg(msg);
		}
		return NONE;
	}

	@RequiredPermission("权限批量删除")
	public String batchDelete() throws Exception {
		try {
			permissionService.batchDelete(ids);
			String msg = "删除成功";
			putMsg(msg);
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "删除失败，请联系管理员";
			putMsg(msg);
		}
		return NONE;
	}

}
