package org.ahicode.utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SpriteManager {

    public static BufferedImage scaleImage(BufferedImage originalImage, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, width, height, null);
        graphics2D.dispose();

        return scaledImage;
    }

    public static BufferedImage flipImageHorizontally(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();

        BufferedImage flipped = new BufferedImage(width, height, original.getType());
        Graphics2D graphics2D = flipped.createGraphics();
        graphics2D.drawImage(original, width, 0, -width, height, null);
        graphics2D.dispose();

        return flipped;
    }

    public static BufferedImage getImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(SpriteManager.class.getResourceAsStream(path)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage[] loadFramesFromSheet(String path, int width, int height, int row, int col, int colsPer, int size) {
        BufferedImage[] frames = new BufferedImage[size];

        for (int i = 0; i < size; i++) {
            frames[i] = getImage(path).getSubimage(col * width, row * height, width, height);

            if (col == colsPer - 1) {
                col = 0;
                row++;
            } else {
                col++;
            }
        }

        return frames;
    }

    public static BufferedImage extractFromSheet(String path, int x, int y, int width, int height) {
        BufferedImage spriteSheet = getImage(path);
        return spriteSheet.getSubimage(x, y, width, height);
    }

    public static void cropAndSave(String path, String outputDir, int spriteWidth, int spriteHeight, int fileNumber) {
        BufferedImage spriteSheet = getImage(path);
        int sheetWidth = spriteSheet.getWidth();
        int sheetHeight = spriteSheet.getHeight();

        if (sheetWidth % spriteWidth != 0 || sheetHeight % spriteHeight != 0) {
            throw new IllegalArgumentException("Spritesheet dimensions are not divisible by sprite size");
        }

        int cols = sheetWidth / spriteWidth;
        int rows = sheetHeight / spriteHeight;
        int totalTiles = cols * rows;

        File outputDirectory = new File(outputDir);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        int tileNumber = fileNumber;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                BufferedImage sprite = spriteSheet.getSubimage(
                        x * spriteWidth,
                        y * spriteHeight,
                        spriteWidth,
                        spriteHeight
                );

                String fileName = String.format("%03d.png", tileNumber + 1);

                File outputFile = new File(outputDirectory, fileName);

                try {
                    ImageIO.write(sprite, "png", outputFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                tileNumber++;
            }
        }
    }

    public static void main(String[] args) {
        SpriteManager.cropAndSave(
                "/tileset/grass-tileset.png",
                "src/main/resources/tileset/cropped",
                16, 16, 40
        );
    }
}
