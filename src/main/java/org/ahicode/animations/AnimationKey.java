package org.ahicode.animations;

import org.ahicode.entities.enums.Direction;
import org.ahicode.entities.enums.Action;

import java.util.Objects;

public class AnimationKey {
    private final Action action;
    private final Direction direction;

    public AnimationKey(Action action, Direction direction) {
        this.action = action;
        this.direction = direction;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        AnimationKey that = (AnimationKey) object;
        return action == that.action && direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(action, direction);
    }
}
