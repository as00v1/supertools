package com.qiaohx.utils.sequence;

import com.qiaohx.utils.date.DateFormatRules;
import com.qiaohx.utils.date.DateUtil;

public class SequenceUtil {

    /**
     * 以通用方式获取序列
     * @return
     */
    public static String getSeq(){
        return getSeq(SequenceEnum.COMMON);
    }

    /**
     * 获取主键
     * @param sequenceEnum 序列枚举
     * @return
     */
    public static String getSeq(SequenceEnum sequenceEnum){
        switch (sequenceEnum){
            case COMMON:
                return getCommonSeq();
            default:
                break;
        }
        return null;
    }


    /**
     * 通用主键生成方式：<br/>
     * yyyyMMddHHmmssSSS + 3位随机数
     * @return
     */
    private static synchronized String getCommonSeq(){
        StringBuilder sb = new StringBuilder();
        sb.append(DateUtil.dateToStr(DateFormatRules.YYYYMMDDHHMMSSSSS));
        int random = (int) ((Math.random() * 9 + 1) * 100);
        sb.append(random);
        return sb.toString();
    }
}
