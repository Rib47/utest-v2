package org.rib.gamemap.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PlayerDto {
    private int id;
    private int x = 0;
    private int y = 0;
    private int timeout = 0;

    private Long[] tasksRemainingTimes;

    public PlayerDto(final int id, final int x, final int y, final Long[] tasksRemainingTimes) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.tasksRemainingTimes = tasksRemainingTimes;
    }

}
