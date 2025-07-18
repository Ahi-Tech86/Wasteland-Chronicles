package org.ahicode.world;

import lombok.Getter;

import java.awt.image.BufferedImage;

@Getter
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
}
