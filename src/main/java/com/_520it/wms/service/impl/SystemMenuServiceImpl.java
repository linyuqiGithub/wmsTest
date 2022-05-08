package com._520it.wms.service.impl;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.SystemMenu;
import com._520it.wms.domain.SystemMenuVO;
import com._520it.wms.mapper.SystemMenuMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.SystemMenuQueryObject;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SystemMenuServiceImpl implements ISystemMenuService {
    @Setter
    private SystemMenuMapper systemMenuMapper;

    public void delete(Long id) {
        systemMenuMapper.delete(id);
    }

    public void save(SystemMenu entity) {
        systemMenuMapper.save(entity);
    }

    public SystemMenu get(Long id) {
        return systemMenuMapper.get(id);
    }

    public List<SystemMenu> listAll() {
        return systemMenuMapper.listAll();
    }

    @Override
    public List<SystemMenu> listChildrenMenu() {
        return systemMenuMapper.listChildrenMenu();
    }

    public void update(SystemMenu entity) {
        systemMenuMapper.update(entity);
    }

    @Override
    public PageResult queryByConditionPage(SystemMenuQueryObject qo) {
        Long count = systemMenuMapper.queryByConditionCount(qo);
        if (count <= 0) {
            return new PageResult(1, qo.getPageSize(), 0, Collections.EMPTY_LIST);
        }
        List<SystemMenu> data = systemMenuMapper.queryByCondition(qo);
        PageResult pageResult = new PageResult(qo.getCurrentPage(), qo.getPageSize(), count.intValue(), data);
        return pageResult;
    }

    //查询所有的父级菜单的层级关系
    @Override
    public List<SystemMenuVO> listParentMenus(Long parentId) {
        //通过获取从qo获取的父类id,若为当前菜单是根菜单,currentMenu则为null
        //若当前菜单不是根菜单,currentMenu不为null
        SystemMenu currentMenu = systemMenuMapper.get(parentId);
        List<SystemMenuVO> list = new ArrayList<>();
        //一级菜单没有parentId,所以currentMenu为null,不进入循环
        while (currentMenu != null) {
            SystemMenuVO vo = new SystemMenuVO();
            vo.setId(currentMenu.getId());
            vo.setName(currentMenu.getName());
            //将获取的父目录放入集合中
            list.add(vo);
            //获取父目录的父目录,若无则为null,此时currentMenu为一级菜单，退出循环
            currentMenu = currentMenu.getParent();
        }
        //将列表反转,因为子目录是先放进集合中,然后放父目录,所以需要将集合反转
        Collections.reverse(list);
        return list;
    }

    @Override
    public List<SystemMenu> queryMenuByparentSn(String parentSn) {
        Employee currentUser = UserContext.getCurrentUser();
        if(currentUser.isAdmin()){
            //如果是超级管理员,直接根据菜单编码返回当前父目录的所有子目录
            return systemMenuMapper.queryMenuByparentSn(parentSn);
        }else {
            //不是超级管理员,则根据登陆用户可查看的菜单返回有相应浏览权限的菜单
            return systemMenuMapper.queryMenuByparentSnAndEmpId(parentSn,currentUser.getId());
        }
    }
}
