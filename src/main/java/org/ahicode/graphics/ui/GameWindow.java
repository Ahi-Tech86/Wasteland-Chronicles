package org.ahicode.graphics.ui;

import javax.swing.*;

public class GameWindow {

    private final JFrame jFrame;

    public GameWindow(GamePanel gamePanel) {
        jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
