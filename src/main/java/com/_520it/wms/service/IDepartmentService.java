package com._520it.wms.service;

import com._520it.wms.domain.Department;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.DepartmentQueryObject;

import java.util.List;

public interface IDepartmentService {
	void save(Department dept);
	void update(Department dept);
	void delete(Long id);
	Department get(Long id);
	List<Department> list();
	PageResult queryByCondition(DepartmentQueryObject qo);
	void batchDelete(List<Long> list);
}
