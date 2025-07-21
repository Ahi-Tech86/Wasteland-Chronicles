package org.ahicode.graphics;

import org.ahicode.entity.enums.Direction;
import org.ahicode.entity.enums.Action;
import org.ahicode.graphics.animation.AnimationKey;
import org.ahicode.utility.SpriteManager;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GraphicsLoader {
    private static final String characterBase = "/old_assets/character/PlayerBase.png";
    private static final String characterHands = "/old_assets/character/PlayerHands.png";
    private static final String shotgunHands = "/old_assets/character/Shotgun.png";

    public static class PlayerAnimationsLoader {

        public static Map<AnimationKey, BufferedImage[]> loadPlayerBaseAnimations() {
            Map<AnimationKey, BufferedImage[]> animations = new HashMap<>();

            BufferedImage[] idleUp = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.IDLE.baseUp, 11, 16, 0, 0, 6, 6
            );
            BufferedImage[] idleDown = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.IDLE.baseDown, 11, 16, 0, 0, 6, 6
            );
            BufferedImage[] idleLeft = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.IDLE.baseLeft, 10, 16, 0, 0, 6, 6
            );
            BufferedImage[] idleRight = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.IDLE.baseRight, 10, 16, 0, 0, 6, 6
            );

            BufferedImage[] runUp = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.RUN.baseUp, 11, 17, 0, 0, 6, 6
            );
            BufferedImage[] runDown = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.RUN.baseDown, 11, 17, 0, 0, 6, 6
            );
            BufferedImage[] runLeft = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.RUN.baseLeft, 10, 17, 0, 0, 6, 6
            );
            BufferedImage[] runRight = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.RUN.baseRight, 10, 17, 0, 0, 6, 6
            );

            BufferedImage[] pickupUp = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.PICKUP.baseUp, 11, 15, 0, 0, 3, 3
            );
            BufferedImage[] pickupDown = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.PICKUP.baseDown, 11, 16, 0, 0, 3, 3
            );
            BufferedImage[] pickupLeft = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.PICKUP.baseLeft, 10, 16, 0, 0, 3, 3
            );
            BufferedImage[] pickupRight = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.PICKUP.baseRight, 10, 16, 0, 0, 3, 3
            );

            BufferedImage[] punchUp = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.PUNCH.baseUp, 11, 17, 0, 0, 4, 4
            );
            BufferedImage[] punchDown = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.PUNCH.baseDown, 11, 17, 0, 0, 4, 4
            );
            BufferedImage[] punchLeft = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.PUNCH.baseLeft, 13, 16, 0, 0, 4, 4
            );
            BufferedImage[] punchRight = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.PUNCH.baseRight, 13, 16, 0, 0, 4, 4
            );

            BufferedImage[] deathRight = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.DEATH.baseDeathType1WithoutBlood, 21, 16, 0, 0, 6, 6
            );
            BufferedImage[] deathRightBloody = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.DEATH.baseDeathType3, 21, 16, 0, 0, 7, 7
            );
            BufferedImage[] deathRightHeadshot = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.DEATH.baseDeathType2Headshot, 21, 16, 0, 0, 7, 7
            );
            BufferedImage[] deathLeft = Arrays.stream(deathRight)
                            .map(SpriteManager::flipImageHorizontally)
                            .toArray(BufferedImage[]::new);
            BufferedImage[] deathLeftBloody = Arrays.stream(deathRightBloody)
                            .map(SpriteManager::flipImageHorizontally)
                            .toArray(BufferedImage[]::new);
            BufferedImage[] deathLeftHeadshot = Arrays.stream(deathRightHeadshot)
                            .map(SpriteManager::flipImageHorizontally)
                            .toArray(BufferedImage[]::new);

            animations.put(new AnimationKey(Action.IDLE, Direction.UP), idleUp);
            animations.put(new AnimationKey(Action.IDLE, Direction.DOWN), idleDown);
            animations.put(new AnimationKey(Action.IDLE, Direction.LEFT), idleLeft);
            animations.put(new AnimationKey(Action.IDLE, Direction.RIGHT), idleRight);

            animations.put(new AnimationKey(Action.RUN, Direction.UP), runUp);
            animations.put(new AnimationKey(Action.RUN, Direction.DOWN), runDown);
            animations.put(new AnimationKey(Action.RUN, Direction.LEFT), runLeft);
            animations.put(new AnimationKey(Action.RUN, Direction.RIGHT), runRight);

            animations.put(new AnimationKey(Action.PICKUP, Direction.UP), pickupUp);
            animations.put(new AnimationKey(Action.PICKUP, Direction.DOWN), pickupDown);
            animations.put(new AnimationKey(Action.PICKUP, Direction.LEFT), pickupLeft);
            animations.put(new AnimationKey(Action.PICKUP, Direction.RIGHT), pickupRight);

            animations.put(new AnimationKey(Action.ATTACK, Direction.UP), punchUp);
            animations.put(new AnimationKey(Action.ATTACK, Direction.DOWN), punchDown);
            animations.put(new AnimationKey(Action.ATTACK, Direction.LEFT), punchLeft);
            animations.put(new AnimationKey(Action.ATTACK, Direction.RIGHT), punchRight);

            animations.put(new AnimationKey(Action.DEATH, Direction.LEFT), deathLeft);
            animations.put(new AnimationKey(Action.DEATH, Direction.RIGHT), deathRight);
            animations.put(new AnimationKey(Action.DEATH_BLOODY, Direction.LEFT), deathLeftBloody);
            animations.put(new AnimationKey(Action.DEATH_BLOODY, Direction.RIGHT), deathRightBloody);
            animations.put(new AnimationKey(Action.DEATH_HEADSHOT, Direction.LEFT), deathLeftHeadshot);
            animations.put(new AnimationKey(Action.DEATH_HEADSHOT, Direction.RIGHT), deathRightHeadshot);

            return animations;
        }

        public static Map<AnimationKey, BufferedImage[]> loadPlayerHandsAnimations() {
            Map<AnimationKey, BufferedImage[]> animations = new HashMap<>();

            BufferedImage[] idleUp = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.IDLE.handsUp, 11, 16, 0, 0, 6, 6
            );
            BufferedImage[] idleDown = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.IDLE.handsDown, 13, 16, 0, 0, 6, 6
            );
            BufferedImage[] idleLeft = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.IDLE.handsLeft, 12, 16, 0, 0, 6, 6
            );
            BufferedImage[] idleRight = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.IDLE.handsRight, 12, 16, 0, 0, 6, 6
            );

            BufferedImage[] runUp = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.RUN.handsUp, 13, 17, 0, 0, 6, 6
            );
            BufferedImage[] runDown = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.RUN.handsDown, 13, 17, 0, 0, 6, 6
            );
            BufferedImage[] runLeft = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.RUN.handsLeft, 14, 17, 0, 0, 6, 6
            );
            BufferedImage[] runRight = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.RUN.handsRight, 14, 17, 0, 0, 6, 6
            );

            BufferedImage[] pickupUp = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.PICKUP.handsUp, 11, 15, 0, 0, 3, 3
            );
            BufferedImage[] pickupDown = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.PICKUP.handsDown, 12, 16, 0, 0, 3, 3
            );
            BufferedImage[] pickupRight = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.PICKUP.handsRight, 11, 16, 0, 0, 3, 3
            );
            BufferedImage[] pickupLeft = Arrays.stream(pickupRight)
                            .map(SpriteManager::flipImageHorizontally)
                            .toArray(BufferedImage[]::new);

            BufferedImage[] punchUp = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.PUNCH.handsUp, 12, 17, 0, 0, 4, 4
            );
            BufferedImage[] punchDown = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.PUNCH.handsDown, 12, 10, 0, 0, 4, 4
            );
            BufferedImage[] punchLeft = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.PUNCH.handsLeft, 18, 10, 0, 0, 4, 4
            );
            BufferedImage[] punchRight = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.PUNCH.handsLeft, 18, 10, 0, 0, 4, 4
            );

            BufferedImage[] deathRight = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.DEATH.handsDeathType1WithoutBlood, 21, 16, 0, 0, 6, 6
            );
            BufferedImage[] deathRightBloody = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.DEATH.handsDeathType3, 21, 16, 0, 0, 7, 7
            );
            BufferedImage[] deathRightHeadshot = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.PlayerPath.DEATH.handsDeathType2Headshot, 21, 16, 0, 0, 7, 7
            );
            BufferedImage[] deathLeft = Arrays.stream(deathRight)
                            .map(SpriteManager::flipImageHorizontally)
                            .toArray(BufferedImage[]::new);
            BufferedImage[] deathLeftBloody = Arrays.stream(deathRightBloody)
                    .map(SpriteManager::flipImageHorizontally)
                    .toArray(BufferedImage[]::new);
            BufferedImage[] deathLeftHeadshot = Arrays.stream(deathRightHeadshot)
                    .map(SpriteManager::flipImageHorizontally)
                    .toArray(BufferedImage[]::new);

            animations.put(new AnimationKey(Action.IDLE, Direction.UP), idleUp);
            animations.put(new AnimationKey(Action.IDLE, Direction.DOWN), idleDown);
            animations.put(new AnimationKey(Action.IDLE, Direction.LEFT), idleLeft);
            animations.put(new AnimationKey(Action.IDLE, Direction.RIGHT), idleRight);

            animations.put(new AnimationKey(Action.RUN, Direction.UP), runUp);
            animations.put(new AnimationKey(Action.RUN, Direction.DOWN), runDown);
            animations.put(new AnimationKey(Action.RUN, Direction.LEFT), runLeft);
            animations.put(new AnimationKey(Action.RUN, Direction.RIGHT), runRight);

            animations.put(new AnimationKey(Action.PICKUP, Direction.UP), pickupUp);
            animations.put(new AnimationKey(Action.PICKUP, Direction.DOWN), pickupDown);
            animations.put(new AnimationKey(Action.PICKUP, Direction.LEFT), pickupLeft);
            animations.put(new AnimationKey(Action.PICKUP, Direction.RIGHT), pickupRight);

            animations.put(new AnimationKey(Action.ATTACK, Direction.UP), punchUp);
            animations.put(new AnimationKey(Action.ATTACK, Direction.DOWN), punchDown);
            animations.put(new AnimationKey(Action.ATTACK, Direction.LEFT), punchLeft);
            animations.put(new AnimationKey(Action.ATTACK, Direction.RIGHT), punchRight);

            animations.put(new AnimationKey(Action.DEATH, Direction.LEFT), deathLeft);
            animations.put(new AnimationKey(Action.DEATH, Direction.RIGHT), deathRight);
            animations.put(new AnimationKey(Action.DEATH_BLOODY, Direction.LEFT), deathLeftBloody);
            animations.put(new AnimationKey(Action.DEATH_BLOODY, Direction.RIGHT), deathRightBloody);
            animations.put(new AnimationKey(Action.DEATH_HEADSHOT, Direction.LEFT), deathLeftHeadshot);
            animations.put(new AnimationKey(Action.DEATH_HEADSHOT, Direction.RIGHT), deathRightHeadshot);

            return animations;
        }

        public static Map<AnimationKey, BufferedImage[]> loadLegacyPlayerBodyAnimations() {
            Map<AnimationKey, BufferedImage[]> animations = new HashMap<>();

            BufferedImage[] idleLeftFrames = SpriteManager.loadFramesFromSheet(
                    characterBase,
                    16,
                    16,
                    0,
                    0,
                    3,
                    3
            );

            BufferedImage[] idleRightFrames = SpriteManager.loadFramesFromSheet(
                    characterBase,
                    16,
                    16,
                    1,
                    0,
                    3,
                    3
            );

            BufferedImage[] idleUpFrames = SpriteManager.loadFramesFromSheet(
                    characterBase,
                    16,
                    16,
                    2,
                    0,
                    3,
                    3
            );

            BufferedImage[] idleDownFrames = SpriteManager.loadFramesFromSheet(
                    characterBase,
                    16,
                    16,
                    3,
                    0,
                    3,
                    3
            );

            BufferedImage[] runLeftFrames = SpriteManager.loadFramesFromSheet(
                    characterBase,
                    16,
                    16,
                    4,
                    0,
                    4,
                    4
            );

            BufferedImage[] runRightFrames = SpriteManager.loadFramesFromSheet(
                    characterBase,
                    16,
                    16,
                    5,
                    0,
                    4,
                    4
            );

            BufferedImage[] runUpFrames = SpriteManager.loadFramesFromSheet(
                    characterBase,
                    16,
                    16,
                    6,
                    0,
                    4,
                    4
            );

            BufferedImage[] runDownFrames = SpriteManager.loadFramesFromSheet(
                    characterBase,
                    16,
                    16,
                    7,
                    0,
                    4,
                    4
            );

            animations.put(new AnimationKey(Action.IDLE, Direction.LEFT), idleLeftFrames);
            animations.put(new AnimationKey(Action.IDLE, Direction.RIGHT), idleRightFrames);
            animations.put(new AnimationKey(Action.IDLE, Direction.UP), idleUpFrames);
            animations.put(new AnimationKey(Action.IDLE, Direction.DOWN), idleDownFrames);

            animations.put(new AnimationKey(Action.RUN, Direction.LEFT), runLeftFrames);
            animations.put(new AnimationKey(Action.RUN, Direction.RIGHT), runRightFrames);
            animations.put(new AnimationKey(Action.RUN, Direction.UP), runUpFrames);
            animations.put(new AnimationKey(Action.RUN, Direction.DOWN), runDownFrames);

            return animations;
        }

        public static Map<AnimationKey, BufferedImage[]> loadLegacyPlayerHandsAnimations() {
            Map<AnimationKey, BufferedImage[]> animations = new HashMap<>();

            BufferedImage[] idleLeftFrames = SpriteManager.loadFramesFromSheet(
                    characterHands,
                    16,
                    16,
                    0,
                    0,
                    3,
                    3
            );

            BufferedImage[] idleRightFrames = SpriteManager.loadFramesFromSheet(
                    characterHands,
                    16,
                    16,
                    1,
                    0,
                    3,
                    3
            );

            BufferedImage[] idleUpFrames = SpriteManager.loadFramesFromSheet(
                    characterHands,
                    16,
                    16,
                    2,
                    0,
                    3,
                    3
            );

            BufferedImage[] idleDownFrames = SpriteManager.loadFramesFromSheet(
                    characterHands,
                    16,
                    16,
                    3,
                    0,
                    3,
                    3
            );

            BufferedImage[] runLeftFrames = SpriteManager.loadFramesFromSheet(
                    characterHands,
                    16,
                    16,
                    4,
                    0,
                    4,
                    4
            );

            BufferedImage[] runRightFrames = SpriteManager.loadFramesFromSheet(
                    characterHands,
                    16,
                    16,
                    5,
                    0,
                    4,
                    4
            );

            BufferedImage[] runUpFrames = SpriteManager.loadFramesFromSheet(
                    characterHands,
                    16,
                    16,
                    6,
                    0,
                    4,
                    4
            );

            BufferedImage[] runDownFrames = SpriteManager.loadFramesFromSheet(
                    characterHands,
                    16,
                    16,
                    7,
                    0,
                    4,
                    4
            );

            BufferedImage[] shotgunHoldFrames = new BufferedImage[1];
            shotgunHoldFrames[0] = SpriteManager.extractFromSheet(shotgunHands, 144, 16, 32, 16);

            animations.put(new AnimationKey(Action.IDLE, Direction.LEFT), idleLeftFrames);
            animations.put(new AnimationKey(Action.IDLE, Direction.RIGHT), idleRightFrames);
            animations.put(new AnimationKey(Action.IDLE, Direction.UP), idleUpFrames);
            animations.put(new AnimationKey(Action.IDLE, Direction.DOWN), idleDownFrames);

            animations.put(new AnimationKey(Action.RUN, Direction.LEFT), runLeftFrames);
            animations.put(new AnimationKey(Action.RUN, Direction.RIGHT), runRightFrames);
            animations.put(new AnimationKey(Action.RUN, Direction.UP), runUpFrames);
            animations.put(new AnimationKey(Action.RUN, Direction.DOWN), runDownFrames);

            return animations;
        }
    }

    public static class WeaponsAnimationsLoader {

        public static Map<AnimationKey, BufferedImage[]> loadBatAnimations() {
            Map<AnimationKey, BufferedImage[]> animations = new HashMap<>();

            BufferedImage[] upIdleAndRun = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.WeaponPath.BAT.upIdleAndRun, 16, 14, 0, 0, 6, 6
            );
            BufferedImage[] upAttack = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.WeaponPath.BAT.upAttack, 20, 25, 0, 0, 4, 4
            );

            BufferedImage[] downIdleAndRun = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.WeaponPath.BAT.downIdleAndRun, 17, 11, 0, 0, 6, 6
            );
            BufferedImage[] downAttack = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.WeaponPath.BAT.downAttack, 20, 25, 0, 0, 4, 4
            );

            BufferedImage[] leftIdleAndRun = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.WeaponPath.BAT.leftIdleAndRun, 16, 13, 0, 0, 6, 6
            );
            BufferedImage[] leftAttack = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.WeaponPath.BAT.leftAttack, 28, 16, 0, 0, 4, 4
            );
            BufferedImage[] leftDeath = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.WeaponPath.BAT.leftDeath, 18, 13, 0, 0, 6, 6
            );

            BufferedImage[] rightIdleAndRun = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.WeaponPath.BAT.rightIdleAndRun, 16, 13, 0, 0, 6, 6
            );
            BufferedImage[] rightAttack = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.WeaponPath.BAT.rightAttack, 28, 16, 0, 0, 4, 4
            );
            BufferedImage[] rightDeath = SpriteManager.loadFramesFromSheet(
                    AnimationsPaths.WeaponPath.BAT.rightDeath, 18, 13, 0, 0, 6, 6
            );

            return animations;
        }
    }
}
