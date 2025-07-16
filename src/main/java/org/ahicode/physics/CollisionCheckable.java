package org.ahicode.physics;

import org.ahicode.entity.GameEntity;

public interface CollisionCheckable {
    void checkCollision(GameEntity entity);
    int checkObject(GameEntity entity, boolean isPlayer);
}
