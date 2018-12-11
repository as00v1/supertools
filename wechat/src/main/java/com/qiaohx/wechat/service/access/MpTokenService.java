package com.qiaohx.wechat.service.access;

import com.qiaohx.utils.http.WechatHttpKit;
import com.qiaohx.wechat.mapper.WMpInfoMapper;
import com.qiaohx.wechat.model.WMpInfo;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MpTokenService implements IMpTokenService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public static String JSAPI_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    @Resource
    private WMpInfoMapper wMpInfoMapper;
    @Override
    public void refreshAccessToken() throws Exception {

        List<WMpInfo> wMpInfoList = wMpInfoMapper.queryAll();
        List<WMpInfo> updList = new ArrayList<>();
        for (int i = 0; i < wMpInfoList.size(); i++) {
            WMpInfo wMpInfo = wMpInfoList.get(i);
            String appid = wMpInfo.getAppId();
            String appsecret = wMpInfo.getAppSecret();
            logger.info("appid:" + appid + ",secret:" + appsecret);
            // 获取accesstoken
            String res = WechatHttpKit.doGet(TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret));
            logger.info("access_token res:" + res);
            JSONObject resJson = JSONObject.fromObject(res);
            String accessToken = resJson.containsKey("access_token")?resJson.getString("access_token"):"";
            // 获取jsapiTicket
            res = WechatHttpKit.doGet(JSAPI_URL.replace("ACCESS_TOKEN", accessToken));
            logger.info("js_tacket res:" + res);
            resJson = JSONObject.fromObject(res);
            String jsapiTicket= resJson.containsKey("ticket")?resJson.getString("ticket"):"";

            wMpInfo.setAccessToken(accessToken);
            wMpInfo.setJsapiTicket(jsapiTicket);
            updList.add(wMpInfo);
        }
        wMpInfoMapper.updateTokenBatch(updList);

    }
}
