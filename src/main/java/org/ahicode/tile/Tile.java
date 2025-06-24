package org.ahicode.tile;

import java.awt.image.BufferedImage;

public class Tile {

    private final BufferedImage image;
    private final boolean collision;

    public Tile(BufferedImage image, boolean collision) {
        this.image = image;
        this.collision = collision;
    }

    public Tile(BufferedImage image) {
        this.image = image;
        this.collision = false;
    }

    public BufferedImage getImage() {
        return image;
    }

    public boolean isCollision() {
        return collision;
    }
}
