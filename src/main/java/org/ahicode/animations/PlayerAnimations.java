package org.ahicode.animations;

import org.ahicode.entities.Action;
import org.ahicode.entities.Direction;
import org.ahicode.entities.Player;

import java.awt.*;

public class PlayerAnimations {

    private final AnimationHandler body;
    private final AnimationHandler hands;

    public PlayerAnimations(Player player) {
        this.body = new AnimationHandler(player, AnimationLoader.loadPlayerBodyAnimations(), new AnimationKey(Action.IDLE, Direction.DOWN), 25);
        this.hands = new AnimationHandler(player, AnimationLoader.loadPlayerHandsAnimations(), new AnimationKey(Action.IDLE, Direction.DOWN), 25);
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
