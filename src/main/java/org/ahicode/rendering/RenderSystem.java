package org.ahicode.rendering;

import org.ahicode.application.core.Game;
import org.ahicode.objects.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RenderSystem {

    private final Game game;

    public RenderSystem(Game game) {
        this.game = game;
    }

    public void render(Graphics2D graphics2D) {
        game.getTileManager().draw(graphics2D, game.getPlayer(), game.getCamera());

        // Splitting objects for proper rendering order
        List<GameObject> beforePlayer = new ArrayList<>();
        List<GameObject> afterPlayer = new ArrayList<>();

        for (GameObject object : game.getGameObjectsList()) {
            if (object != null) {
                if (object.getWorldY() + object.getSpriteHeight() < game.getPlayer().getWorldY()) {
                    beforePlayer.add(object);
                } else {
                    afterPlayer.add(object);
                }
            }
        }

        beforePlayer.forEach(obj -> obj.render(graphics2D, game.getPlayer(), game.getCamera()));

        game.getPlayer().render(graphics2D, game.getCamera().getScreenX(), game.getCamera().getScreenY());

        afterPlayer.forEach(obj -> obj.render(graphics2D, game.getPlayer(), game.getCamera()));

        game.getUserInterface().render(graphics2D);
    }
}
