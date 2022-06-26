package com.prudential.car.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author:qutingting
 * @Description: 全局唯一Id生成器
 */
public class IdWorkerUtil {
    private static DefaultKeyGenerator defaultKeyGenerator = new DefaultKeyGenerator();

    static {
        initWorkerId();
    }

    static void initWorkerId() {
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
        } catch (final UnknownHostException e) {
            throw new IllegalStateException("Cannot get LocalHost InetAddress, please check your network!");
        }
        byte[] ipAddressByteArray = address.getAddress();
        DefaultKeyGenerator.setWorkerId((long) (((ipAddressByteArray[ipAddressByteArray.length - 2] & 0B11) << Byte.SIZE) + (ipAddressByteArray[ipAddressByteArray.length - 1] & 0xFF)));
    }

    public static Long generateId() {
        return defaultKeyGenerator.generateKey();
    }
}
