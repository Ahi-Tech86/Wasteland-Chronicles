package org.ahicode.objects;

import org.ahicode.utility.SpriteManager;

public class Tent extends GameObject {

    public Tent(int worldX, int worldY, int tileSize) {
        super(worldX, worldY, tileSize);
        setImage(SpriteManager.extractFromSheet("/objects/template(3).png", 48, 143, 48, 48));
        setSpriteWidth(48 * 4);
        setSpriteHeight(48 * 4);
        setName("tent");
    }
}
