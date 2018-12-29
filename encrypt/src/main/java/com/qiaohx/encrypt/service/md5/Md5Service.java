package com.qiaohx.encrypt.service.md5;

import com.qiaohx.encrypt.mapper.Emd5Mapper;
import com.qiaohx.encrypt.model.Emd5;
import com.qiaohx.utils.constant.ConstantCode;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class Md5Service implements IMd5Service{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ThreadPoolExecutor threadPoolExecutor;

    public Md5Service() {
        logger.debug("Md5Service init");
        this.threadPoolExecutor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(5));
    }

    @Resource
    private Emd5Mapper emd5Mapper;

    @Override
    public JSONObject selectByValue(String value) throws Exception{
        logger.info("收到字符串：" + value);
        JSONObject resultJson = new JSONObject();
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

        // 放入线程池
        threadPoolExecutor.execute(new InsertMD5(md5, value));

        resultJson.put("md5Key", md5);
        resultJson.put("md5Value", value);
        resultJson.put(ConstantCode.ERROR_CODE, ConstantCode.ERROR_CODE_0);
        return resultJson;
    }

    class InsertMD5 implements Runnable{

        private String md5;
        private String value;

        public InsertMD5(String md5, String value) {
            this.md5 = md5;
            this.value = value;
        }

        @Override
        public void run() {
            logger.info("异步线程新增md5开始");
            Emd5 emd5 = emd5Mapper.selectByPrimaryKey(md5);
            if (emd5 == null){
                logger.info("未查询到数据库中存在，新插入一条");
                emd5 = new Emd5();
                emd5.setMd5Key(md5);
                emd5.setMd5Value(value);
                final Emd5 emd = new Emd5();
                emd.setMd5Key(md5);
                emd.setMd5Value(value);
                emd.setCreateDate(new Date());
                emd5Mapper.insert(emd);
            }else{
                logger.info("查询到数据库中存在");
            }
        }
    }
}
