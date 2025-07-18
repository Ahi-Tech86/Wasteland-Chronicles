package org.ahicode.entity;

import org.ahicode.graphics.animation.CharacterAnimations;
import org.ahicode.entity.enums.Action;
import org.ahicode.physics.CollisionCheckable;
import org.ahicode.graphics.rendering.ShadowSystem;
import org.ahicode.audio.Sound;

import java.awt.*;

import static org.ahicode.core.GameSettings.*;
import static org.ahicode.entity.enums.Action.*;
import static org.ahicode.entity.enums.Direction.*;

public class Player extends GameEntity {

    private final CollisionCheckable collisionCheckable;
    private final ShadowSystem.ShadowType shadowType;
    private final CharacterAnimations animations;
    private boolean hasWeapon;

    public Player(int x, int y, CollisionCheckable collisionCheckable, Sound soundEffects) {
        super(x, y, soundEffects);
        setSpeed(2);
        hasWeapon = false;
        setHitbox(new Rectangle(4 * SCALE, 5 * SCALE, 8 * SCALE, 11 * SCALE));
        setHitboxDefaultX(getHitbox().x);
        setHitboxDefaultY(getHitbox().y);

        this.collisionCheckable = collisionCheckable;
        shadowType = ShadowSystem.ShadowType.PLAYER;
        animations = new CharacterAnimations(this);
    }

    public void update() {
        updatePosition();
        animations.update();
        setAnimation();
    }

    public void render(Graphics2D graphics2D, int screenX, int screenY) {
        ShadowSystem.renderShadow(graphics2D, shadowType, new Point(screenX, screenY));
        animations.render(graphics2D, screenX, screenY, TILE_SIZE, TILE_SIZE);
        //graphics2D.setColor(Color.PINK);
        //graphics2D.drawRect(screenX + getHitbox().x, screenY + getHitbox().y, (int) getHitbox().getWidth(), (int) getHitbox().getHeight());
    }

    private void setAnimation() {
        Action bodyAction = this.isMoving() ? RUNNING : IDLE;
        Action handsAction = hasWeapon ? SHOTGUN_HOLD : (this.isMoving() ? RUNNING : IDLE);
        animations.setAction(bodyAction, handsAction, getCurrentDirection());
    }

    private void updatePosition() {
        setMoving(false);

        int originalWorldX = getWorldX();
        int originalWorldY = getWorldY();

        int dirX = 0;
        int dirY = 0;

        if (isLeft() && !isRight()) {
            dirX = -1;
            setMoving(true);
            setCurrentDirection(LEFT);
        } else if (isRight() && !isLeft()) {
            dirX = 1;
            setMoving(true);
            setCurrentDirection(RIGHT);
        }

        if (isUp() && !isDown()) {
            dirY = -1;
            setMoving(true);
            setCurrentDirection(UP);
        } else if (isDown() && !isUp()) {
            dirY = 1;
            setMoving(true);
            setCurrentDirection(DOWN);
        }

        if (dirX != 0 || dirY != 0) {
            int speed = getSpeed();
            int moveX = dirX * speed;
            int moveY = dirY * speed;

            if (dirX != 0 && dirY != 0) {
                moveX = (dirX * speed * 707) / 1000;
                moveY = (dirY * speed * 707) / 1000;

                if (moveX == 0) moveX = dirX;
                if (moveY == 0) moveY = dirY;
            }

            setWorldX(getWorldX() + moveX);
            setWorldY(getWorldY() + moveY);
        }

        collisionCheckable.checkCollision(this);
        collisionCheckable.checkObject(this, true);

        if (isCollisionOn()) {
            setWorldX(originalWorldX);
            setWorldY(originalWorldY);
            setCollisionOn(false);
        }
    }
}
