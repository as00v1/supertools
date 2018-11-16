package com.qiaohx.utils.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static Date getNow(){
        return new Date();
    }

    /**
     * 获取当前时间的指定格式
     * @param dateFormatRules
     * @return
     */
    public static String dateToStr(DateFormatRules dateFormatRules){
        return dateToStr(getNow(), dateFormatRules);
    }

    /**
     * 时间转换字符串
     * @param dateFormatRules
     * @return
     */
    public static String dateToStr(Date date, DateFormatRules dateFormatRules){
        DateFormat dateFormat = null;
        switch (dateFormatRules){
            case YYYY_MM_DD_HH_MM_SS:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
            case YYYYMMDD:
                dateFormat = new SimpleDateFormat("yyyyMMdd");
                break;
            default:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
        }
        return dateFormat.format(date);
    }

}
