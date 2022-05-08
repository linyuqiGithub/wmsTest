package com._520it.wms.util;

import java.lang.reflect.Method;

public class PermissionUtil {
   public static String buildPermission(Method m){
   	   //获取该方法的类名
	   String ClassName = m.getDeclaringClass().getName();
	   //获取该方法的方法名
	   String methodName = m.getName();
	   //拼接表达式:类名:方法名
	   return ClassName+":"+methodName;
   }
}
