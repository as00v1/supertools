package com.qiaohx.wechat.service.tasks;

import com.qiaohx.wechat.service.access.MpTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MpTokenService mpTokenService;

    @Scheduled(cron = "0 10 * * * *")
    public void refreshAccessToken(){
        try {
            mpTokenService.refreshAccessToken();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("刷新accesstoken出错！", e);
        }
    }
}
