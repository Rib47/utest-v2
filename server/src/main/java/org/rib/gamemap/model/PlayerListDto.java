package org.rib.gamemap.model;

import lombok.Data;

import java.util.List;

@Data
public class PlayerListDto {
    private final List<PlayerDto> players;

    public PlayerListDto(final List<PlayerDto> players) {
        this.players = players;
    }
}
