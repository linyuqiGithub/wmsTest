package com._520it.wms.query;

import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
public class StockOutcomebillQueryObject extends QueryObject {
    private Date beginDate;//开始时间
    private Date endDate;//结束时间
    private Long depotId = -1L;//仓库id
    private Long clientId = -1L;//客户id
    private Integer status = -1;//审核状态

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

    public Long getClientId() {
        return clientId;
    }

    public Integer getStatus() {
        return status;
    }


}
