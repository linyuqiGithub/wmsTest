package com._520it.wms.service.impl;


import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.domain.SystemMenu;
import com._520it.wms.mapper.RoleMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IRoleService;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

public class RoleServiceImpl implements IRoleService {


    @Setter
    private RoleMapper roleMapper;

    @Override
    public void save(Role role) {
        roleMapper.save(role);
        List<Permission> permissions = role.getPermissions();
        //处理角色和权限的中间表关系
        for (Permission permission : permissions) {
            roleMapper.inserRelation(role.getId(), permission.getId());
        }

        List<SystemMenu> menus = role.getMenus();
        //处理角色和菜单的中间表关系
        for (SystemMenu menu : menus) {
            roleMapper.inserMenusRelation(role.getId(),menu.getId());
        }
    }

    @Override
    public void update(Role role) {
        roleMapper.update(role);
        //删除角色和权限的中间表关系
        roleMapper.deleteRelation(role.getId());
        //删除角色和菜单的中间表关系
        roleMapper.deleteMenusRelation(role.getId());
        List<Permission> permissions = role.getPermissions();
        for (Permission permission : permissions) {
            //添加角色和权限的中间表关系
            roleMapper.inserRelation(role.getId(), permission.getId());
        }
        List<SystemMenu> menus = role.getMenus();
        for (SystemMenu menu : menus) {
            //添加角色和菜单的中间表关系
            roleMapper.inserMenusRelation(role.getId(),menu.getId());
        }
    }

    @Override
    public void delete(Long id) {
        //删除角色与权限的中间表关系
        roleMapper.deleteRelation(id);
        //删除角色和菜单的中间表关系
        roleMapper.deleteMenusRelation(id);
        //删除角色和员工的关系
        roleMapper.deleteRelation2(id);
        //再删除自己
        roleMapper.delete(id);

    }

    @Override
    public Role get(Long id) {
        return roleMapper.get(id);
    }

    @Override
    public List<Role> list() {
        return roleMapper.list();
    }

    @Override
    public Long queryByConditionCount(QueryObject qo) {
        return roleMapper.queryByConditionCount(qo);
    }

    @Override
    public PageResult queryByCondition(QueryObject qo) {
        Long count = roleMapper.queryByConditionCount(qo);
        if (count == 0) {
            return new PageResult(1, qo.getPageSize(), 0, Collections.EMPTY_LIST);
        }
        List<Role> data = roleMapper.queryByCondition(qo);

        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), count.intValue(), data);
    }

    @Override
    public void batchDelete(List<Long> list) {
        for (Long id : list) {
            //删除角色与权限的中间表关系
            roleMapper.deleteRelation(id);
            //删除角色和菜单的中间表关系
            roleMapper.deleteMenusRelation(id);
            //删除角色和员工的关系
            roleMapper.deleteRelation2(id);
        }
        roleMapper.batchDelete(list);
    }


}
