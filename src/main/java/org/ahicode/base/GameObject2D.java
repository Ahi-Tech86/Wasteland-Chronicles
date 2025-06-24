package org.ahicode.base;

public abstract class GameObject2D {
    private int worldX, worldY;

    public GameObject2D(int worldX, int worldY) {
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
