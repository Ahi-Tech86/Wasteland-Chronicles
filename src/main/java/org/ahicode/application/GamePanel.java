package org.ahicode.application;

import org.ahicode.input.KeyboardInputHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final Game game;

    public GamePanel(Game game) {
        this.game = game;

        setPreferredSize(new Dimension(1024, 576));
        setDoubleBuffered(true);
        addKeyListener(new KeyboardInputHandler(this));
        setFocusable(true);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        game.render(graphics2D);
    }

    public Game getGame() {
        return game;
    }
}
