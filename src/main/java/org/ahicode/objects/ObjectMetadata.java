package org.ahicode.objects;

import java.awt.image.BufferedImage;

public class ObjectMetadata {

    private final BufferedImage image;
    private final boolean collision;
    private final String name;

    public ObjectMetadata(String name, BufferedImage image, boolean collision) {
        this.image = image;
        this.name = name;
        this.collision = collision;
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
}
