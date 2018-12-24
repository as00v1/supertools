package com.qiaohx.utils.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 处理request的一些常用方法集合
 */
public class RequestUtils {

    private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    private static final String CHARSET = "UTF-8";

    /**
     * 获取request中的body
     * @param request
     * @return
     */
    public static String getRequestBody(HttpServletRequest request){
        String result = "";
        ServletInputStream is = null;
        try {
            is = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            StringBuilder sb = new StringBuilder();
//            while (!is.isFinished()){
//                byte[] b = new byte[1024];
//                is.read(b);
//                sb.append(new String(b, CHARSET));
//            }
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            result = sb.toString();
            logger.info(result);
        }catch (IOException e){
            e.printStackTrace();
            logger.error("获取request body异常", e);
        }finally {
            try {
                if (is != null) is.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
