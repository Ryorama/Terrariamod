package kmerrill285.trewrite.core.client;

import kmerrill285.trewrite.Trewrite;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import kmerrill285.trewrite.events.ScoreboardEvents;
import kmerrill285.trewrite.items.Armor;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Resource;

@OnlyIn(Dist.CLIENT)
public class TerrariaUIManager {

    public static ResourceLocation health_icon = new ResourceLocation(Trewrite.modid, "textures/ui/heart.png");
    public static ResourceLocation mana = new ResourceLocation(Trewrite.modid, "textures/ui/mana.png");
    public static ResourceLocation shield = new ResourceLocation(Trewrite.modid, "textures/ui/shield.png");

    public static PlayerEntity player;

    public static void renderTerrariaHealth() {

            float scaledWidth = Minecraft.getInstance().mainWindow.getScaledWidth();

            if (Minecraft.getInstance().player != null) {
                player = Minecraft.getInstance().player;
            }

            if (player != null) {
                for (int h = 0; h < player.getHealth() + 20; h++) {
                    Minecraft.getInstance().ingameGUI.drawString(Minecraft.getInstance().fontRenderer, "Life: " + (int)player.getHealth() + "/" + (int)player.getMaxHealth(), (int) (scaledWidth - 113), 2, 0xffffff);

                    if (h % 20 == 0) {
                        if (h < 200) {
                            UIRenderer.instance.renderOverlay(health_icon, 50, 8, 8, scaledWidth - 125 + h / 2, 10, -90);
                        } else if (h > 200) {
                            UIRenderer.instance.renderOverlay(health_icon, 50, 8, 8, scaledWidth - 125 + h / 2 - 110, 20, -90);
                        }
                    }
                }
            }
    }

    public static void renderTerrariaDefense() {
        float scaledWidth = Minecraft.getInstance().mainWindow.getScaledWidth();

        PlayerEntity player = Minecraft.getInstance().player;

        if (player != null) {

            int armor = player.getTotalArmorValue();

            int ironskin = ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.IRONSKIN).getScorePoints();
            if (ironskin > 0) {
                armor += 8;
            }

            int weak = ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.WEAK).getScorePoints();
            if (weak > 0) {
                armor -= 8;
            }

            InventoryTerraria inventory = ContainerTerrariaInventory.inventory;
            if (inventory != null) {

                for (int i = 0; i < 3; i++) {
                    if (inventory.armor[i].stack != null) {
                        if (inventory.armor[i].stack.item instanceof Armor) {
                            Armor a = (Armor)inventory.armor[i].stack.item;
                            armor += a.getDefense(inventory.armor);
                        }
                    }
                }
            }

            UIRenderer.instance.renderOverlay(shield, 50, 16, 16, (int)(scaledWidth - 63), 230, -90);

            if ((int)player.getAttribute(SharedMonsterAttributes.ARMOR).getValue() >= 10) {
                Minecraft.getInstance().ingameGUI.drawString(Minecraft.getInstance().fontRenderer, Integer.toString(armor), (int)(scaledWidth - 54), 233, 0xffffff);
            } else {
                Minecraft.getInstance().ingameGUI.drawString(Minecraft.getInstance().fontRenderer, Integer.toString(armor), (int)(scaledWidth - 58), 233,0xffffff);
            }
        }
    }

    public static void renderTerrariaMana() {

            float scaledWidth = Minecraft.getInstance().mainWindow.getScaledWidth();

            if (Minecraft.getInstance().player != null) {
                player = Minecraft.getInstance().player;
            }

            if (player != null) {
                Minecraft.getInstance().ingameGUI.drawString(Minecraft.getInstance().fontRenderer, "Mana", (int) (scaledWidth - 25), 3, 0xffffff);

                for (int i = 0; i <= ScoreboardEvents.getMana(player) - 20; i++) {
                    if (i % 20 == 0) {
                        UIRenderer.instance.renderOverlay(mana, 50, 10, 10, scaledWidth - 18, 10 + i / 2, -90);
                    }
                }
            }
    }

}
