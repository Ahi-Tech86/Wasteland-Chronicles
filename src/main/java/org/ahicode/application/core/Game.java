package org.ahicode.application.core;

import org.ahicode.application.rendering.GamePanel;
import org.ahicode.application.rendering.GameWindow;
import org.ahicode.application.sound.SoundManager;
import org.ahicode.entities.Camera;
import org.ahicode.entities.Player;
import org.ahicode.objects.GameObject;
import org.ahicode.objects.ObjectsSetter;
import org.ahicode.physics.CollisionCheckable;
import org.ahicode.physics.CollisionChecker;
import org.ahicode.tile.TileManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.ahicode.application.core.GameSettings.*;

public class Game implements Runnable {

    private static Game instance;
    private final GameWindow gameWindow;
    private final GamePanel gamePanel;
    private final Thread gameThread;
    private final TileManager tileManager;

    // WORLD SETTINGS
    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;

    private final SoundManager soundEffects;
    private final GameObject[] gameObjectsList;
    private final CollisionCheckable collisionCheckable;
    private final ObjectsSetter objectsSetter;
    private final Player player;
    private final Camera camera;

    public Game() {
        instance = this;
        soundEffects = new SoundManager();
        tileManager = new TileManager(maxWorldCol, maxWorldRow);
        objectsSetter = new ObjectsSetter();
        collisionCheckable = new CollisionChecker(tileManager, objectsSetter);

        player = new Player(11 * TILE_SIZE, 11 * TILE_SIZE, collisionCheckable);
        camera = new Camera(TILE_SIZE * MAX_SCREEN_COL, TILE_SIZE * MAX_SCREEN_ROW);
        gameObjectsList = objectsSetter.getLevelObjects();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        player.update();
    }

    public void render(Graphics2D graphics2D) {
        tileManager.draw(graphics2D, getPlayer(), getCamera());

        List<GameObject> beforePlayer = new ArrayList<>();
        List<GameObject> afterPlayer = new ArrayList<>();

        for (GameObject object : gameObjectsList) {
            if (object != null) {
                if (object.getWorldY() + object.getSpriteHeight() < player.getWorldY()) {
                    beforePlayer.add(object);
                } else {
                    afterPlayer.add(object);
                }
            }
        }

        for (GameObject object : beforePlayer) {
            object.render(graphics2D, player, camera);
        }

        player.render(graphics2D, camera.getScreenX(), camera.getScreenY());

        for (GameObject object : afterPlayer) {
            object.render(graphics2D, player, camera);
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / GameSettings.FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / GameSettings.UPS_SET;

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

    public Camera getCamera() {
        return camera;
    }

    public void playSE(int i) {
        soundEffects.setFile(i);
        soundEffects.play();
    }

    public static Game getInstance() {
        return instance;
    }
}
