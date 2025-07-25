package org.ahicode.core;

import org.ahicode.graphics.ui.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class InputHandler implements KeyListener {

    private final GamePanel gamePanel;

    public InputHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // NOTHING
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gamePanel.getGame().getGameState() == GameSettings.PLAY_STATE) {
            switch (e.getKeyCode()) {
                case VK_W -> gamePanel.getGame().getPlayer().setUp(true);
                case VK_S -> gamePanel.getGame().getPlayer().setDown(true);
                case VK_A -> gamePanel.getGame().getPlayer().setLeft(true);
                case VK_D -> gamePanel.getGame().getPlayer().setRight(true);
                case VK_P -> gamePanel.getGame().setGameState(GameSettings.PAUSE_STATE);
            }
        } else if (gamePanel.getGame().getGameState() == GameSettings.PAUSE_STATE) {
            if (e.getKeyCode() == VK_P) {
                gamePanel.getGame().setGameState(GameSettings.PLAY_STATE);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (gamePanel.getGame().getGameState() == GameSettings.PLAY_STATE) {
            switch (e.getKeyCode()) {
                case VK_W -> gamePanel.getGame().getPlayer().setUp(false);
                case VK_S -> gamePanel.getGame().getPlayer().setDown(false);
                case VK_A -> gamePanel.getGame().getPlayer().setLeft(false);
                case VK_D -> gamePanel.getGame().getPlayer().setRight(false);
            }
        }
    }
}
