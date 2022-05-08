package com._520it.wms.web.action;

import com._520it.wms.domain.Department;
import com._520it.wms.domain.Employee;
import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.EmloyeeQueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Setter
	IEmployeeService employeeService;
    @Setter
    IDepartmentService departmentService;
    @Setter
    IRoleService roleService;
    @Setter
	List<Long> ids = new ArrayList<>();
	@Getter
	Employee employee = new Employee();
    @Getter
    EmloyeeQueryObject qo = new EmloyeeQueryObject();

	/*页面发生异常时,由于defaultStack中的workflow拦截器,使用addActionError后会默认跳转到input视图,
	但是此时跳转到input视图时并没有执行input方法,所以导致了没有dept和role数据,页面会报错
	所以通过注解是的发生异常时执行input方法，这样执行过input方法再跳转到input视图就不会报错*/
	@InputConfig(methodName = "input")
	@Override
	@RequiredPermission("员工查询")
	public String execute() throws Exception {
		List<Department> depts = departmentService.list();
		PageResult pageResult = employeeService.queryByCondition(qo);
		putContext("pageResult", pageResult);
		putContext("depts", depts);
		return "list";
	}
	@Override
	@RequiredPermission("员工编辑")
	public String input() throws Exception {
		try {
			List<Department> depts = departmentService.list();
			putContext("depts", depts);
			if (employee.getId() != null) {
                employee = employeeService.get(employee.getId());
            }
			List<Role> roles = roleService.list();
			putContext("roles", roles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	@RequiredPermission("员工保存")
	public String saveOrUpdate() throws Exception {
		try {
			if (employee.getId() != null) {
                employeeService.update(employee);
                //将信息共享到jsp页面
                addActionMessage("修改成功");
            } else {
                employeeService.save(employee);
                addActionMessage("新增成功");
            }
			//int a = 1 / 0;
		} catch (Exception e) {
			 e.printStackTrace();
			//把错误信息共享到jsp页面中
			addActionError("操作失败:异常原因:"+e.getMessage());
		}
		//通过重定向重新执行execute方法
		return SUCCESS;
	}
	@RequiredPermission("员工删除")
	public String delete() throws Exception {
        try {
            employeeService.delete(employee.getId());
            String msg = "删除成功";
            //通过ajax响应,封装在父类BaseAction的putMsg方法中
            putMsg(msg);
        } catch (Exception e) {
            String msg = "删除失败,请联系管理员";
            putMsg(msg);
            e.printStackTrace();
        }
        //删除操作使用的是发送ajax异步请求,所以不需要有返回视图，已经发送了ajax响应
        return NONE;
	}
    @RequiredPermission("员工批量删除")
	public String batchDelete() throws Exception {
        try {
            employeeService.batchDelete(ids);
            String msg = "删除成功";
            putMsg(msg);
        } catch (Exception e) {
            String msg = "删除失败,请联系管理员";
            putMsg(msg);
            e.printStackTrace();
        }

        return NONE;
	}
}
