package com._520it.wms.service.impl;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.mapper.EmployeeMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.util.MD5;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

public class EmployeeServiceImpl implements IEmployeeService {
    @Setter
    EmployeeMapper employeeMapper;
    @Setter
    IPermissionService permissionService;
	@Override
	public void save(Employee emp) {
		//新增员工将密码先进行加密再设置到对象中
		emp.setPassword(MD5.encode(emp.getPassword()));
		employeeMapper.save(emp);
		List<Role> roles = emp.getRoles();
		for (Role role : roles) {
			employeeMapper.inserRelation(emp.getId(),role.getId());
		}
	}

	@Override
	public void update(Employee emp) {
		employeeMapper.update(emp);
		employeeMapper.deleteRelation(emp.getId());
		List<Role> roles = emp.getRoles();
		for (Role role : roles) {
			employeeMapper.inserRelation(emp.getId(),role.getId());
		}
	}

	@Override
	public void delete(Long id) {
		employeeMapper.deleteRelation(id);
		employeeMapper.delete(id);
	}

	@Override
	public Employee get(Long id) {
		return employeeMapper.get(id);
	}

	@Override
	public List<Employee> list() {
		return employeeMapper.list();
	}
	
	@Override
	public PageResult queryByCondition(QueryObject qo){
		Long count = employeeMapper.queryByConditionCount(qo);
		if(count == 0) {
			return new PageResult(1, qo.getPageSize(), 0,Collections.EMPTY_LIST);
		}
		List<Employee> data = employeeMapper.queryByCondition(qo);
		
		return new PageResult(qo.getCurrentPage(),qo.getPageSize(), count.intValue(), data);
	}

	@Override
	public Employee login(String username, String password){
		//登录前先将密码进行加密
		password = MD5.encode(password);
        Employee emp = employeeMapper.login(username, password);
        if(emp == null){
        	//抛出异常,表示登陆账号密码不正确
			//抛出运行时异常,这样程序可以不处理,若要抛出Exception,则login方法也必须抛出异常
			//同时接口中的login方法也必须定义抛出异常
        	throw new RuntimeException("账号密码有误");
        }
        //将员工信息设置到session中
		UserContext.setCurrentUser(emp);

        //获取该员工的所有权限
        List<Permission> permissions = permissionService.selectByEmployeeId(emp.getId());
        //获取员工的权限表达式,并设置到session中
        UserContext.setCurrentPermission(permissions);
		return emp;
	}

	@Override
	public void batchDelete(List<Long> list) {
		for (Long id : list) {
			//删除每一个中间表关系
			employeeMapper.deleteRelation(id);
		}
		employeeMapper.batchDelete(list);
	}


}
