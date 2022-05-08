package com._520it.wms.query;

import lombok.Data;

@Data
public class EmloyeeQueryObject extends QueryObject {
    private String keyword;
    private Long deptId;

}
