package org.ahicode.entities;

import org.ahicode.animations.PlayerAnimations;
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

    public Player(int x, int y, int screenWidth, int screenHeight, int tileSize, CollisionCheckable collisionCheckable) {
        super(x, y);
        setSpeed(2);
        hasWeapon = false;
        setHitbox(new Rectangle(4 * 4, 5 * 4, 8 * 4, 11 * 4));

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
        animations.render(graphics2D, screenX, screenY, 64, 64);
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

        if (isLeft() && !isRight()) {
            setWorldX(getWorldX() - getSpeed());
            setMoving(true);
            setCurrentDirection(LEFT);
        } else if (isRight() && !isLeft()) {
            setWorldX(getWorldX() + getSpeed());
            setMoving(true);
            setCurrentDirection(RIGHT);
        }

        if (isUp() && !isDown()) {
            setWorldY(getWorldY() - getSpeed());
            setMoving(true);
            setCurrentDirection(UP);
        } else if (isDown() && !isUp()) {
            setWorldY(getWorldY() + getSpeed());
            setMoving(true);
            setCurrentDirection(DOWN);
        }

        collisionCheckable.checkCollision(this);

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
