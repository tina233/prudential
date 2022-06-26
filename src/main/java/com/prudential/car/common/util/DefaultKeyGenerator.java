package com.prudential.car.common.util;

import com.google.common.base.Preconditions;

import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author:qutingting
 * @Date: 2022-06-26 16:52
 * @Description: 全局唯一Id生成器
 */
public final class DefaultKeyGenerator {
    public static final long EPOCH;

    private static final long SEQUENCE_BITS = 12L;

    private static final long WORKER_ID_BITS = 10L;

    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;

    private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;

    private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;

    private static final long WORKER_ID_MAX_VALUE = 1L << WORKER_ID_BITS;

    private static long workerId;

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.NOVEMBER, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        EPOCH = calendar.getTimeInMillis();
    }

    private long sequence;

    private long lastTime;

    /**
     * Set work process id.
     *
     * @param workerId work process id
     */
    public static void setWorkerId(final long workerId) {
        Preconditions.checkArgument(workerId >= 0L && workerId < WORKER_ID_MAX_VALUE, "illegal workerId");

        DefaultKeyGenerator.workerId = workerId;
    }

    /**
     * Generate key.
     *
     * @return key type is @{@link Long}.
     */

    public synchronized long generateKey() {
        long currentMillis = getCurrentMillis();
        if (lastTime > currentMillis) {
            throw new IllegalArgumentException(String.format("Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", lastTime, currentMillis));
        } else if (lastTime == currentMillis) {
            if (0L == (sequence = (sequence + 1) & SEQUENCE_MASK)) {
                currentMillis = waitUntilNextTime(currentMillis);
            }
        } else {
            sequence = ThreadLocalRandom.current().nextInt(2);//防止低并发时id几乎为偶数，不利于分库分表
        }
        lastTime = currentMillis;
        return ((currentMillis - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (workerId << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
    }

    private long waitUntilNextTime(final long lastTime) {
        long time = getCurrentMillis();
        while (time <= lastTime) {
            time = getCurrentMillis();
        }
        return time;
    }

    private long getCurrentMillis() {
        return System.currentTimeMillis();
    }

}
