package com.qiaohx.wechat;

import com.qiaohx.wechat.service.access.MpTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatApplicationTests {

    @Autowired
    private MpTokenService mpTokenService;
    @Test
    public void contextLoads() {
        try {
            mpTokenService.refreshAccessToken();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
