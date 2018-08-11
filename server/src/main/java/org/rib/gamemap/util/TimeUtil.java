package org.rib.gamemap.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TimeUtil {

    public static long getCurrentTimestampInSec() {
        return System.currentTimeMillis() / 1000;
    }
}
