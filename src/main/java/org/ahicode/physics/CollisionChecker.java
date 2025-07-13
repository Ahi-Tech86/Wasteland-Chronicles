package org.ahicode.physics;

import org.ahicode.application.core.GameSettings;
import org.ahicode.entities.GameEntity;
import org.ahicode.entities.enums.Direction;
import org.ahicode.tile.TileManager;

public class CollisionChecker implements CollisionCheckable {

    private final TileManager tileManager;
    private final int tileSize;

    public CollisionChecker(TileManager tileManager) {
        this.tileSize = GameSettings.TILE_SIZE;
        this.tileManager = tileManager;
    }

    @Override
    public void checkCollision(GameEntity entity) {
        checkTile(entity);
    }

    private void checkTile(GameEntity entity) {
        int entityLeftWorldX = entity.getWorldX() + entity.getHitbox().x;
        int entityRightWorldX = entity.getWorldX() + entity.getHitbox().x + entity.getHitbox().width;
        int entityTopWorldY = entity.getWorldY() + entity.getHitbox().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getHitbox().y + entity.getHitbox().height;

        int entityLeftCol = entityLeftWorldX / tileSize;
        int entityRightCol = entityRightWorldX / tileSize;
        int entityTopRow = entityTopWorldY / tileSize;
        int entityBottomRow = entityBottomWorldY / tileSize;

        int tileNum1, tileNum2;

        Direction direction = entity.getCurrentDirection();

        switch (direction) {
            case UP -> {
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / tileSize;
                tileNum1 = tileManager.getTileMapNum()[entityLeftCol][entityTopRow];
                tileNum2 = tileManager.getTileMapNum()[entityRightCol][entityTopRow];

                if (tileManager.getTiles()[tileNum1].isCollision() || tileManager.getTiles()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
            }
            case DOWN -> {
                entityBottomRow = (entityBottomWorldY - entity.getSpeed()) / tileSize;
                tileNum1 = tileManager.getTileMapNum()[entityLeftCol][entityBottomRow];
                tileNum2 = tileManager.getTileMapNum()[entityRightCol][entityBottomRow];

                if (tileManager.getTiles()[tileNum1].isCollision() || tileManager.getTiles()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
            }
            case LEFT -> {
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / tileSize;
                tileNum1 = tileManager.getTileMapNum()[entityLeftCol][entityTopRow];
                tileNum2 = tileManager.getTileMapNum()[entityLeftCol][entityBottomRow];

                if (tileManager.getTiles()[tileNum1].isCollision() || tileManager.getTiles()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
            }
            case RIGHT -> {
                entityRightCol = (entityRightWorldX - entity.getSpeed()) / tileSize;
                tileNum1 = tileManager.getTileMapNum()[entityRightCol][entityTopRow];
                tileNum2 = tileManager.getTileMapNum()[entityRightCol][entityBottomRow];

                if (tileManager.getTiles()[tileNum1].isCollision() || tileManager.getTiles()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
            }
        }
    }
}
