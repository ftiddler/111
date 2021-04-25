package com.example.demo.common.utils;

import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

/**
 * @author Huang Yuchen
 * @version 1.0
 * @date 2021-03-30 10:14
 */
public class UuidUtils {
    public static BigInteger getUUID20() {
        // 获取时间戳
        String timestampStr = DateUtils.currentTimestamp().toString();
        // 获取7位随机数字
        Random random = new Random();
        String randomStr = String.valueOf(1000000 + random.nextInt(9000000));
        return new BigInteger(timestampStr + randomStr);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-" ,"");
    }
}
