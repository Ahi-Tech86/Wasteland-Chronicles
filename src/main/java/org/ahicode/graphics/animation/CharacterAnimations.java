package org.ahicode.graphics.animation;

import org.ahicode.core.GameSettings;
import org.ahicode.graphics.GraphicsLoader;
import org.ahicode.entity.enums.Action;
import org.ahicode.entity.enums.Direction;
import org.ahicode.entity.Player;

import java.awt.*;

public class CharacterAnimations {

    private final AnimationController body;
    private final AnimationController hands;

    public CharacterAnimations(Player player) {
        this.body = new AnimationController(player, GraphicsLoader.PlayerAnimationsLoader.loadPlayerBaseAnimations(), new AnimationKey(Action.IDLE, Direction.DOWN), 25);
        this.hands = new AnimationController(player, GraphicsLoader.PlayerAnimationsLoader.loadPlayerHandsAnimations(), new AnimationKey(Action.IDLE, Direction.DOWN), 25);
    }

    public void update() {
        body.update();
        hands.update();
    }

    public void render(Graphics2D graphics2D, int x, int y, int width, int height) {
        body.render(graphics2D, x, y);

        if (hands.getCurrentKey().getAction().equals(Action.IDLE) && hands.getCurrentKey().getDirection().equals(Direction.UP)) {
            hands.render(graphics2D, x, y);
        } else if (hands.getCurrentKey().getAction().equals(Action.RUN) && hands.getCurrentKey().getDirection().equals(Direction.RIGHT)) {
            hands.render(graphics2D, x - GameSettings.SCALE * 2, y);
        } else {
            hands.render(graphics2D, x - GameSettings.SCALE, y);
        }
    }

    public void setAction(Action bodyAction, Action handsAction, Direction direction) {
        body.setAction(new AnimationKey(bodyAction, direction));
        hands.setAction(new AnimationKey(handsAction, direction));
    }
}
