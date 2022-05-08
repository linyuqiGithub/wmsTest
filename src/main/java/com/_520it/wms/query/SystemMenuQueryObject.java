package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class SystemMenuQueryObject extends QueryObject {
    //根据父目录id显示菜单
    private Long parentId;
    //根据sn查询对应的子菜单
    private String parentSn;
}
