package org.rib.gamemap.controller;

import org.rib.gamemap.model.PlayerDto;
import org.rib.gamemap.model.PlayerListDto;
import org.rib.gamemap.model.PlayerSectorListDto;
import org.rib.gamemap.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

import static org.rib.gamemap.common.Constants.Game.FIELD_SIZE;
import static org.rib.gamemap.common.Constants.Game.PLAYERS_COUNT;
import static org.rib.gamemap.common.Constants.Rest.PLAYERS_PATH;
import static org.rib.gamemap.common.Constants.Rest.PLAYER_PATH;
import static org.rib.gamemap.common.Constants.Rest.PLAYER_TASK_PATH;
import static org.rib.gamemap.common.Constants.Rest.VERSION;
import static org.springframework.http.HttpStatus.NOT_MODIFIED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = VERSION)
@Validated
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @ResponseStatus(OK)
    @GetMapping(path = PLAYERS_PATH, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, params = {"x", "y"})
    public ResponseEntity<PlayerSectorListDto> getPlayersFromSector(
            @RequestParam("x") @Min(1) @Max(FIELD_SIZE) Integer x,
            @RequestParam("y") @Min(1) @Max(FIELD_SIZE) Integer y) {
        PlayerSectorListDto playerList = playerService.getPlayersForLookArea(x, y);
        return ResponseEntity.ok(playerList);

    }

    @ResponseStatus(OK)
    @GetMapping(path = PLAYERS_PATH, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, params = {"count"})
    public ResponseEntity<PlayerListDto> getRandomPlayers(@RequestParam @Min(1) @Max(PLAYERS_COUNT) Integer count) {
        List<PlayerDto> playerList = playerService.getRandomPlayers(count);
        return ResponseEntity.ok(new PlayerListDto(playerList));
    }

    @ResponseStatus(OK)
    @GetMapping(path = PLAYER_PATH, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerDto> getPlayer(@PathVariable Integer playerId) {
        PlayerDto player = playerService.getPlayer(playerId);
        return ResponseEntity.ok(player);
    }

    @PostMapping(path = PLAYER_TASK_PATH, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerDto> createTasks(@PathVariable Integer playerId, @RequestParam(required = false, defaultValue = "1") Integer count) {
        int resultCount = playerService.createTasksForPlayer(playerId, count);
        PlayerDto player = playerService.getPlayer(playerId);
        if (resultCount == 0) {
            return ResponseEntity.status(NOT_MODIFIED).body(player);
        }
        return ResponseEntity.ok(player);
    }


    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(path = PLAYER_TASK_PATH, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerDto>  stopAllTasks(@PathVariable Integer playerId) {
        PlayerDto playerDto = playerService.stopAllTasksForPlayer(playerId);
        return ResponseEntity.ok(playerDto);
    }


}
