package org.ahicode.core;

public abstract class WorldPositionedObject {
    private int worldX, worldY;

    public WorldPositionedObject(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
}
