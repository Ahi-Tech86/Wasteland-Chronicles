package org.ahicode.physics;

import org.ahicode.world.object.WorldObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChunkSystem {

    private final Map<ChunkCoordinate, List<WorldObject>> chunkMap;
    private final int chunkSize;

    public ChunkSystem(int chunkSize) {
        this.chunkMap = new HashMap<>();
        this.chunkSize = chunkSize;
    }

    public void addObject(WorldObject object) {
        ChunkCoordinate chunkCoord = getChunkCoordinate(object.getWorldX(), object.getWorldY());
        chunkMap.computeIfAbsent(chunkCoord, k -> new ArrayList<>()).add(object);
    }

    public List<WorldObject> getNearByObjects(int worldX, int worldY, int radius) {
        ChunkCoordinate center = getChunkCoordinate(worldX, worldY);
        List<WorldObject> result = new ArrayList<>();

        for (int x = center.x - radius; x <= center.x + radius; x++) {
            for (int y = center.y - radius; y <= center.y + radius; y++) {
                ChunkCoordinate coord = new ChunkCoordinate(x, y);

                if (chunkMap.containsKey(coord)) {
                    result.addAll(chunkMap.get(coord));
                }
            }
        }

        return result;
    }

    private record ChunkCoordinate(int x, int y) {}

    private ChunkCoordinate getChunkCoordinate(int worldX, int worldY) {
        return new ChunkCoordinate(worldX / chunkSize, worldY / chunkSize);
    }
}
