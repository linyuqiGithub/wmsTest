package com._520it.wms.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 登陆检查拦截器
 */
public class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
         Object user = invocation.getInvocationContext().getSession().get("user_in_session");
         //判断是否有登陆
         if(user == null) {
        	 return "login";
         }
		return invocation.invoke();
	}

}
