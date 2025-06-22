package org.ahicode.utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class SpriteManager {

    public static BufferedImage getSpriteImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(SpriteManager.class.getResourceAsStream(path)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage[] loadFramesFromSpriteSheet(String path, int width, int height, int row, int col, int colsPer, int size) {
        BufferedImage[] frames = new BufferedImage[size];

        for (int i = 0; i < size; i++) {
            frames[i] = getSpriteImage(path).getSubimage(col * width, row * height, width, height);

            if (col == colsPer - 1) {
                col = 0;
                row++;
            } else {
                col++;
            }
        }

        return frames;
    }
}
