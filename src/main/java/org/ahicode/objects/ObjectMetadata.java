package org.ahicode.objects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjectMetadata {

    private final Rectangle solidArea;
    private final BufferedImage image;
    private final boolean collision;
    private final String name;

    public ObjectMetadata(String name, BufferedImage image, boolean collision, Rectangle solidArea) {
        this.image = image;
        this.name = name;
        this.collision = collision;
        this.solidArea = solidArea;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public boolean isCollision() {
        return collision;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }
}
