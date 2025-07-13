package org.ahicode.objects;

import java.util.Map;

public class ObjectsSetter {

    private Map<Integer, ObjectMetadata> objectMetadataMap;
    private GameObject[] levelObjects;
    private final int tileSize;

    public ObjectsSetter(int tileSize) {
        this.tileSize = tileSize;
        objectMetadataMap = ObjectsLoader.getObjectsMetadata();
        levelObjects = ObjectsLoader.loadObjects(objectMetadataMap);
    }

    public GameObject[] getLevelObjects() {
        return levelObjects;
    }
}
