package org.ahicode.entities;

import org.ahicode.utility.SpriteManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityShadow {

    private final BufferedImage image;
    private final int height;
    private final int width;
    private final int offsetX;
    private final int offsetY;

    public EntityShadow() {
        this.image = SpriteManager.getImage("/character/CharacterShadowHalfAlpha.png");
        this.height = 20;
        this.width = 40;
        this.offsetX = 8;
        this.offsetY = 48;
    }

    public void render(Graphics2D graphics2D, int targetX, int targetY) {
        graphics2D.drawImage(image, targetX + offsetX, targetY + offsetY, width, height, null);
    }
}
