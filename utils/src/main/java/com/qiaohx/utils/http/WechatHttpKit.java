package com.qiaohx.utils.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 用于请求微信的HTTP请求
 * @author jiayong
 * @since 2018年11月29日
 */
public class WechatHttpKit {

    private static final String METHOD_POST = "POST";
    private static final String METHOD_GET = "GET";
    private static final String CHARSET_UTF8 = "UTF-8";
    private static final String CONTENT_TYPE = "application/json";

    private static Logger logger = LoggerFactory.getLogger(WechatHttpKit.class);

    /**
     * get请求，默认UTF-8编码，content-type为application/json
     * @param url
     * @return
     */
    public static String doGet(String url){
        return doGet(url, CONTENT_TYPE);
    }

    /**
     * get请求，默认UTF-8编码
     * @param url
     * @param contentType
     * @return
     */
    public static String doGet(String url, String contentType){
        return doGet(url, CONTENT_TYPE, 30000, 60000);
    }

    /**
     * get请求
     * @param url
     * @param contentType
     * @param connectTimeOut 连接超时
     * @param readTimeOut 读取超时
     * @return
     */
    public static String doGet(String url, String contentType, int connectTimeOut, int readTimeOut){
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        HttpURLConnection urlcon = null;
        try {
            urlcon = getConnection(url, METHOD_GET, contentType);
            urlcon.setConnectTimeout(connectTimeOut);
            urlcon.setReadTimeout(readTimeOut);
            urlcon.connect();
            reader = new BufferedReader(new InputStreamReader(urlcon.getInputStream(), CHARSET_UTF8));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line.toString());
            }
        }catch (IOException e){
            e.printStackTrace();
            logger.error("捕获 IOException ", e);
        }finally {
            try {
                if (reader != null) reader.close();
            }catch (IOException e){
                e.printStackTrace();
                logger.error("关闭reader时捕获 IOException ", e);
            }
            if (urlcon != null) urlcon.disconnect();
        }
        return sb.toString();
    }

    /**
     * 打开链接
     * @param url
     * @param method
     * @return
     */
    private static HttpURLConnection getConnection(String url, String method, String contentType){
        HttpURLConnection connection = null;
        try {
            URL urlObject = new URL(url);
            connection = (HttpURLConnection)urlObject.openConnection();
            // 关于setDoOutput()方法我理解是因为POST方式需要发送参数，所以要设置为true
            if(METHOD_POST.equals(method)) connection.setDoOutput(true);
            if(METHOD_GET.equals(method)) connection.setDoOutput(false);
            connection.setDoInput(true);
            connection.setRequestMethod(method);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", contentType);
            connection.setRequestProperty("Charset", CHARSET_UTF8);
            connection.setRequestProperty("Accept-Charset", CHARSET_UTF8);

        }catch (MalformedURLException e){
            e.printStackTrace();
            logger.error("捕获 MalformedURLException ", e);
        }catch (IOException e){
            e.printStackTrace();
            logger.error("捕获 IOException ", e);
        }

        return connection;
    }
}
