package com._520it.wms.web.action;

import com._520it.wms.domain.Department;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.DepartmentQueryObject;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.util.RequiredPermission;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class DepartmentAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Setter
	IDepartmentService departmentService;

	@Getter
	Department department = new Department();

	@Getter
	DepartmentQueryObject qo = new DepartmentQueryObject();

	@Setter
	List<Long> ids = new ArrayList<>();

	@InputConfig(methodName = "input")

	@Override
	@RequiredPermission("部门查询")
	public String execute() throws Exception {
		PageResult result = departmentService.queryByCondition(qo);
		putContext("pageResult",result);
		return "list";
	}
	@Override
	@RequiredPermission("部门编辑")
	public String input() throws Exception {
		if (department.getId() != null) {
			department = departmentService.get(department.getId());
		}
		return "input";
	}
    @RequiredPermission("部门保存")
	public String saveOrUpdate() throws Exception {
		try {
			//int a = 1/0;
 			if (department.getId() != null) {
                departmentService.update(department);
                addActionMessage("修改成功");
            } else {
                departmentService.save(department);
                addActionMessage("新增成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("操作失败!失败原因:"+e.getMessage());
		}
		return SUCCESS;
	}
    @RequiredPermission("部门删除")
	public String delete() throws Exception {
		try {
			departmentService.delete(department.getId());
			String msg = "删除成功";
			putMsg(msg);
		} catch (Exception e){
			String msg = "删除失败,请联系管理员";
			putMsg(msg);
		}
		return NONE;
	}

	@RequiredPermission("部门批量删除")
	public String batchDelete() throws Exception {
		try {
			departmentService.batchDelete(ids);
			String msg = "删除成功";
			putMsg(msg);
		} catch (Exception e) {
			String msg = "删除失败,请联系管理员";
			putMsg(msg);
		}
		return NONE;
	}
}
