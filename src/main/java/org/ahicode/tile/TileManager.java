package org.ahicode.tile;

import org.ahicode.application.Game;

import java.awt.*;

public class TileManager {

    private Game game;
    private Tile[] tiles;
    private int[][] tileMapNum;

    public TileManager(Game game) {
        this.game = game;

        tiles = TileLoader.loadTileset();
        tileMapNum = TileLoader.loadMap(game.getMaxScreenCol(), game.getMaxScreenRow());
    }

    public void render(Graphics2D graphics2D) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < game.getMaxScreenCol() && row < game.getMaxScreenRow()) {
            int tileNum = tileMapNum[col][row];

            graphics2D.drawImage(tiles[tileNum].getImage(), x, y, game.getTileSize(), game.getTileSize(), null);
            col++;
            x += game.getTileSize();

            if (col == game.getMaxScreenCol()) {
                col = 0;
                x = 0;
                row++;
                y += game.getTileSize();
            }
        }
    }
}
