package org.ahicode.entities;

public class Camera {

    private final int screenX, screenY;

    public Camera(int screenWidth, int screenHeight, int tileSize) {
        this.screenX = screenWidth / 2 - (tileSize / 2);
        this.screenY = screenHeight / 2 - (tileSize / 2);
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }
}
