package com.qiaohx.wechat.service.access;

public interface IMpTokenService {

    /**
     * 刷新微信accessToken
     * @throws Exception
     */
    void refreshAccessToken() throws Exception;
}
