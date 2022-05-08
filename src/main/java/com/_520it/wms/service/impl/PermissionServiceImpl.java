package com._520it.wms.service.impl;

import java.lang.reflect.Method;
import java.util.*;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com._520it.wms.domain.Permission;
import com._520it.wms.mapper.PermissionMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.util.PermissionUtil;
import com._520it.wms.util.RequiredPermission;
import com._520it.wms.web.action.BaseAction;

import lombok.Setter;

public class PermissionServiceImpl implements IPermissionService, ApplicationContextAware {

    private ApplicationContext cxt;
    @Setter
    private PermissionMapper permissionMapper;

    @Override
    public void setApplicationContext(ApplicationContext cxt) throws BeansException {
        this.cxt = cxt;
    }

    @Override
    public void reload() {
        List<Permission> permissions = permissionMapper.list();
        Set<String> permissionSet = new HashSet<>();
        for (Permission permission : permissions) {
            permissionSet.add(permission.getExpression());
        }
        //获取所有继承了BaseAction的action存入Map中
        Map<String, BaseAction> actionMap = cxt.getBeansOfType(BaseAction.class);
        //获取所有action类放入Collection中
        Collection<BaseAction> actions = actionMap.values();
        //遍历每一个action类的所有方法
        for (BaseAction action : actions) {
            Method[] methods = action.getClass().getDeclaredMethods();

            for (Method method : methods) {
                String expression = PermissionUtil.buildPermission(method);
                //判断数据库中是否已经包含了该权限表达式
                if (!permissionSet.contains(expression)) {
                    RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
                    if (annotation != null) {
                        Permission p = new Permission();
                        p.setName(annotation.value());
                        p.setExpression(expression);
                        permissionMapper.save(p);
                    }
                }
            }
        }
    }

    @Override
    public PageResult queryByCondition(QueryObject qo) {
        Long count = permissionMapper.queryByConditionCount(qo);
        if (count == 0) {
            return new PageResult(1, qo.getPageSize(), 0, Collections.EMPTY_LIST);
        }
        List<Permission> data = permissionMapper.queryByCondition(qo);

        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), count.intValue(), data);
    }

	/*@Override
    public void save(Permission per) {
		permissionMapper.save(per);
	}*/

    @Override
    public void delete(Long id) {
        permissionMapper.deleteRelation(id);
        permissionMapper.delete(id);
    }

    @Override
    public List<Permission> list() {
        return permissionMapper.list();
    }

	/*@Override
    public Long queryByConditionCount(QueryObject qo) {
		return permissionMapper.queryByConditionCount(qo);
	}*/

    @Override
    public List<Permission> selectByEmployeeId(Long empId) {
        return permissionMapper.selectByEmployeeId(empId);
    }

    @Override
    public void batchDelete(List<Long> list) {
        for (Long id : list) {
            permissionMapper.deleteRelation(id);
        }
        permissionMapper.batchDelete(list);
    }

}
