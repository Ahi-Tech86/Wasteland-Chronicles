package org.ahicode.physics;

import org.ahicode.entities.GameEntity;

public interface CollisionCheckable {
    void checkCollision(GameEntity entity);
    int checkObject(GameEntity entity, boolean isPlayer);
}
