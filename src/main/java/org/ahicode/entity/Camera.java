package org.ahicode.entity;

import org.ahicode.core.GameSettings;

import java.awt.*;

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

    public Point worldCoordsToScreen(int worldX, int worldY, int playerWorldX, int playerWorldY) {
        return new Point(
                worldX - playerWorldX + screenX,
                worldY - playerWorldY + screenY
        );
    }

    public boolean isVisible(int worldX, int worldY, int playerWorldX, int playerWorldY) {
        return
                worldX + GameSettings.TILE_SIZE > playerWorldX - screenX &&
                worldX - GameSettings.TILE_SIZE < playerWorldX + screenX &&
                worldY + GameSettings.TILE_SIZE * 2 > playerWorldY - screenY &&
                worldY - GameSettings.TILE_SIZE < playerWorldY + screenY;
    }
}
