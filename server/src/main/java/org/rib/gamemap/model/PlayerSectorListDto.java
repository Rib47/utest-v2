package org.rib.gamemap.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlayerSectorListDto {
    private final List<PlayerDto> players;
    private final int x1;
    private final int x2;
    private final int y1;
    private final int y2;

}
