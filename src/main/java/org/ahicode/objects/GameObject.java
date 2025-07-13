package org.ahicode.objects;

import org.ahicode.application.core.GameSettings;
import org.ahicode.core.WorldPositionedObject;
import org.ahicode.entities.Camera;
import org.ahicode.entities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject extends WorldPositionedObject {

    private boolean collision = false;
    private BufferedImage image;
    private String name;
    private int spriteWidth;
    private int spriteHeight;
    private final RenderingOrder order;


    public GameObject(int worldX, int worldY, RenderingOrder order) {
        super(worldX, worldY);
        this.order = order;
    }

    public void render(Graphics2D graphics2D, Player player, Camera camera) {
        int screenX = getWorldX() - player.getWorldX() + camera.getScreenX();
        int screenY = getWorldY() - player.getWorldY() + camera.getScreenY();

        graphics2D.drawImage(image, screenX, screenY, getSpriteWidth() * GameSettings.SCALE, getSpriteHeight() * GameSettings.SCALE, null);
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

    public RenderingOrder getOrder() {
        return order;
    }
}
