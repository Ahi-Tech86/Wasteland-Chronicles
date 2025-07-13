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

    public GameObject[] setObject() {
        GameObject[] result = new GameObject[10];

        return result;
    }

    public GameObject[] getLevelObjects() {
        return levelObjects;
    }
}
