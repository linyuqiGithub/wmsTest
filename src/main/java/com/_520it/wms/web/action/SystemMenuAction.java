package com._520it.wms.web.action;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.query.SystemMenuQueryObject;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class SystemMenuAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private ISystemMenuService systemMenuService;

	@Getter
	private SystemMenuQueryObject qo=new SystemMenuQueryObject();

	@Getter
	private SystemMenu systemMenu = new SystemMenu();
	
	@RequiredPermission("系统菜单列表")
	public String execute(){
		try {
			putContext("pageResult", systemMenuService.queryByConditionPage(qo));
			//需要把显示的层级菜单存放到list集合中显示在页面中
			putContext("parentMenus",systemMenuService.listParentMenus(qo.getParentId()));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	@RequiredPermission("系统菜单编辑")
	public String input() {
		try {
			//点击新增按钮后，编辑页面的父菜单回显
			if(qo.getParentId() != null){
				//表示该新增是在非一级菜单上的新增，需要显示父菜单的名称
				SystemMenu parent = systemMenuService.get(qo.getParentId());
				putContext("parentName",parent.getName());
			}else {
				//表示该新增是在一级菜单的新增，没有父菜单，父菜单就是根目录
				putContext("parentName","根目录");
			}
			if(systemMenu.getId() != null){
				systemMenu = systemMenuService.get(systemMenu.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "input";
	}

	@RequiredPermission("系统菜单保存/更新")
	public String saveOrUpdate() {
		try {
			if(qo.getParentId()!= null){
				//根据qo查找父级菜单
				SystemMenu parent = systemMenuService.get(qo.getParentId());
				//将父级菜单设置给当前菜单对象
				systemMenu.setParent(parent);
			}
			if (systemMenu.getId() == null) {
                systemMenuService.save(systemMenu);
				addActionMessage("增加成功");
            } else {
                systemMenuService.update(systemMenu);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("系统菜单删除")
	public String delete()  throws  Exception {
		try {
			if (systemMenu.getId() != null) {
                systemMenuService.delete(systemMenu.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败，请联系管理员");
		}
		return NONE;
	}

	public String queryMenuByparentSn() {
		try {
			//根据父节点的sn查询出该父菜单的所有子菜单
			List<SystemMenu> menus = systemMenuService.queryMenuByparentSn(qo.getParentSn());
			List<Object> list = new ArrayList<>();
			for (SystemMenu menu : menus) {
				//将子菜单的属性转换成zThree能够识别的属性名
                list.add(menu.toJson());
            }
            //将集合转换成JSon数据格式,zThree规定响应的数据必须是JSon
			putJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}



}
