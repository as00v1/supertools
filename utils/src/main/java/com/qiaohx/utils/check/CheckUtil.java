package com.qiaohx.utils.check;

import com.qiaohx.utils.constant.ConstantCode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Set;

/**
 * 检查类
 */
public class CheckUtil {

    /**
     * 检查入参是否缺失
     * @param json 待检查的json串
     * @param keyJson 需要校验的json
     * @return 成功或失败
     */
    public static JSONObject checkRequest(JSONObject json, JSONObject keyJson){
        JSONObject res = new JSONObject();
        // 拿到所有待检测的key
        Set<?> it = keyJson.keySet();
        // 遍历待检测的key
        for (Object anIt : it) {
            String key = (String) anIt;
            if (!json.containsKey(key)) {
                // 待检查中没有这个key
                res.put(ConstantCode.ERROR_CODE, ConstantCode.ERROR_CODE_1000);
                res.put(ConstantCode.MESSAGE, keyJson.getString(key) + key + "不能为空！");
                return res;
            } else {
                Object value = json.get(key);
                boolean errorFlag = false;

                if (value instanceof String) {
                    if ("".equals(value))
                        errorFlag = true;
                } else if (value instanceof JSONArray) {
                    if (((JSONArray) value).size() <= 0)
                        errorFlag = true;
                } else if (value instanceof JSONObject) {
                    if (((JSONObject) value).isEmpty())
                        errorFlag = true;
                }

                if (errorFlag) {
                    res.put(ConstantCode.ERROR_CODE, ConstantCode.ERROR_CODE_1000);
                    res.put(ConstantCode.MESSAGE, keyJson.getString(key) + key + "不能为空！");
                    return res;
                }
            }
        }
        res.put(ConstantCode.ERROR_CODE, ConstantCode.ERROR_CODE_0);
        return res;
    }


    /**
     * 检查返回值
     * @param json
     * @return
     */
    public static boolean checkResponse(JSONObject json){
        if (json == null || json.isEmpty()) {
            return false;
        }
        boolean result = false;
        Object o = json.get(ConstantCode.CODE);
        if (o == null) {
            // false
        }else if(ConstantCode.ERROR_CODE_0.equals(o.toString())){
            result = true;
        }
        return result;
    }
}
