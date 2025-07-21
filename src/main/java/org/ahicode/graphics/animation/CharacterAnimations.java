package org.ahicode.graphics.animation;

import org.ahicode.core.GameSettings;
import org.ahicode.graphics.GraphicsLoader;
import org.ahicode.entity.enums.Action;
import org.ahicode.entity.enums.Direction;
import org.ahicode.entity.Player;
import org.ahicode.graphics.rendering.ShadowSystem;

import java.awt.*;

public class CharacterAnimations {

    private final int handsOffsetIdleUp_X = GameSettings.SCALE * 2;
    private final int handsCommonOffset_X = GameSettings.SCALE;
    private final int bodyOffsetRightDir_X = GameSettings.SCALE * 3;
    private final int bodyOffset_X = GameSettings.SCALE * 2;
    private final AnimationController body;
    private final AnimationController hands;
    private final Player player;

    public CharacterAnimations(Player player) {
        this.player = player;
        this.body = new AnimationController(player, GraphicsLoader.PlayerAnimationsLoader.loadPlayerBaseAnimations(), new AnimationKey(Action.IDLE, Direction.DOWN), 25);
        this.hands = new AnimationController(player, GraphicsLoader.PlayerAnimationsLoader.loadPlayerHandsAnimations(), new AnimationKey(Action.IDLE, Direction.DOWN), 25);
    }

    public void update() {
        body.update();
        hands.update();
    }

    public void render(Graphics2D graphics2D, int x, int y) {
        ShadowSystem.renderShadow(graphics2D, player.getShadowType(), new Point(x, y));

        if (body.getCurrentKey().getDirection().equals(Direction.RIGHT)) {
            body.render(graphics2D, x + bodyOffsetRightDir_X, y);
        } else {
            body.render(graphics2D, x + bodyOffset_X, y);
        }

        if (hands.getCurrentKey().getAction().equals(Action.IDLE) && hands.getCurrentKey().getDirection().equals(Direction.UP)) {
            hands.render(graphics2D, x + handsOffsetIdleUp_X, y);
        } else if (hands.getCurrentKey().getAction().equals(Action.RUN) && hands.getCurrentKey().getDirection().equals(Direction.RIGHT)) {
            hands.render(graphics2D, x, y);
        } else {
            hands.render(graphics2D, x + handsCommonOffset_X, y);
        }
    }

    public void setAction(Action bodyAction, Action handsAction, Direction direction) {
        body.setAction(new AnimationKey(bodyAction, direction));
        hands.setAction(new AnimationKey(handsAction, direction));
    }
}
