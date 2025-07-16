package org.ahicode.physics;

import org.ahicode.core.GameSettings;
import org.ahicode.entity.GameEntity;
import org.ahicode.entity.enums.Direction;
import org.ahicode.world.object.WorldObject;
import org.ahicode.world.object.ObjectsSetter;
import org.ahicode.world.TileManager;

import java.util.Set;

public class CollisionSystem implements CollisionCheckable {

    private final static Set<String> bushNames = Set.of("bush", "bushType1", "bushType2", "bushType3", "bushType4");
    private final ObjectsSetter objectsSetter;
    private final TileManager tileManager;
    private final int tileSize;

    public CollisionSystem(TileManager tileManager, ObjectsSetter objectsSetter) {
        this.tileSize = GameSettings.TILE_SIZE;
        this.tileManager = tileManager;
        this.objectsSetter = objectsSetter;
    }

    @Override
    public void checkCollision(GameEntity entity) {
        checkTile(entity);
    }

    @Override
    public int checkObject(GameEntity entity, boolean isPlayer) {
        int index = 9999;

        WorldObject[] objects = objectsSetter.getLevelObjects();

        int entityHitboxDefaultX = entity.getHitbox().x;
        int entityHitboxDefaultY = entity.getHitbox().y;

        entity.getHitbox().x = entity.getWorldX() + entity.getHitbox().x;
        entity.getHitbox().y = entity.getWorldY() + entity.getHitbox().y;

        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null && objects[i].isCollision()) {
                // Get object solid area
                objects[i].getSolidArea().x = objects[i].getWorldX() + objects[i].getSolidArea().x;
                objects[i].getSolidArea().y = objects[i].getWorldY() + objects[i].getSolidArea().y;

                if (entity.getHitbox().intersects(objects[i].getSolidArea())) {
                    entity.setCollisionOn(true);

                    if (isPlayer) {
                        index = i;
                    }
                }

                objects[i].getSolidArea().x = objects[i].getSolidAreaDefaultX();
                objects[i].getSolidArea().y = objects[i].getSolidAreaDefaultY();

            } else if (objects[i] != null && bushNames.contains(objects[i].getName())) {
                objects[i].getSolidArea().x = objects[i].getWorldX() + objects[i].getSolidArea().x;
                objects[i].getSolidArea().y = objects[i].getWorldY() + objects[i].getSolidArea().y;

                if (entity.getHitbox().intersects(objects[i].getSolidArea())) {
                    entity.playBushSoundEffect();
                }

                objects[i].getSolidArea().x = objects[i].getSolidAreaDefaultX();
                objects[i].getSolidArea().y = objects[i].getSolidAreaDefaultY();
            }
        }

        entity.getHitbox().x = entity.getHitboxDefaultX();
        entity.getHitbox().y = entity.getHitboxDefaultY();

        return index;
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
