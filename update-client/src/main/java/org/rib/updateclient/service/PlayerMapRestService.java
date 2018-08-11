package org.rib.updateclient.service;

import org.rib.gamemap.model.PlayerDto;
import org.rib.gamemap.model.PlayerListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

import static org.rib.gamemap.common.Constants.Rest.PLAYER_ID_PLACEHOLDER;
import static org.rib.updateclient.common.Constants.Rest.PLAYER_FULL_PATH;
import static org.rib.updateclient.common.Constants.Rest.PLAYER_FULL_TASK_PATH;
import static org.rib.updateclient.common.Constants.Rest.PLAYER_FULL_TASK_PATH_WITH_COUNT;
import static org.rib.updateclient.common.Constants.Rest.RANDOM_PLAYERS_FULL_PATH;
import static org.rib.updateclient.util.HttpUtil.getHeaders;

@Service
public class PlayerMapRestService {

   @Autowired
   private RestTemplate restTemplate;


   public String runTasks(int playerId, int tasksCount) {
      MultiValueMap<String, String> headers = getHeaders();

      String playerFullTaskPath = PLAYER_FULL_TASK_PATH_WITH_COUNT.replace(PLAYER_ID_PLACEHOLDER, String.valueOf(playerId));
      String url = MessageFormat.format(playerFullTaskPath, tasksCount);
      System.out.println("Starting up  tasks for player [" + playerId + "] by URL:" + url);
      try {
         ResponseEntity<PlayerDto> response =
                 restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(headers), PlayerDto.class);
         if (response.getStatusCode().isError()) {
            return response.toString();
         } else {
            return "Succeed: OK. Player info: " + response.getBody().toString();
         }
      } catch (Exception ex) {
         return "Failed: " + ex.getMessage();
      }
   }

   public String stopAllTasks(int playerId) {

      MultiValueMap<String, String> headers = getHeaders();
      String url = PLAYER_FULL_TASK_PATH.replace(PLAYER_ID_PLACEHOLDER, String.valueOf(playerId));
      System.out.println("Stopping all tasks for player [" + playerId + "] by URL:" + url);

      try {
         ResponseEntity<PlayerDto> response =
                 restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(headers), PlayerDto.class);
         if (response.getStatusCode().isError()) {
            return response.toString();
         } else {
            return "Succeed: OK. Player info: " + response.getBody().toString();
         }
      } catch (Exception ex) {
         return "Failed: " + ex.getMessage();
      }
   }


   public PlayerListDto getRandomPlayers(int count) {

      MultiValueMap<String, String> headers = getHeaders();

      String url = MessageFormat.format(RANDOM_PLAYERS_FULL_PATH, count);
      System.out.println("Requesting players by URL:" + url);

      ResponseEntity<PlayerListDto> response = restTemplate.exchange(
              url, HttpMethod.GET, new HttpEntity<>(headers), PlayerListDto.class);
      PlayerListDto playerList = response.getBody();

      return playerList;
   };


   public PlayerDto getPlayer(int playerId) {

      MultiValueMap<String, String> headers = getHeaders();
      String url = PLAYER_FULL_PATH.replace(PLAYER_ID_PLACEHOLDER, String.valueOf(playerId));
      System.out.println("Requesting players by URL:" + url);

      ResponseEntity<PlayerDto> response = restTemplate.exchange(
              url, HttpMethod.GET, new HttpEntity<>(headers), PlayerDto.class);
      PlayerDto playerDto = response.getBody();

      return playerDto;
   };


}
