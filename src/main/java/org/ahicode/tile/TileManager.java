package org.ahicode.tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TileManager {

    private final int tileSize, maxScreenCol, maxScreenRow;
    private List<TilePosition> tilePositions;
    private final int[][] tileMapNum;
    private final Tile[] tiles;

    public TileManager(int tileSize, int maxScreenCol, int maxScreenRow) {
        this.maxScreenCol = maxScreenCol;
        this.maxScreenRow = maxScreenRow;
        this.tileSize = tileSize;

        tiles = TileLoader.loadTileset();
        tileMapNum = TileLoader.loadMap(maxScreenCol, maxScreenRow);
        calculateTilePositions();
    }

    public void render(Graphics2D graphics2D) {
        for (TilePosition position : tilePositions) {
            int tileNum = tileMapNum[position.getCol()][position.getRow()];
            graphics2D.drawImage(
                    tiles[tileNum].getImage(),
                    position.getX(),
                    position.getY(),
                    tileSize,
                    tileSize,
                    null
            );
        }
    }

    private void calculateTilePositions() {
        tilePositions = new ArrayList<>();
        int x = 0;
        int y = 0;

        for (int row = 0; row < maxScreenRow; row++) {
            for (int col = 0; col < maxScreenCol; col++) {
                tilePositions.add(new TilePosition(col, row, x, y));
                x += tileSize;
            }

            x = 0;
            y += tileSize;
        }
    }
}
