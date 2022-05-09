package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统菜单的vo对象
 */
@Setter@Getter@ToString
public class SystemMenuVO extends BaseDomain{
    private Long id;//菜单id
    private String name;//菜单名称
}
