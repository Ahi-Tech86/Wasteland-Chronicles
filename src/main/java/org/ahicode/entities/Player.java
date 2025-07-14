package org.ahicode.entities;

import org.ahicode.animations.PlayerAnimations;
import org.ahicode.application.core.GameSettings;
import org.ahicode.entities.enums.Action;
import org.ahicode.physics.CollisionCheckable;

import java.awt.*;

import static org.ahicode.entities.enums.Action.*;
import static org.ahicode.entities.enums.Direction.*;

public class Player extends GameEntity {

    private final CollisionCheckable collisionCheckable;
    private final PlayerAnimations animations;
    private final EntityShadow entityShadow;
    private boolean hasWeapon;

    public Player(int x, int y, CollisionCheckable collisionCheckable) {
        super(x, y);
        setSpeed(2);
        hasWeapon = false;
        setHitbox(new Rectangle(4 * 4, 5 * 4, 8 * 4, 11 * 4));
        setHitboxDefaultX(getHitbox().x);
        setHitboxDefaultY(getHitbox().y);

        this.collisionCheckable = collisionCheckable;
        entityShadow = new EntityShadow();
        animations = new PlayerAnimations(this);
    }

    public void update() {
        updatePosition();
        animations.update();
        setAnimation();
    }

    public void render(Graphics2D graphics2D, int screenX, int screenY) {
        entityShadow.render(graphics2D, screenX, screenY);
        animations.render(graphics2D, screenX, screenY, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE);
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

    public void setHasWeapon(boolean hasWeapon) {
        this.hasWeapon = hasWeapon;
    }
}
