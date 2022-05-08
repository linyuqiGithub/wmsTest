package com._520it.wms.service;
import java.util.List;
import com._520it.wms.domain.SystemMenu;
import com._520it.wms.domain.SystemMenuVO;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.SystemMenuQueryObject;

public interface ISystemMenuService {
	void delete(Long id);
	void save(SystemMenu entity);
    SystemMenu get(Long id);
    List<SystemMenu> listAll();
	List<SystemMenu> listChildrenMenu();
	void update(SystemMenu entity);
	PageResult queryByConditionPage(SystemMenuQueryObject qo);
	List<SystemMenuVO> listParentMenus(Long parentId);
	List<SystemMenu> queryMenuByparentSn(String parentSn);
}
