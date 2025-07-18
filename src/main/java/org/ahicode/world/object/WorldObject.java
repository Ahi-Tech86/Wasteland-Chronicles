package org.ahicode.world.object;

import lombok.Getter;
import org.ahicode.core.WorldPositionedObject;
import org.ahicode.entity.Camera;
import org.ahicode.entity.Player;
import org.ahicode.graphics.rendering.ShadowSystem;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.ahicode.core.GameSettings.SCALE;

@Getter
public class WorldObject extends WorldPositionedObject {

    private final int gid;
    private final boolean collision;
    private final ShadowSystem.ShadowType shadowType;
    private final String name;
    private final int spriteWidth;
    private final int spriteHeight;
    private final Rectangle solidArea;
    private final int solidAreaDefaultX;
    private final int solidAreaDefaultY;

    public WorldObject(
            int gid,
            String name,
            int worldX,
            int worldY,
            int spriteWidth,
            int spriteHeight,
            boolean collision,
            Rectangle solidArea,
            ShadowSystem.ShadowType shadowType
    ) {
        super(worldX, worldY);
        this.name = name;
        this.gid = gid;
        this.solidArea = solidArea;
        this.collision = collision;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
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
}