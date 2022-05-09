package com._520it.wms.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 数据库使用时间进行查询时，假设查询条件是beginDate:2021-12-24 endDate:2021-12-24 那么数据库会自动将两个查询条件的时分秒默认为0:00:00
 * 由于数据库保存的时候时间都是自动设置为0:00:00,对于那些保存时间不是0:00:0则没办法查询出来,所以必须使
 * 用该日期帮助类将查询条件设置为0:00:00-23:59:59,这样不管数据库的保存的时间的时分秒是多少，在这个范围时间的都可以查询出来。
 */
public class DateUtil {
    //将查询条件的开始时间的时分秒都设置为0----->2021-12-24 0:00:00
    public static Date getBeginDate(Date currentDate){
        //获取当前日期的Calendar
        Calendar calendar = Calendar.getInstance();
        //设置时间
        calendar.setTime(currentDate);
        //设置时分秒
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }
    //将查询条件的结束时间的时分秒设置为23:59:59------>2021-12-24 23:59:59
    public static Date getEndDate(Date currentDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        return calendar.getTime();
    }
}
