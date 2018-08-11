package org.rib.gamemap.model.converter;

import org.rib.gamemap.model.Player;
import org.rib.gamemap.model.PlayerDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.rib.gamemap.util.TimeUtil.getCurrentTimestampInSec;

@Component
public class PlayerConverter {

    public PlayerDto toPlayerDto(Player player) {
        long currTime = getCurrentTimestampInSec();
        Long[] tasksConverted = player.getTaskList().getTasks().stream()
                .map(timestamp -> (timestamp != null) ? (timestamp - currTime) : null)
                .toArray(Long[]::new);

        return PlayerDto.builder()
                .id(player.getId())
                .x(player.getX())
                .y(player.getY())
                .tasksRemainingTimes(tasksConverted)
                .timeout(player.getTaskList().getTaskGenerationTimeoutSec())
                .build();
    }

    public List<PlayerDto> toPlayerDtoList(List<Player> players) {
        return players.stream()
                .map(this::toPlayerDto)
                .collect(toList());
    }

}
