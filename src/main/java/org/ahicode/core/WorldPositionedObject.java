package org.ahicode.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class WorldPositionedObject {
    private int worldX, worldY;

    public WorldPositionedObject(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }
}
