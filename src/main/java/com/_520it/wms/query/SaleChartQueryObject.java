package com._520it.wms.query;

import com._520it.wms.util.DateUtil;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/7.
 */
@Setter
public class SaleChartQueryObject extends QueryObject {
    public static Map<String,String> mapType = new LinkedHashMap<>();
    static {
       mapType.put("e.name","销售人员");
       mapType.put("p.name","货品名称");
       mapType.put("c.name","客户");
       mapType.put("b.name","货品品牌");
       mapType.put("date_format(sc.vdate,'%Y-%m')","订货期期(月)");
       mapType.put("date_format(sc.vdate,'%Y-%m-%d')","订货期期(日)");
    }
    private Date beginDate;
    private Date endDate;
    private String keyword;
    private Long clientId = -1L;
    private Long brandId = -1L;
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

    public Long getClientId() {
        return clientId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public String getGroupBy() {
        return groupBy;
    }


}
