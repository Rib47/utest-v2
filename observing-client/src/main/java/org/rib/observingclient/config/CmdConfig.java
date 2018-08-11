package org.rib.observingclient.config;

import lombok.extern.slf4j.Slf4j;
import org.rib.gamemap.model.PlayerSectorListDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.rib.gamemap.common.Constants.Game.FIELD_SIZE;
import static org.rib.gamemap.common.Constants.Game.LOOK_SIZE;
import static org.rib.gamemap.common.Constants.Rest.URL_PREFIX;
import static org.rib.gamemap.util.TaskUtil.printPlayerList;
import static org.rib.observingclient.common.Constants.Rest.SECTOR_PLAYERS_PATH;

@Configuration
@Slf4j
public class CmdConfig {

   private static Random random = new Random();

   @Bean
   public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
      return args -> {

         MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
         headers.add("Content-Type", "application/json");

         int x = random.nextInt(FIELD_SIZE - LOOK_SIZE);
         int y = random.nextInt(FIELD_SIZE - LOOK_SIZE);

         String lookSectorPlayersListUrl = URL_PREFIX + MessageFormat.format(SECTOR_PLAYERS_PATH, x, y);
         log.info("Requesting players by URL: {}", lookSectorPlayersListUrl);

         while (true) {
            ResponseEntity<PlayerSectorListDto> response = restTemplate.exchange(
                    lookSectorPlayersListUrl, HttpMethod.GET, new HttpEntity<Object>(headers), PlayerSectorListDto.class);
            PlayerSectorListDto playerList = response.getBody();

            printPlayerList(playerList);

            // just little sleep timeout )
            TimeUnit.MILLISECONDS.sleep(1);
         }
      };
   }

}
