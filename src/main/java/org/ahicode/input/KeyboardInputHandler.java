package org.ahicode.input;

import org.ahicode.application.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class KeyboardInputHandler implements KeyListener {

    private final GamePanel gamePanel;

    public KeyboardInputHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // NOTHING
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_W -> gamePanel.getGame().getPlayer().setUp(true);
            case VK_S -> gamePanel.getGame().getPlayer().setDown(true);
            case VK_A -> gamePanel.getGame().getPlayer().setLeft(true);
            case VK_D -> gamePanel.getGame().getPlayer().setRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_W -> gamePanel.getGame().getPlayer().setUp(false);
            case VK_S -> gamePanel.getGame().getPlayer().setDown(false);
            case VK_A -> gamePanel.getGame().getPlayer().setLeft(false);
            case VK_D -> gamePanel.getGame().getPlayer().setRight(false);
        }
    }
}
