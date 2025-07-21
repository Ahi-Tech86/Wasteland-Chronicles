package org.ahicode.entity.enums;

import lombok.Getter;

@Getter
public enum Action {
    IDLE,
    RUN,
    PICKUP,
    ATTACK(AttackType.FISTS),
    DEATH,
    DEATH_HEADSHOT,
    DEATH_BLOODY;

    public enum AttackType {
        FISTS, BAT, PISTOL, SHOTGUN, RIFLE
    }

    private final AttackType attackType;

    Action() {
        this.attackType = null;
    }

    Action(AttackType attackType) {
        this.attackType = attackType;
    }
}