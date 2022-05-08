package com._520it.wms.mapper;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.query.SystemMenuQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemMenuMapper {
	void save(SystemMenu entity);
	void update(SystemMenu entity);
	void delete(Long id);
    SystemMenu get(Long id);
	List<SystemMenu> listAll();
    Long queryByConditionCount(SystemMenuQueryObject qo);
    List<SystemMenu> queryByCondition(SystemMenuQueryObject qo);
	/**
	 * 除一级菜单的所有菜单
	 * @return
	 */
	List<SystemMenu> listChildrenMenu();
	//根据父菜单返回该父菜下的所有子菜单
	List<SystemMenu> queryMenuByparentSn(String parentSn);
	//根据用户id和父菜单查询可以查看的菜单
	List<SystemMenu> queryMenuByparentSnAndEmpId(@Param("parentSn") String parentSn,@Param("empId") Long empId);
}