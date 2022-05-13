package com.ryorama.terrariamod.items.api;

import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;

public class AbstractDiscItem extends MusicDiscItem {

    public AbstractDiscItem(int comparatorOutput, SoundEvent sound, Settings settings) {
        super(comparatorOutput, sound, settings);
    }
}
