package com._520it.wms.web.action;

import com.opensymphony.xwork2.ActionContext;

public class LogoutAction extends BaseAction {

	private static final long serialVersionUID = 1L;
    @Override
    public String execute() throws Exception {
     	//清空session中的数据
        ActionContext.getContext().getSession().clear();
    	return "login";
    }
}
