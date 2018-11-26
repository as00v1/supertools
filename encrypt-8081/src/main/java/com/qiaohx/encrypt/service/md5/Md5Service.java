package com.qiaohx.encrypt.service.md5;

import com.qiaohx.encrypt.mapper.Emd5Mapper;
import com.qiaohx.encrypt.model.Emd5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

@Service
public class Md5Service implements IMd5Service{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private Emd5Mapper emd5Mapper;

    @Override
    public Emd5 selectByValue(String value) throws Exception{
        logger.info("收到字符串：" + value);

        MessageDigest md = MessageDigest.getInstance("MD5");
        //对字符串进行加密
        md.update(value.getBytes());
        //获得加密后的数据
        byte[] secretBytes = md.digest();
        String md5 = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5.length(); i++) {
            md5 = "0" + md5;
        }
        logger.info("MD5：" + md5);
        Emd5 emd5 = emd5Mapper.selectByPrimaryKey(md5);
        if (emd5 == null){
            logger.info("未查询到数据库中存在，新插入一条");
            emd5 = new Emd5();
            emd5.setMd5Key(md5);
            emd5.setMd5Value(value);
            emd5.setCreateDate(new Date());
            emd5Mapper.insert(emd5);
        }else{
            logger.info("查询到数据库中存在");
        }
        return emd5;
    }
}
