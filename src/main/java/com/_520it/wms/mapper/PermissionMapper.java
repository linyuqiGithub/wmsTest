package com._520it.wms.mapper;

import java.util.List;

import com._520it.wms.domain.Permission;
import com._520it.wms.query.QueryObject;

public interface PermissionMapper {
  void save(Permission dept);
  void delete(Long id);
  List<Permission> list(); 
  Long queryByConditionCount(QueryObject qo);
  List<Permission> queryByCondition(QueryObject qo);
  List<Permission> selectByEmployeeId(Long empId);
  Permission selectByRoleId(Long id);
  void deleteRelation(Long id);
  void batchDelete(List<Long> list);
}
