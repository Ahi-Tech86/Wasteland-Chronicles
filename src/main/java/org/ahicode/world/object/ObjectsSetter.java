package org.ahicode.world.object;

import lombok.Getter;

import java.util.Map;

@Getter
public class ObjectsSetter {

    private final Map<Integer, ObjectMetadata> objectMetadataMap;
    private final WorldObject[] levelObjects;

    public ObjectsSetter() {
        objectMetadataMap = ObjectsLoader.getObjectsMetadata();
        levelObjects = ObjectsLoader.loadObjects(objectMetadataMap);
    }
}
