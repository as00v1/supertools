package com.qiaohx.wechat.controller;

import com.qiaohx.utils.http.RequestUtils;
import com.qiaohx.utils.transfer.XmlUtils;
import com.qiaohx.wechat.mapper.WMpInfoMapper;
import com.qiaohx.wechat.model.WMpInfo;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class MessageController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private WMpInfoMapper wMpInfoMapper;

    @RequestMapping("/message/{mpId}")
    public void message(HttpServletRequest request, HttpServletResponse response,@PathVariable String mpId) {
        String method = request.getMethod();
        try {
            logger.info("收到请求方式：" + method);
            WMpInfo wMpInfo = wMpInfoMapper.selectByPrimaryKey(mpId);
            if (wMpInfo == null){
                logger.warn("查询不到公众号，将抛弃消息");
                response.getWriter().write("fail");
                return;
            }
            // get方式请求是验证服务器用的
            if ("GET".equals(method)){
                Map<String, String[]> map = request.getParameterMap();
                JSONObject json = JSONObject.fromObject(map);
                logger.info(json.toString());
                String echostr = json.containsKey("echostr") ? json.getString("echostr") : "";
                response.getWriter().write(echostr.replace("[\"", "").replace("\"]", ""));
                response.flushBuffer();
                return;
            }

            String body = RequestUtils.getRequestBody(request);
            body = body.replaceAll("\r\n", "");
            logger.info("收到消息：" + body);
            JSONObject mpMsg = XmlUtils.xmlToJSON(body);
            logger.info("收到消息：" + mpMsg);

            response.getWriter().write("success");
            return;

        }catch (IOException e){
            e.printStackTrace();
            logger.error("微信消息 IOException ", e);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("微信消息 Exception ", e);
        }

    }
}
