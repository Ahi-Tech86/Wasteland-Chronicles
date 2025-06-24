package org.ahicode.application;

import org.ahicode.entities.Player;
import org.ahicode.tile.TileManager;

import java.awt.*;

public class Game implements Runnable {

    private final GameWindow gameWindow;
    private final GamePanel gamePanel;
    private final Thread gameThread;
    private final TileManager tileManager;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private final int originalTileSize = 16;
    private final int scale = 4;
    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 9;

    // WORLD SETTINGS
    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;

    private final Player player;

    public Game() {
        player = new Player(100, 200, tileSize * maxScreenCol, tileSize * maxScreenRow, tileSize);
        tileManager = new TileManager(tileSize, maxWorldCol, maxWorldRow);

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        player.update();
    }

    public void render(Graphics2D graphics2D) {
        tileManager.draw(graphics2D, getPlayer());
        player.render(graphics2D);
    }

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaF = 0;
        double deltaU = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaF += (currentTime - previousTime) / timePerFrame;
            deltaU += (currentTime - previousTime) / timePerUpdate;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.printf("FPS: %s | UPS: %s %n", frames, updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public Player getPlayer() {
        return player;
    }
}
