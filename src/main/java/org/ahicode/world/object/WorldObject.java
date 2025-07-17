package org.ahicode.world.object;

import org.ahicode.core.WorldPositionedObject;
import org.ahicode.entity.Camera;
import org.ahicode.entity.Player;
import org.ahicode.graphics.rendering.ShadowSystem;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.ahicode.core.GameSettings.SCALE;

public class WorldObject extends WorldPositionedObject {

    private final int gid;
    private boolean collision = false;
    private ShadowSystem.ShadowType shadowType;
    private String name;
    private int spriteWidth;
    private int spriteHeight;
    private final Rectangle solidArea;
    private final int solidAreaDefaultX;
    private final int solidAreaDefaultY;

    public WorldObject(int worldX, int worldY, int gid, Rectangle solidArea, ShadowSystem.ShadowType shadowType) {
        super(worldX, worldY);
        this.gid = gid;
        this.solidArea = solidArea;
        this.solidAreaDefaultX = solidArea.x;
        this.solidAreaDefaultY = solidArea.y;
        this.shadowType = shadowType;
    }

    public void render(Graphics2D graphics2D, Player player, Camera camera, BufferedImage image) {
        if (camera.isVisible(getWorldX(), getWorldY(), player.getWorldX(), player.getWorldY())) {
            Point screenCoords = camera.worldCoordsToScreen(
                    getWorldX(),
                    getWorldY(),
                    player.getWorldX(),
                    player.getWorldY()
            );

            ShadowSystem.renderShadow(graphics2D, shadowType, screenCoords);

            graphics2D.drawImage(
                    image,
                    screenCoords.x,
                    screenCoords.y,
                    spriteWidth * SCALE,
                    spriteHeight * SCALE,
                    null
            );
        }
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public String getName() {
        return name;
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

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public int getGid() {
        return gid;
    }
}
