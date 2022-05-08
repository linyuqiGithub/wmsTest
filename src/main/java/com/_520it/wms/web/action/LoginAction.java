package com._520it.wms.web.action;

import com._520it.wms.domain.Employee;
import com._520it.wms.service.IEmployeeService;

import lombok.Setter;

public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Setter
	IEmployeeService employeeService;
	@Setter
	private String username;
	@Setter
	private String password;
    @Override
    public String execute() throws Exception {
        try {
        	//login方法中若查询不到该用户,则会抛出一个异常
			Employee emp = employeeService.login(username,password);
		} catch (Exception e) {
        	//出现异常,表示账号密码错误,返回登录界面
			super.addActionError(e.getMessage());
			return "login";
		}
		//成功则跳转
    	return SUCCESS;
    }   
}
