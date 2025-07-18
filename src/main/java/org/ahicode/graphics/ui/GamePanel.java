package org.ahicode.graphics.ui;

import lombok.Getter;
import org.ahicode.core.Game;
import org.ahicode.core.InputHandler;

import javax.swing.*;
import java.awt.*;

import static org.ahicode.core.GameSettings.*;

@Getter
public class GamePanel extends JPanel {

    private final Game game;

    public GamePanel(Game game) {
        this.game = game;

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        addKeyListener(new InputHandler(this));
        setFocusable(true);
        setDoubleBuffered(true);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());

        game.render(graphics2D);
    }
}
