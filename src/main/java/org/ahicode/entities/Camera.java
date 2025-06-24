package org.ahicode.entities;

public class Camera {

    private int screenWidth, screenHeight;
    private int mapWidth, mapHeight;
    private int offsetX, offsetY;

    public Camera(int screenWidth, int screenHeight, int mapWidth, int mapHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public void update(int targetX, int targetY) {
        offsetX = targetX - screenWidth / 2;
        offsetY = targetY - screenHeight / 2;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }
}
