package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.Employee;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

public interface IEmployeeService {
	void save(Employee dept);
	void update(Employee dept);
	void delete(Long id);
	Employee get(Long id);
	List<Employee> list();
	PageResult queryByCondition(QueryObject qo);
	Employee login(String username, String password);
	void batchDelete(List<Long> list);
}
