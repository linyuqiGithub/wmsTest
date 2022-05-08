package com._520it.wms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com._520it.wms.domain.Role;
import com._520it.wms.query.QueryObject;

public interface RoleMapper {
  void save(Role role);
  void update(Role role);
  void delete(Long id);
  Role get(Long id);
  List<Role> list();
  Long queryByConditionCount(QueryObject qo);
  List<Role> queryByCondition(QueryObject qo);
  void inserRelation(@Param("roleId")Long roleId,@Param("permissionId")Long permissionId);
  void inserMenusRelation(@Param("roleId")Long roleId,@Param("menuId")Long menuId);
  void deleteRelation(Long id);
  void deleteMenusRelation(Long id);
  void deleteRelation2(Long id);
  Role selectByEmployeeId(Long id);
  void batchDelete(List<Long> list);
}
