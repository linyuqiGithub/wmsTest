package com._520it.wms.query;

import com._520it.wms.util.DateUtil;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


@Setter
public class OrderChartQueryObject extends QueryObject {
    public static Map<String,String> mapType = new LinkedHashMap<>();
    static {
       mapType.put("e.name","订货人员");
       mapType.put("p.name","货品名称");
       mapType.put("s.name","供应商");
       mapType.put("b.name","货品品牌");
       mapType.put("date_format(bill.vdate,'%Y-%m')","订货期期(月)");
       mapType.put("date_format(bill.vdate,'%Y-%m-%d')","订货期期(日)");
    }
    //业务开始时间
    private Date beginDate;
    //业务结束时间
    private Date endDate;
    //商品编码和名称
    private String keyword;
    //供应商
    private Long supplierId = -1L;
    //品牌
    private Long brandId = -1L;
    //分组类型
    private String groupBy = "e.name";

    public Date getBeginDate() {
        if(beginDate == null){
            return null;
        }
        return DateUtil.getBeginDate(beginDate);
    }
    public Date getEndDate() {
        if(endDate == null){
            return null;
        }
        return DateUtil.getEndDate(endDate);
    }

    public String getKeyword() {
        return keyword;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public String getGroupBy() {
        return groupBy;
    }


}
