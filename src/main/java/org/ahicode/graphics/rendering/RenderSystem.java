package org.ahicode.graphics.rendering;

import org.ahicode.core.Game;
import org.ahicode.world.object.WorldObject;

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
        List<WorldObject> beforePlayer = new ArrayList<>();
        List<WorldObject> afterPlayer = new ArrayList<>();

        for (WorldObject object : game.getGameObjectsList()) {
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
