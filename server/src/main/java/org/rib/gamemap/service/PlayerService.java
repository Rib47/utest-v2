package org.rib.gamemap.service;

import lombok.extern.slf4j.Slf4j;
import org.rib.gamemap.game.PlayerStorage;
import org.rib.gamemap.game.PlayersMappingHelper;
import org.rib.gamemap.model.Player;
import org.rib.gamemap.model.PlayerDto;
import org.rib.gamemap.model.PlayerSectorListDto;
import org.rib.gamemap.model.TaskList;
import org.rib.gamemap.model.converter.PlayerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.rib.gamemap.common.Constants.Game.FIELD_SIZE;
import static org.rib.gamemap.common.Constants.Game.LOOK_SIZE;

@Service
@Slf4j
public class PlayerService {

    @Autowired
    private PlayersMappingHelper mappingHelper;
    @Autowired
    private PlayerStorage playerStorage;
    @Autowired
    private PlayerConverter playerConverter;

    // indexes are started from 1
    public PlayerSectorListDto getPlayersForLookArea(int lookAreaTopX, int lookAreaTopY) {


        int topX = Math.max(lookAreaTopX, 1);
        int topY = Math.max(lookAreaTopY, 1);
        int bottomX = Math.min(lookAreaTopX  + LOOK_SIZE, FIELD_SIZE);
        int bottomY = Math.min(lookAreaTopY + LOOK_SIZE, FIELD_SIZE);

        List<Integer> playerIds =
                mappingHelper.getPlayerIdsForLookAreaSections(topX, topY, bottomX, bottomY);

        List<PlayerDto> playerDtoList = playerIds.stream()
                .map(playerStorage::getPlayer)
                .filter(player -> isInArea(player, topX, topY, bottomX, bottomY))
                .map(playerConverter::toPlayerDto)
                .collect(toList());

        return PlayerSectorListDto.builder()
                .players(playerDtoList)
                .x1(topX)
                .x2(bottomX)
                .y1(topY)
                .y2(bottomY)
                .build();
    }

    public List<PlayerDto> getRandomPlayers(int count) {
        return playerConverter.toPlayerDtoList(playerStorage.getRandomPlayers(count));
    }

    public PlayerDto getPlayer(int playerId) {
        return playerConverter.toPlayerDto(playerStorage.getPlayer(playerId));
    }

    public PlayerDto stopAllTasksForPlayer(int playerId) {
        Player player = playerStorage.getPlayer(playerId);
        player.getTaskList().stopAllTasks();
        return playerConverter.toPlayerDto(player);
    }


    public int createTasksForPlayer(int playerId, int taskCount) {
        log.debug("Generating [{}] tasks for player ID = {}", taskCount, playerId);
        TaskList taskList = playerStorage.getPlayer(playerId).getTaskList();
        return taskList.generateNewTasks(taskCount);
    }


    private boolean isInArea(Player player, int x1, int y1, int x2, int y2) {
        return (x1 <= player.getX()) && (player.getX() <= x2) && (y1 <= player.getY()) && (player.getY() <= y2);
    }


}
