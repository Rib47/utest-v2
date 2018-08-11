package org.rib.updateclient.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.rib.gamemap.model.PlayerListDto;
import org.rib.updateclient.service.PlayerMapRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static org.rib.gamemap.util.TaskUtil.printPlayerList;

@Configuration
@Slf4j
public class CmdConfig {

   private static final String RESULT_PREFIX = "Result: ";
   private static Random random = new Random();
   private Scanner scanner = new Scanner(System.in);

   @Autowired
   private PlayerMapRestService playerService;

   @Bean
   public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
      return args -> {

         while (true) {
            System.out.println();
            System.out.println("Please enter command:");
            System.out.println("Get random players:      get [player ID]");
            System.out.println("Get random players:      getrnd [number of players]");
            System.out.println("Run tasks:               run [player ID] [number of tasks (1-4)] ");
            System.out.println("Stop all current tasks:  stop [player ID]");

            String input = null;
            String[] params;
            while (Strings.isBlank(input)) {
               try {
                  // input = System.console().readLine();
                  input = scanner.nextLine();
                  TimeUnit.MILLISECONDS.sleep(100);
               } catch (Exception ex) {
                  input = null;
               }
            }

            try {
               params = input.split(" ");

               int count;
               int id;

               switch (params[0]) {
                  case "get":
                     id = Integer.parseInt(params[1]);
                     System.out.println(RESULT_PREFIX + playerService.getPlayer(id));
                     break;
                  case "getrnd":
                     count = Integer.parseInt(params[1]);
                     PlayerListDto randomPlayers = playerService.getRandomPlayers(count);
                     printPlayerList(randomPlayers);
                     break;
                  case "run":
                     id = Integer.parseInt(params[1]);
                     count = Integer.parseInt(params[2]);
                     if ((count < 1) || (count > 4))
                        throw new IllegalArgumentException("'count' argument must be from 1 to 4");
                     System.out.println(RESULT_PREFIX + playerService.runTasks(id, count));
                     break;
                  case "stop":
                     id = Integer.parseInt(params[1]);
                     System.out.println(RESULT_PREFIX + playerService.stopAllTasks(id));
                     break;
                  default:
                     System.out.println(RESULT_PREFIX + "Wrong command, try again.");
               }
            } catch (Exception ex) {
               System.out.println(RESULT_PREFIX + "Error happen. Message: " + ex.getMessage() + ". Try again.");
            }

            TimeUnit.MILLISECONDS.sleep(1);
         }
      };
   }

}
