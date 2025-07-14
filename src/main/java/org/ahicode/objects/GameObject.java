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
    private final Rectangle solidArea;
    private int solidAreaDefaultX;
    private int solidAreaDefaultY;

    public GameObject(int worldX, int worldY, RenderingOrder order, Rectangle solidArea) {
        super(worldX, worldY);
        this.order = order;
        this.solidArea = solidArea;
        this.solidAreaDefaultX = solidArea.x;
        this.solidAreaDefaultY = solidArea.y;
    }

    public void render(Graphics2D graphics2D, Player player, Camera camera) {
        int screenX = getWorldX() - player.getWorldX() + camera.getScreenX();
        int screenY = getWorldY() - player.getWorldY() + camera.getScreenY();

        graphics2D.drawImage(image, screenX, screenY, getSpriteWidth() * GameSettings.SCALE, getSpriteHeight() * GameSettings.SCALE, null);

        if (name.equals("fenceSideLeft") || name.equals("fenceSideRight")) {
            graphics2D.setColor(Color.PINK);
            graphics2D.drawRect(screenX + solidArea.x, screenY + solidArea.y, (int) solidArea.getWidth(), (int) solidArea.getHeight());
        }
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

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }
}
