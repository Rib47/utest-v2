package org.rib.updateclient.common;

import lombok.experimental.UtilityClass;

import static org.rib.gamemap.common.Constants.Rest.PLAYERS_PATH;
import static org.rib.gamemap.common.Constants.Rest.PLAYER_PATH;
import static org.rib.gamemap.common.Constants.Rest.PLAYER_TASK_PATH;
import static org.rib.gamemap.common.Constants.Rest.URL_PREFIX_WITH_VERSION;

@UtilityClass
public final class Constants {


    @UtilityClass
    public final class Rest {


        public static final String PLAYERS_FULL_PATH = URL_PREFIX_WITH_VERSION + PLAYERS_PATH;
        public static final String PLAYER_FULL_PATH = URL_PREFIX_WITH_VERSION + PLAYER_PATH;


        public static final String RANDOM_PLAYERS_FULL_PATH =  PLAYERS_FULL_PATH + "?count={0}";
        public static final String SECTOR_PLAYERS_PATH =  PLAYERS_FULL_PATH + "?x={0}&y={1}";

        public static final String PLAYER_FULL_TASK_PATH =  URL_PREFIX_WITH_VERSION + PLAYER_TASK_PATH;
        public static final String PLAYER_FULL_TASK_PATH_WITH_COUNT =  PLAYER_FULL_TASK_PATH + "?count={0}";
    }


}
