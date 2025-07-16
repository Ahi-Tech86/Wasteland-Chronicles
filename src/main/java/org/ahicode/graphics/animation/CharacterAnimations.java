package org.ahicode.graphics.animation;

import org.ahicode.graphics.GraphicsLoader;
import org.ahicode.entity.enums.Action;
import org.ahicode.entity.enums.Direction;
import org.ahicode.entity.Player;

import java.awt.*;

public class CharacterAnimations {

    private final AnimationController body;
    private final AnimationController hands;

    public CharacterAnimations(Player player) {
        this.body = new AnimationController(player, GraphicsLoader.loadPlayerBodyAnimations(), new AnimationKey(Action.IDLE, Direction.DOWN), 25);
        this.hands = new AnimationController(player, GraphicsLoader.loadPlayerHandsAnimations(), new AnimationKey(Action.IDLE, Direction.DOWN), 25);
    }

    public void update() {
        body.update();
        hands.update();
    }

    public void render(Graphics2D graphics2D, int x, int y, int width, int height) {
        body.render(graphics2D, x, y, width, height);

        if (hands.getCurrentKey().getAction().equals(Action.SHOTGUN_HOLD)) {
            hands.render(graphics2D, x - 45, y, width + 64, height);
        } else {
            hands.render(graphics2D, x, y, width, height);
        }
    }

    public void setAction(Action bodyAction, Action handsAction, Direction direction) {
        body.setAction(new AnimationKey(bodyAction, direction));
        hands.setAction(new AnimationKey(handsAction, direction));
    }
}
