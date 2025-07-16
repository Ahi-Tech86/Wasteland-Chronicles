package org.ahicode.core;

public class GameSettings {

    // Game window dimensions
    public static final int WINDOW_WIDTH = 1024;
    public static final int WINDOW_HEIGHT = 576;

    // Game loop settings
    public static final int FPS_SET = 120;
    public static final int UPS_SET = 200;

    // Tiles and scale settings
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int SCALE = 4;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;

    // Display settings
    public static final int MAX_SCREEN_COL = 16;
    public static final int MAX_SCREEN_ROW = 9;

    // Game states
    public static final int PLAY_STATE = 1;
    public static final int PAUSE_STATE = 2;

    // Alpha value for shadows
    public static final float SHADOW_ALPHA = 0.3f;
}
