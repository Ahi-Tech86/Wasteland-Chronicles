package org.ahicode.core;

import lombok.Getter;
import lombok.Setter;
import org.ahicode.graphics.ui.GamePanel;
import org.ahicode.graphics.ui.GameWindow;
import org.ahicode.graphics.ui.UserInterface;
import org.ahicode.entity.Camera;
import org.ahicode.entity.Player;
import org.ahicode.world.object.WorldObject;
import org.ahicode.world.object.ObjectsSetter;
import org.ahicode.physics.CollisionCheckable;
import org.ahicode.physics.CollisionSystem;
import org.ahicode.graphics.rendering.RenderSystem;
import org.ahicode.audio.Sound;
import org.ahicode.audio.AudioManager;
import org.ahicode.world.TileManager;

import java.awt.*;

import static org.ahicode.core.GameSettings.*;

@Getter
@Setter
public class Game implements Runnable {

    private int gameState;

    private final GamePanel gamePanel;
    private final Thread gameThread;
    private final TileManager tileManager;

    // WORLD SETTINGS
    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;

    private final ObjectsSetter objectsSetter;
    private final RenderSystem renderSystem;
    private final UserInterface userInterface;
    private final Sound soundEffects;
    private final WorldObject[] worldObjectsList;
    private final Player player;
    private final Camera camera;

    public Game() {
        soundEffects = new AudioManager();
        renderSystem = new RenderSystem(this);
        tileManager = new TileManager(maxWorldCol, maxWorldRow);
        objectsSetter = new ObjectsSetter();
        CollisionCheckable collisionCheckable = new CollisionSystem(tileManager, objectsSetter, 8 * TILE_SIZE);

        player = new Player(11 * TILE_SIZE, 11 * TILE_SIZE, collisionCheckable, getSoundEffects());
        camera = new Camera(TILE_SIZE * MAX_SCREEN_COL, TILE_SIZE * MAX_SCREEN_ROW);
        worldObjectsList = objectsSetter.getLevelObjects();

        gamePanel = new GamePanel(this);
        userInterface = new UserInterface(gamePanel);
        new GameWindow(gamePanel);
        gameThread = new Thread(this);
        gameThread.start();

        gameState = PLAY_STATE;
    }

    public void update() {
        if (gameState == PLAY_STATE) {
            player.update();
        }
    }

    public void render(Graphics2D graphics2D) {
        renderSystem.render(graphics2D);
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
}
