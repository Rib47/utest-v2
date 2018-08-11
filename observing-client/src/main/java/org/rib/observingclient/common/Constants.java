package org.rib.observingclient.common;

import lombok.experimental.UtilityClass;

import static org.rib.gamemap.common.Constants.Rest.PLAYERS_PATH;
import static org.rib.gamemap.common.Constants.Rest.VERSION;

@UtilityClass
public final class Constants {


    @UtilityClass
    public final class Rest {

        public static final String PLAYERS_FULL_PATH =  VERSION + PLAYERS_PATH;
        public static final String RANDOM_PLAYERS_PATH =  PLAYERS_FULL_PATH + "?count=";

        public static final String SECTOR_PLAYERS_PATH =  PLAYERS_FULL_PATH + "?x={0}&y={1}";
    }


}
