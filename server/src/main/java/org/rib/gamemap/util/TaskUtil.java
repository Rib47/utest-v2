package org.rib.gamemap.util;

import lombok.experimental.UtilityClass;
import org.rib.gamemap.model.PlayerDto;
import org.rib.gamemap.model.PlayerListDto;
import org.rib.gamemap.model.PlayerSectorListDto;

import java.text.MessageFormat;
import java.util.List;

@UtilityClass
public class TaskUtil {


   public static void printPlayerList(final PlayerListDto playerList) {
      System.out.println("Players list:");
      printPlayers(playerList.getPlayers());
      printEndingDelimiter();
   }

   public static void printPlayerList(final PlayerSectorListDto playerList) {

      System.out.println(MessageFormat.format("Players list for sector: [{0}, {1}] - [{2}, {3}]",
              playerList.getX1(), playerList.getY1(), playerList.getX2(), playerList.getY2()));

      printPlayers(playerList.getPlayers());
      printEndingDelimiter();
   }

   private static void printPlayers(final List<PlayerDto> players) {
      players.forEach(pl -> {
         String tasksString = taskArrayToPrintString(pl.getTasksRemainingTimes());
         System.out.println(MessageFormat.format("Player(id={0})[{1},{2}] {3}", pl.getId(), pl.getX(), pl.getY(), tasksString));
      });
   }

   private static String taskArrayToPrintString(Long[] taskArray) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < taskArray.length; i++) {
         String timeStr = taskTimeToString(taskArray[i]);
         sb.append("Task").append(i + 1).append(": ").append(timeStr).append(", ");
      }
      sb.delete(sb.length() - 2, sb.length());
      return sb.toString();
   }

   private static String taskTimeToString(Long timeSec) {
      if (timeSec == null) {
         return "-";
      }
      String timeString = "";
      long min = timeSec / 60;
      long sec = timeSec % 60;
      if (min != 0) {
         timeString += min + "m:";
      }
      timeString += sec + "s.";

      return timeString;
   }

   private static void printEndingDelimiter() {
      System.out.println("********************");
      System.out.println("**********");
      System.out.println("***");
   }

}
