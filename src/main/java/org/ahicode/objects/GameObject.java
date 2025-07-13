package org.ahicode.objects;

import org.ahicode.core.WorldPositionedObject;
import org.ahicode.entities.Camera;
import org.ahicode.entities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject extends WorldPositionedObject {

    private boolean collision = false;
    private BufferedImage image;
    private final int tileSize;
    private final int gid;
    private String name;
    private int spriteWidth;
    private int spriteHeight;


    public GameObject(int worldX, int worldY, int tileSize, int gid) {
        super(worldX, worldY);
        this.tileSize = tileSize;
        this.gid = gid;
    }

    public void render(Graphics2D graphics2D, Player player, Camera camera) {
        int screenX = getWorldX() - player.getWorldX() + camera.getScreenX();
        int screenY = getWorldY() - player.getWorldY() + camera.getScreenY();

        graphics2D.drawImage(image, screenX, screenY, getSpriteWidth() * 4, getSpriteHeight() * 4, null);
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }
}
