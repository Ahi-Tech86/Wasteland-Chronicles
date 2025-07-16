package org.ahicode.world.object;

import org.ahicode.core.GameSettings;

import java.util.Map;

public class ObjectsSetter {

    private Map<Integer, ObjectMetadata> objectMetadataMap;
    private WorldObject[] levelObjects;
    private final int tileSize;

    public ObjectsSetter() {
        this.tileSize = GameSettings.TILE_SIZE;
        objectMetadataMap = ObjectsLoader.getObjectsMetadata();
        levelObjects = ObjectsLoader.loadObjects(objectMetadataMap);
    }

    public WorldObject[] getLevelObjects() {
        return levelObjects;
    }
}
