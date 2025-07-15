package org.ahicode.application.rendering;

import org.ahicode.application.core.GameSettings;

import java.awt.*;

public class UserInterface {

    GamePanel gamePanel;
    Graphics2D graphics2D;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public UserInterface(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.arial_40 = new Font("Arial", Font.PLAIN, 40);
        this.arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    public void render(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;

        graphics2D.setFont(arial_40);
        graphics2D.setColor(Color.white);

        if (gamePanel.getGame().getGameState() == GameSettings.PLAY_STATE) {
            // Nothing now
        } else if (gamePanel.getGame().getGameState() == GameSettings.PAUSE_STATE) {
            drawPauseScreen();
        }
    }

    private void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = GameSettings.TILE_SIZE * GameSettings.MAX_SCREEN_ROW / 2;

        graphics2D.drawString(text, x, y);
    }

    private int getXforCenteredText(String text) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        return GameSettings.TILE_SIZE * GameSettings.MAX_SCREEN_COL / 2 - length / 2;
    }
}
