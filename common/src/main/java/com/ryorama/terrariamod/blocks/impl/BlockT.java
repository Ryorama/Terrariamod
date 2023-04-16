package com.ryorama.terrariamod.blocks.impl;

import com.ryorama.terrariamod.items.impl.ItemT;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.Random;

public class BlockT extends Block {

    public int buy, sell;
    public float difficulty = 1;
    public boolean pick, axe, hammer;
    public boolean material;
    public boolean harvest = true;
    public boolean consumable;

    public int potionSickness;

    public Random random = new Random();

    public int health, mana;

    public String tooltip = "";

    public String name;
    public String shape = "";

    protected ArrayList<String> allowed = new ArrayList<String>();

    public BlockT(Settings properties, float hardness, float difficulty) {
        super(properties.hardness(hardness * 0.03f));
        this.difficulty = difficulty;
    }

    public BlockT addAllowed(String... block) {
        for (String b : block)
            this.allowed.add(b);
        return this;
    }

    public float getMiningSpeed(ItemT item) {
        if (pick && item.pick >= difficulty) {
            return item.pick / difficulty;
        }

        if (axe && item.axe >= difficulty) {
            return item.axe / difficulty;
        }

        if (hammer && item.hammer >= difficulty) {
            return item.hammer / difficulty;
        }

        return -1;
    }

    public BlockT setPick(boolean pick) {
        this.pick = pick;
        return this;
    }

    public BlockT setAxe(boolean axe) {
        this.axe = axe;
        return this;
    }
}
