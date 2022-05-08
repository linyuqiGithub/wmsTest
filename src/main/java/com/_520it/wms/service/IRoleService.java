package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

public interface IRoleService {
    void save(Role role);
    void update(Role role);
    void delete(Long id);
    Role get(Long id);
    List<Role> list();
    Long queryByConditionCount(QueryObject qo);
    PageResult queryByCondition(QueryObject qo);
    void batchDelete(List<Long> list);
}
