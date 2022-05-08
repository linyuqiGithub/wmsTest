package com._520it.wms.mapper;

import com._520it.wms.domain.Department;
import com._520it.wms.query.DepartmentQueryObject;

import java.util.List;

public interface DepartmentMapper {
  /**
   * 新增部门
   * @param dept
   */
  void save(Department dept);

  /**
   * 更新部门
   * @param dept
   */
  void update(Department dept);

  /**
   * 删除部门
   * @param id
   */
  void delete(Long id);

  /**
   * 根据id获取部门
   * @param id
   * @return
   */
  Department get(Long id);

  /**
   * 遍历所有部门
   * @return
   */
  List<Department> list();

  /**
   * 根据查询条件计算部门总数
   * @param qo
   * @return
   */
  Long queryByConditionCount(DepartmentQueryObject qo);

  /**
   * 根据查询条件查询部门集合
   * @param qo
   * @return
   */
  List<Department> queryByCondition(DepartmentQueryObject qo);

  /**
   * 批量删除
   * @param list
   */
  void batchDelete(List<Long> list);
}
