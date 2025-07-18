package org.ahicode.world;

import lombok.Getter;
import org.ahicode.core.GameSettings;
import org.ahicode.entity.Camera;
import org.ahicode.entity.Player;

import java.awt.*;

public class TileManager {

    private final int tileSize, maxWorldCol, maxWorldRow;
    @Getter
    private final int[][] tileMapNum;
    @Getter
    private final Tile[] tiles;

    public TileManager(int maxWorldCol, int maxWorldRow) {
        this.maxWorldCol = maxWorldCol;
        this.maxWorldRow = maxWorldRow;
        this.tileSize = GameSettings.TILE_SIZE;

        tiles = TileLoader.loadTilesetFromTsx();
        tileMapNum = TileLoader.loadMapFromTmx(maxWorldCol, maxWorldRow);
    }

    public void draw(Graphics2D graphics2D, Player player, Camera camera) {
        // Getting player coordinates and screen borders
        int playerWorldX = player.getWorldX();
        int playerWorldY = player.getWorldY();
        int playerScreenX = camera.getScreenX();
        int playerScreenY = camera.getScreenY();

        // Calculate borders of visible area (in world coordinates)
        // We add/subtract 'tileSize' to ensure tiles at the edges are fully rendered
        // and prevent empty gaps when the camera moves near map borders.
        int leftBoundary = playerWorldX - playerScreenX - tileSize;
        int rightBoundary = playerWorldX + playerScreenX + tileSize;
        int topBoundary = playerWorldY - playerScreenY - tileSize;
        int bottomBoundary = playerWorldY + playerScreenY + tileSize;

        // Converting borders values in tiles index's
        int startCol = Math.max(0, leftBoundary / tileSize);
        int endCol = Math.min(maxWorldCol - 1, rightBoundary / tileSize);
        int startRow = Math.max(0, topBoundary / tileSize);
        int endRow = Math.min(maxWorldRow - 1, bottomBoundary / tileSize);

        // Rendering only visible tiles
        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                int tileNum = tileMapNum[col][row];

                // Calculating screen coordinates
                int worldX = col * tileSize;
                int worldY = row * tileSize;
                int screenX = worldX - playerWorldX + playerScreenX;
                int screenY = worldY - playerWorldY + playerScreenY;

                graphics2D.drawImage(
                        tiles[tileNum].getImage(),
                        screenX,
                        screenY,
                        tileSize,
                        tileSize,
                        null
                );
            }
        }
    }
}
