package com._520it.wms.service.impl;

import com._520it.wms.domain.Department;
import com._520it.wms.mapper.DepartmentMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.DepartmentQueryObject;
import com._520it.wms.service.IDepartmentService;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {
    @Setter
	DepartmentMapper departmentMapper;
	@Override
	public void save(Department dept) {
		departmentMapper.save(dept);
	}

	@Override
	public void update(Department dept) {
		departmentMapper.update(dept);
	}

	@Override
	public void delete(Long id) {
		departmentMapper.delete(id);
	}

	@Override
	public Department get(Long id) {
		return departmentMapper.get(id);
	}

	@Override
	public List<Department> list() {
		return departmentMapper.list();
	}

	@Override
	public PageResult queryByCondition(DepartmentQueryObject qo) {
		Long count = departmentMapper.queryByConditionCount(qo);
		if(count == 0) {
			return new PageResult(1, qo.getPageSize(), 0, Collections.EMPTY_LIST);
		}
		List<Department> data = departmentMapper.queryByCondition(qo);

		return new PageResult(qo.getCurrentPage(),qo.getPageSize(), count.intValue(), data);
	}

	@Override
	public void batchDelete(List<Long> list) {
        departmentMapper.batchDelete(list);
	}

}
