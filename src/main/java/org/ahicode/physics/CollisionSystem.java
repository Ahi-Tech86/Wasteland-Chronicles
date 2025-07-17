package org.ahicode.physics;

import org.ahicode.core.GameSettings;
import org.ahicode.entity.GameEntity;
import org.ahicode.entity.enums.Direction;
import org.ahicode.world.object.WorldObject;
import org.ahicode.world.object.ObjectsSetter;
import org.ahicode.world.TileManager;

import java.util.List;
import java.util.Set;

public class CollisionSystem implements CollisionCheckable {

    private final static Set<String> bushNames = Set.of("bush", "bushType1", "bushType2", "bushType3", "bushType4");
    private final ChunkSystem chunkSystem;
    private final TileManager tileManager;
    private final int tileSize;

    public CollisionSystem(TileManager tileManager, ObjectsSetter objectsSetter, int chunkSize) {
        this.tileSize = GameSettings.TILE_SIZE;
        this.tileManager = tileManager;
        this.chunkSystem = new ChunkSystem(chunkSize);

        for (WorldObject object : objectsSetter.getLevelObjects()) {
            if (object != null) {
                chunkSystem.addObject(object);
            }
        }
    }

    @Override
    public void checkCollision(GameEntity entity) {
        checkTile(entity);
    }

    @Override
    public int checkObject(GameEntity entity, boolean isPlayer) {
        long startTime = System.nanoTime();

        int index = 9999;

        List<WorldObject> nearByObjects = chunkSystem.getNearByObjects(
                entity.getWorldX(),
                entity.getWorldY(),
                1
        );

        int entityHitboxDefaultX = entity.getHitbox().x;
        int entityHitboxDefaultY = entity.getHitbox().y;

        entity.getHitbox().x = entity.getWorldX() + entity.getHitbox().x;
        entity.getHitbox().y = entity.getWorldY() + entity.getHitbox().y;

        for (int i = 0; i < nearByObjects.size(); i++) {
            WorldObject object = nearByObjects.get(i);

            if (object.isCollision()) {
                object.getSolidArea().x = object.getWorldX() + object.getSolidArea().x;
                object.getSolidArea().y = object.getWorldY() + object.getSolidArea().y;

                if (entity.getHitbox().intersects(object.getSolidArea())) {
                    entity.setCollisionOn(true);

                    if (isPlayer) {
                        index = i;
                    }
                }

                object.getSolidArea().x = object.getSolidAreaDefaultX();
                object.getSolidArea().y = object.getSolidAreaDefaultY();

            } else if (bushNames.contains(object.getName())) {
                object.getSolidArea().x = object.getWorldX() + object.getSolidArea().x;
                object.getSolidArea().y = object.getWorldY() + object.getSolidArea().y;

                if (entity.getHitbox().intersects(object.getSolidArea())) {
                    entity.playBushSoundEffect();
                }

                object.getSolidArea().x = object.getSolidAreaDefaultX();
                object.getSolidArea().y = object.getSolidAreaDefaultY();
            }
        }

        entity.getHitbox().x = entity.getHitboxDefaultX();
        entity.getHitbox().y = entity.getHitboxDefaultY();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        System.out.printf("Collision check: %d objects | Time: %,d ns %n", nearByObjects.size(), duration);

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
