package org.rib.gamemap.game;

import org.rib.gamemap.model.Coordinates;
import org.rib.gamemap.model.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.rib.gamemap.common.Constants.Game.FIELD_SIZE;

@Component
public class PlayersMappingHelper {

    private static final int SECTION_SIZE = FIELD_SIZE;
    private int MAP_HEIGHT_IN_SECTIONS = (FIELD_SIZE + SECTION_SIZE - 1) / SECTION_SIZE;

    private Map<Coordinates, Set<Integer>> playersByMapSections;

    public PlayersMappingHelper() {
        clearAllData();
    }

    public void clearAllData() {
        playersByMapSections = new HashMap<>(MAP_HEIGHT_IN_SECTIONS * MAP_HEIGHT_IN_SECTIONS);
    }

    public void addPlayer(Player player) {
        int sectionX = player.getX() / SECTION_SIZE;
        int sectionY = player.getY() / SECTION_SIZE;

        Coordinates sectionCoordinates = new Coordinates(sectionX, sectionY);
        playersByMapSections
                .computeIfAbsent(sectionCoordinates, (coord) -> new HashSet<>())
                .add(player.getId());
    }


    public List<Integer> getPlayerIdsForLookAreaSections(int lookAreaTopX, int lookAreaTopY, int lookAreaBottomX, int lookAreaBottomY) {

        // if(() || ())

        int startSectionX = Math.max(lookAreaTopX / SECTION_SIZE, 0);
        int startSectionY = Math.max(lookAreaTopY / SECTION_SIZE, 0);
        int endSectionX = Math.min(lookAreaBottomX / SECTION_SIZE, MAP_HEIGHT_IN_SECTIONS);
        int endSectionY = Math.min(lookAreaBottomY / SECTION_SIZE, MAP_HEIGHT_IN_SECTIONS);

        List<Integer> playersIdsRough = getPlayersIdsFromSector(startSectionX, startSectionY, endSectionX, endSectionY);
        return playersIdsRough;
    }

    private List<Integer> getPlayersIdsFromSector(final int startSectionX, final int startSectionY, final int endSectionX, final int endSectionY) {
        ArrayList<Integer> sectorIds = new ArrayList<>();
        for (int x = startSectionX; x <= endSectionX; x++) {
            for (int y = startSectionY; y <= endSectionY; y++) {
                Set<Integer> sectionIds = playersByMapSections.get(new Coordinates(x, y));
                sectorIds.addAll(sectionIds);
            }
        }
        return sectorIds;
    }

}
