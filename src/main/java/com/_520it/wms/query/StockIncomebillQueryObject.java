package com._520it.wms.query;

import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter@Getter
public class StockIncomebillQueryObject extends QueryObject {
    private Date beginDate;//开始时间
    private Date endDate;//结束时间
    private Long depotId = -1L;//仓库id
    private Integer status = -1;//审核状态

    //getter方法必须做非空判断,否则DateUtil会报控制针异常
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
    public Long getDepotId() {
        return depotId;
    }

    public Integer getStatus() {
        return status;
    }

}
