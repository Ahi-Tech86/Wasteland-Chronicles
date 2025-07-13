package org.ahicode.objects;

import org.ahicode.application.core.GameSettings;

import java.util.Map;

public class ObjectsSetter {

    private Map<Integer, ObjectMetadata> objectMetadataMap;
    private GameObject[] levelObjects;
    private final int tileSize;

    public ObjectsSetter() {
        this.tileSize = GameSettings.TILE_SIZE;
        objectMetadataMap = ObjectsLoader.getObjectsMetadata();
        levelObjects = ObjectsLoader.loadObjects(objectMetadataMap);
    }

    public GameObject[] getLevelObjects() {
        return levelObjects;
    }
}
