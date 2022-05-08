package com._520it.wms.util;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.Permission;
import org.apache.struts2.ServletActionContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 抽取业务层中表现层的代码
 * Created by Administrator on 2017/5/31.
 */
public class UserContext {
    public static void setCurrentUser(Employee currentUser){
            if(currentUser == null){
                //删除session中的信息
                ServletActionContext.getRequest().getSession().removeAttribute("user_in_session");
            }else {
                ServletActionContext.getRequest().getSession().setAttribute("user_in_session",currentUser);
            }
    }

    public static Employee getCurrentUser(){
        Employee curentUser = (Employee) ServletActionContext.getRequest().getSession().getAttribute("user_in_session");
        return curentUser;
    }

    public static void setCurrentPermission(List<Permission> permissions){
            if(permissions == null){
                //删除session中的信息
                ServletActionContext.getRequest().getSession().removeAttribute("permission_in_session");
            }else {
                Set<String> permissionSet = new HashSet<>();
                for (Permission permission : permissions) {
                    permissionSet.add(permission.getExpression());
                }
                ServletActionContext.getRequest().getSession().setAttribute("permission_in_session",permissionSet);
            }
    }

    public static Set<String> getCurrentPermission(){
        return (Set<String>) ServletActionContext.getRequest().getSession().getAttribute("permission_in_session");
    }

}
