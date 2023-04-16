package com.ryorama.terrariamod.items.impl.enums;

public enum EnumModifierType {
    NONE(0b0), ACCESSORY(0b1), UNIVERSAL(0b10), COMMON(0b110), RANGED(0b1110), MELEE(0b10110), MAGIC(0b100110);

    private int type = 0;
    EnumModifierType(int type) {
        this.type = type;
    }

    public boolean isCompatible(EnumModifierType B) {
        return this == ACCESSORY && B == ACCESSORY
                || this == UNIVERSAL && B == UNIVERSAL
                || this == COMMON && B == UNIVERSAL
                || this == COMMON && B == COMMON
                || this == RANGED && B == RANGED
                || this == RANGED && B == COMMON
                || this == RANGED && B == UNIVERSAL
                || this == MELEE && B == MELEE
                || this == MELEE && B == COMMON
                || this == MELEE && B == UNIVERSAL
                || this == MAGIC && B == MAGIC
                || this == MAGIC && B == COMMON
                || this == MAGIC && B == UNIVERSAL;
    }
}
