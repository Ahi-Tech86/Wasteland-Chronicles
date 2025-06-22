package org.ahicode.main;

import org.ahicode.KeyboardHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final Game game;

    public GamePanel(Game game) {
        this.game = game;

        setPreferredSize(new Dimension(1024, 576));
        setDoubleBuffered(true);
        addKeyListener(new KeyboardHandler());
        setFocusable(true);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        game.render(graphics2D);
    }
}
