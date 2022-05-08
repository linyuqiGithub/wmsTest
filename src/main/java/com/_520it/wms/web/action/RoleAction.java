package com._520it.wms.web.action;


import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.EmloyeeQueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class RoleAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    IRoleService roleService;
    @Setter
    IPermissionService permissionService;
    @Setter
    ISystemMenuService systemMenuService;
    @Getter
    EmloyeeQueryObject qo = new EmloyeeQueryObject();
    @Getter
    Role role = new Role();

    @Setter
    List<Long> ids = new ArrayList<>();

    @InputConfig(methodName = "input")

    @Override
    @RequiredPermission("角色查询")
    public String execute() throws Exception {
        PageResult pageResult = roleService.queryByCondition(qo);
        putContext("pageResult", pageResult);
        return "list";
    }

    @Override
    @RequiredPermission("角色编辑")
    public String input() throws Exception {
        if (role.getId() != null) {
            role = roleService.get(role.getId());
        }
        List<Permission> permissions = permissionService.list();
        putContext("permissions", permissions);
        putContext("menus", systemMenuService.listChildrenMenu());
        return "input";
    }

    @RequiredPermission("角色保存")
    public String saveOrUpdate() throws Exception {
        try {
            //int a = 1/0;
            if (role.getId() != null) {
                roleService.update(role);
                addActionMessage("修改成功");
            } else {
                roleService.save(role);
                addActionMessage("新增成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败" + e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("角色删除")
    public String delete() throws Exception {
        try {
            roleService.delete(role.getId());
            String msg = "删除成功";
            putMsg(msg);
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败,请联系管理员");
        }
        return NONE;
    }

    @RequiredPermission("角色批量删除")
    public String batchDelete() throws Exception {
        try {
            roleService.batchDelete(ids);
            String msg = "删除成功";
            putMsg(msg);
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败,请联系管理员");
        }
        return NONE;
    }
}
