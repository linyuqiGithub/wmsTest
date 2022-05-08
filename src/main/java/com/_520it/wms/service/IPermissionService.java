package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.Permission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

public interface IPermissionService {
   void reload();
   /*void save(Permission dept);*/
   void delete(Long id);
   List<Permission> list(); 
   /*Long queryByConditionCount(QueryObject qo);*/
   PageResult queryByCondition(QueryObject qo);
   List<Permission> selectByEmployeeId(Long empId);
   void batchDelete(List<Long> list);
}
