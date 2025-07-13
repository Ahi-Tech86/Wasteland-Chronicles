package org.ahicode.entities;

import org.ahicode.application.core.GameSettings;

public class Camera {

    private final int screenX, screenY;

    public Camera(int screenWidth, int screenHeight) {
        this.screenX = screenWidth / 2 - (GameSettings.TILE_SIZE / 2);
        this.screenY = screenHeight / 2 - (GameSettings.TILE_SIZE / 2);
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }
}
