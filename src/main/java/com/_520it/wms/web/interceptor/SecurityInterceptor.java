package com._520it.wms.web.interceptor;

import com._520it.wms.domain.Employee;
import com._520it.wms.util.PermissionUtil;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.lang.reflect.Method;
import java.util.Set;

public class SecurityInterceptor implements Interceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Employee user = (Employee) invocation.getInvocationContext().getSession().get("user_in_session");
		//判断该用户是否为超级管理员
		if(user.isAdmin()){
			//放行
			return invocation.invoke();
		}
		Object action = invocation.getAction();//action对象
		String methodName = invocation.getProxy().getMethod();
		Method method = action.getClass().getMethod(methodName);
		//判断该方法有没有粘贴注解
		RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
		//如果该方法没有粘贴注解
		if(annotation == null){
			//放行
			return invocation.invoke();
		}
		//获取该用户权限集合
		Set<String> permissionSet = (Set<String>) invocation.getInvocationContext().getSession().get("permission_in_session");
		String expression = PermissionUtil.buildPermission(method);
		//判断该用户权限集合中是否包含执行该action的权限
		if(permissionSet.contains(expression)){
			//包含,则放行
			return invocation.invoke();
		}
		//否则该用户没有权限,返回无权限页面
		return "nopermission";
	}

}
