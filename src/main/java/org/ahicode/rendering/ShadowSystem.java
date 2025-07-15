package org.ahicode.rendering;

import org.ahicode.application.core.GameSettings;
import org.ahicode.utility.SpriteManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ShadowSystem {

    private static final BufferedImage TREE_BUSH_SHADOW = applyAlpha(SpriteManager.getImage("/environment/TreeShadow.png"));
    private static final BufferedImage PLAYER_SHADOW = applyAlpha(SpriteManager.getImage("/character/CharacterShadow.png"));

    public enum ShadowType {
        BUSH(14, 5, TREE_BUSH_SHADOW, 1, 13),
        TREE(14, 5, TREE_BUSH_SHADOW, 1, GameSettings.ORIGINAL_TILE_SIZE * 2 - 3),
        PLAYER(12, 5, PLAYER_SHADOW, 2, 12),
        NONE(0, 0, null, 0, 0);

        final int width;
        final int height;
        final BufferedImage image;
        final int offsetX;
        final int offsetY;

        ShadowType(int width, int height, BufferedImage image, int offsetX, int offsetY) {
            this.width = width;
            this.height = height;
            this.image = image;
            this.offsetX = offsetX;
            this.offsetY = offsetY;
        }
    }

    public static void renderShadow(Graphics2D graphics2D, ShadowType type, Point screenCoords) {
        if (type != ShadowType.NONE && type.image != null) {
            graphics2D.drawImage(
                    type.image,
                    screenCoords.x + type.offsetX * GameSettings.SCALE,
                    screenCoords.y + type.offsetY * GameSettings.SCALE,
                    type.width * GameSettings.SCALE,
                    type.height * GameSettings.SCALE,
                    null
            );
        }
    }

    private static BufferedImage applyAlpha(BufferedImage original) {
        if (original == null) return null;

        BufferedImage result = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = result.createGraphics();

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, GameSettings.SHADOW_ALPHA));
        graphics2D.drawImage(original, 0, 0, null);
        graphics2D.dispose();

        return result;
    }
}
