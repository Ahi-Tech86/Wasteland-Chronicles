package org.ahicode.tile;

public class TilePosition {
    private final int col;
    private final int row;
    private final int x;
    private final int y;

    public TilePosition(int col, int row, int x, int y) {
        this.col = col;
        this.row = row;
        this.x = x;
        this.y = y;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
