package com.ryorama.terrariamod.core.client.inventory;

import com.ryorama.terrariamod.items.ItemModifier;
import com.ryorama.terrariamod.items.ItemStackT;
import com.ryorama.terrariamod.items.ItemsT;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class InventoryTerraria {
    public static int MAIN = 0, HOTBAR = 1, TRASH = 2, ARMOR = 3, ACCESSORY = 4, VANITY_ARMOR = 3, VANITY_ACCESSORY = 5, EQUIPMENT = 7, COINS = 8, AMMO = 9,
            PIGGY_BANK = 10, SAFE = 11, ACCESSORY_DYE = 5, ARMOR_DYE = 3, EQUIPMENT_DYE = 14;

    public static int MAIN_SLOTS = 40, HOTBAR_SLOTS = 10, ARMOR_SLOTS = 3, ACCESSORY_SLOTS = 5, EQUIPMENT_SLOTS = 5;
    //equipment: Pet, Light Pet, Minecart, Mount, Hook

    private int[] TOP_ARMOR = {8, 8}, TOP_ARMOR_VANITY = {80, 8}, TRASHCAN = {8, 66}, INVENTORY_TOPLEFT = {8, 84}, HOTBAR_LEFT = {8, 142},
            ACCESSORY_TOPLEFT = {197, 71}, CRAFTING_TOPLEFT = {120, 8}, ICONS = {69, 168};

    public int hotbarSelected = 0;

    public InventorySlot holdingSlot;

    public boolean loaded = false;

    public InventorySlot[] hotbar = new InventorySlot[HOTBAR_SLOTS];
    public InventorySlot[] main = new InventorySlot[MAIN_SLOTS];
    public InventorySlot[] armor = new InventorySlot[ARMOR_SLOTS];
    public InventorySlot trash = new InventorySlot(InventorySlot.ItemType.ANY, 170, 99, 8, 0);
    public InventorySlot[] armorVanity = new InventorySlot[VANITY_ARMOR];
    public InventorySlot[] armorDyes = new InventorySlot[ARMOR_DYE];
    public InventorySlot[] accessory = new InventorySlot[ACCESSORY_SLOTS];
    public InventorySlot[] accessoryVanity = new InventorySlot[VANITY_ACCESSORY];
    public InventorySlot[] accessoryDyes = new InventorySlot[ACCESSORY_DYE];

    public boolean open = false;

    public ItemStack[] savedHotbar = new ItemStack[9];
    public ItemStack savedOffhand;

    public PlayerEntity player;

    public boolean canSave = true;

    public boolean isEmpty() {
        for (InventorySlot slot : accessoryDyes) {
            if (slot != null)
                if (slot.stack != null)
                    if (slot.stack.item != null)
                    {
                        return false;
                    }
        }
        for (InventorySlot slot : accessoryVanity) {
            if (slot != null)
                if (slot.stack != null)
                    if (slot.stack.item != null)
                    {
                        return false;
                    }
        }
        for (InventorySlot slot : accessory) {
            if (slot != null)
                if (slot.stack != null)
                    if (slot.stack.item != null)
                    {
                        return false;
                    }
        }
        for (InventorySlot slot : armorDyes) {
            if (slot != null)
                if (slot.stack != null)
                    if (slot.stack.item != null)
                    {
                        return false;
                    }
        }
        for (InventorySlot slot : armorVanity) {
            if (slot != null)
                if (slot.stack != null)
                    if (slot.stack.item != null)
                    {
                        return false;
                    }
        }
        if (trash != null)
            if (trash.stack != null)
                if (trash.stack.item != null)
                {
                    return false;
                }

        for (InventorySlot slot : armor) {
            if (slot != null)
                if (slot.stack != null)
                    if (slot.stack.item != null)
                    {
                        return false;
                    }
        }
        for (InventorySlot slot : hotbar) {
            if (slot != null)
                if (slot.stack != null)
                    if (slot.stack.item != null)
                    {
                        return false;
                    }
        }
        for (InventorySlot slot : main) {
            if (slot != null)
                if (slot.stack != null)
                    if (slot.stack.item != null)
                    {
                        return false;
                    }
        }
        return true;
    }

    public InventoryTerraria() {

        float scaledWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();

        for (int i = 0; i < HOTBAR_SLOTS; i++) {
            hotbar[i] = new InventorySlot(InventorySlot.ItemType.ANY, HOTBAR_LEFT[0] + i * 16 + i * 2, HOTBAR_LEFT[1], 1, i);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                main[i + j * 10] = new InventorySlot(InventorySlot.ItemType.ANY, INVENTORY_TOPLEFT[0] + i * 16 + i * 2, INVENTORY_TOPLEFT[1] + j * 16 + j * 2, 0, i + j * 10);

            }
        }

        armor[0] = new InventorySlot(InventorySlot.ItemType.HEAD, (int)(scaledWidth - 17), 120, 2, 0);
        armor[1] = new InventorySlot(InventorySlot.ItemType.CHESTPLATE, (int)(scaledWidth - 17), 137, 2, 1);
        armor[2] = new InventorySlot(InventorySlot.ItemType.LEGGINGS, (int)(scaledWidth - 17), 154, 2, 2);

        armorVanity[0] = new InventorySlot(InventorySlot.ItemType.HEAD, TOP_ARMOR_VANITY[0], TOP_ARMOR_VANITY[1], 3, 0);
        armorVanity[1] = new InventorySlot(InventorySlot.ItemType.CHESTPLATE, TOP_ARMOR_VANITY[0], TOP_ARMOR_VANITY[1] + 16 + 2, 3, 1);
        armorVanity[2] = new InventorySlot(InventorySlot.ItemType.LEGGINGS, TOP_ARMOR_VANITY[0], TOP_ARMOR_VANITY[1] + 16 * 2 + 4, 3, 2);

        for (int i = 0; i < ARMOR_DYE; i++) {
            armorDyes[i] = new InventorySlot(InventorySlot.ItemType.DYE, TOP_ARMOR_VANITY[0] + 16 + 2, TOP_ARMOR_VANITY[1] + i * 16 + i * 2, 4, i);
        }

        for (int i = 0; i < ACCESSORY_SLOTS; i++) {
            accessory[i] = new InventorySlot(InventorySlot.ItemType.ACCESSORY, (int)(scaledWidth - 17), 154 + i * 17, 5, i);
        }

        for (int i = 0; i < VANITY_ACCESSORY; i++) {
            accessoryVanity[i] = new InventorySlot(InventorySlot.ItemType.ACCESSORY, ACCESSORY_TOPLEFT[0] + 16 + 2, ACCESSORY_TOPLEFT[1] + i * 16 + i * 2, 6, i);
        }

        for (int i = 0; i < ACCESSORY_DYE; i++) {
            accessoryDyes[i] = new InventorySlot(InventorySlot.ItemType.DYE, ACCESSORY_TOPLEFT[0], ACCESSORY_TOPLEFT[1] + i * 16 + i * 2, 7, i);
        }

        trash.isTrashSlot = true;

//		hotbar[0].stack = new ItemStackT(ItemsT.DIRT_BLOCK, 999);
    }

    public InventoryTerraria(boolean cansave) {
        this();
        this.canSave = cansave;
    }

    public void save(String player, String world) {


        //System.out.println("SAVING INVENTORY FOR ["+player+"] IN WORLD ["+world+"]");
        File file = new File("saves/"+world+"/playerdata/"+player+".inventory");
        File path = new File("saves/"+world+"/playerdata/");
        if (!path.exists())
        {
            path.mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
//

        //System.out.println(file);
        String savedata = getDataForSave();

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(savedata);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void load(String player, String world) {
        //System.out.println("LOADING INVENTORY FOR ["+player+"] IN WORLD ["+world+"]");
        File file = new File("saves/"+world+"/playerdata/"+player+".inventory");

        if (!file.exists()) {
            System.out.println("FILE DOES NOT EXIST, MAKING NEW INVENTORY!");
            giveTools();
            save(player, world);
        }
        String inv = "";
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                inv += scanner.nextLine() + "\n";
            }
            scanner.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //System.out.println(inv);
        loadFromString(inv);

        //System.out.println("FINISHED LOADING INVENTORY FOR PLAYER");
    }

    public void giveTools() {
        this.hotbar[0].stack = new ItemStackT(ItemsT.COPPER_SHORTSWORD, 1, ItemModifier.getRandomModifier(ItemsT.COPPER_SHORTSWORD));
        this.hotbar[1].stack = new ItemStackT(ItemsT.COPPER_AXE, 1, ItemModifier.getRandomModifier(ItemsT.COPPER_AXE));
        this.hotbar[2].stack = new ItemStackT(ItemsT.COPPER_PICKAXE, 1, ItemModifier.getRandomModifier(ItemsT.COPPER_PICKAXE));
    }
    public void loadFromString(String inventory) {
        //main, hotbar, armor, armor vanity, armor dyes, accessory, accessory vanity, accesory dyes, trash

        String[] lines = inventory.split("\n");
//		System.out.println("LOAD FROM STRING: " + lines.length + " LINES TO LOAD");
//		for (int i = 0; i < lines.length; i++) {
//			System.out.println(lines[i]);
//		}
        int i = 0;
        int j = 0;
        for (String line : lines) {
            String[] data = line.split(",");

            if (j == 0) {
                if (i < MAIN_SLOTS) {
                    System.out.println("LOADING MAIN SLOT " + i);
                    if (data[0].equals("null") == false || line.equals("null") == false) {
                        String item = data[0];
                        int stack = Integer.parseInt(data[1]);
                        int modifier = Integer.parseInt(data[2]);
                        main[Integer.parseInt(data[3])].stack = new ItemStackT(ItemsT.getItemFromString(item), stack, ItemModifier.getModifier(modifier));
                        main[Integer.parseInt(data[3])].isFavorite = Boolean.parseBoolean(data[4]);
                        //System.out.println("SERVER: MAIN " + i + " SET TO " + item);
                    }
                    i++;
                } else {
                    i = 0;
                    j++;
                }
            }
            if (j == 1) {
                if (i < HOTBAR_SLOTS) {
                    //System.out.println("LOADING HOTBAR SLOT " + i);
                    if (data[0].equals("null") == false || line.equals("null") == false) {
                        String item = data[0];
                        int stack = Integer.parseInt(data[1]);
                        int modifier = Integer.parseInt(data[2]);
                        hotbar[Integer.parseInt(data[3])].stack = new ItemStackT(ItemsT.getItemFromString(item), stack, ItemModifier.getModifier(modifier));
                        hotbar[Integer.parseInt(data[3])].isFavorite = Boolean.parseBoolean(data[4]);
                        //System.out.println("SERVER: HOTBAR " + i + " SET TO " + item);
                    }
                    i++;
                } else {
                    i = 0;
                    j++;
                }
            }
            if (j == 2) {
                if (i < ARMOR_SLOTS) {
                    if (data[0].equals("null") == false || line.equals("null") == false) {
                        String item = data[0];
                        int stack = Integer.parseInt(data[1]);
                        int modifier = Integer.parseInt(data[2]);
                        armor[Integer.parseInt(data[3])].stack = new ItemStackT(ItemsT.getItemFromString(item), stack, ItemModifier.getModifier(modifier));
                        //System.out.println("SERVER: ARMOR " + i + " SET TO " + item);
                    }
                    i++;
                } else {
                    i = 0;
                    j++;
                }
            }
            if (j == 3) {
                if (i < ARMOR_SLOTS) {
                    if (data[0].equals("null") == false || line.equals("null") == false) {
                        String item = data[0];
                        int stack = Integer.parseInt(data[1]);
                        int modifier = Integer.parseInt(data[2]);
                        armorVanity[Integer.parseInt(data[3])].stack = new ItemStackT(ItemsT.getItemFromString(item), stack, ItemModifier.getModifier(modifier));
                        //System.out.println("SERVER: ARMOR VANITY " + i + " SET TO " + item);
                    }
                    i++;
                } else {
                    i = 0;
                    j++;
                }
            }
            if (j == 4) {
                if (i < ARMOR_SLOTS) {
                    if (data[0].equals("null") == false || line.equals("null") == false) {
                        String item = data[0];
                        int stack = Integer.parseInt(data[1]);
                        int modifier = Integer.parseInt(data[2]);
                        armorDyes[Integer.parseInt(data[3])].stack = new ItemStackT(ItemsT.getItemFromString(item), stack, ItemModifier.getModifier(modifier));
                        //System.out.println("SERVER: ARMOR DYES " + i + " SET TO " + item);
                    }
                    i++;
                } else {
                    i = 0;
                    j++;
                }
            }
            if (j == 5) {
                if (i < ACCESSORY_SLOTS) {
                    if (data[0].equals("null") == false || line.equals("null") == false) {
                        String item = data[0];
                        int stack = Integer.parseInt(data[1]);
                        int modifier = Integer.parseInt(data[2]);
                        accessory[Integer.parseInt(data[3])].stack = new ItemStackT(ItemsT.getItemFromString(item), stack, ItemModifier.getModifier(modifier));
                        //System.out.println("SERVER: ACCESSORY " + i + " SET TO " + item);
                    }
                    i++;
                } else {
                    i = 0;
                    j++;
                }
            }
            if (j == 6) {
                if (i < ACCESSORY_SLOTS) {
                    if (data[0].equals("null") == false || line.equals("null") == false) {
                        String item = data[0];
                        int stack = Integer.parseInt(data[1]);
                        int modifier = Integer.parseInt(data[2]);
                        accessoryVanity[Integer.parseInt(data[3])].stack = new ItemStackT(ItemsT.getItemFromString(item), stack, ItemModifier.getModifier(modifier));
                        //System.out.println("SERVER: ACCESSORY VANITY " + i + " SET TO " + item);
                    }
                    i++;
                } else {
                    i = 0;
                    j++;
                }
            }
            if (j == 7) {
                if (i < ACCESSORY_SLOTS) {
                    if (data[0].equals("null") == false || line.equals("null") == false) {
                        String item = data[0];
                        int stack = Integer.parseInt(data[1]);
                        int modifier = Integer.parseInt(data[2]);
                        accessoryDyes[Integer.parseInt(data[3])].stack = new ItemStackT(ItemsT.getItemFromString(item), stack, ItemModifier.getModifier(modifier));
                        //System.out.println("SERVER: ACCESSORY DYES " + i + " SET TO " + item);
                    }
                    i++;
                } else {
                    i = 0;
                    j++;
                }
            }
            if (j == 8) {
                if (data[0].equals("null") == false || line.equals("null") == false) {
                    String item = data[0];
                    int stack = Integer.parseInt(data[1]);
                    int modifier = Integer.parseInt(data[2]);
                    trash.stack = new ItemStackT(ItemsT.getItemFromString(item), stack, ItemModifier.getModifier(modifier));
                    //System.out.println("SERVER: TRASH SLOT SET TO " + item);
                }
                i = 0;
                j++;

            }

        }

        //System.out.println("LOADING FINISHED");
    }

    public String getDataForSave()
    {
        String savedata = "";

        for (int i = 0; i < main.length; i++) {
            if (main[i].stack == null)
                savedata += "null\n";
            else {
                savedata += ItemsT.getStringForItem(main[i].stack.item) + "," + main[i].stack.size + "," + main[i].stack.modifier + "," + i + "," + main[i].isFavorite + "\n";
                System.out.println(i);
            }
        }

        for (int i = 0; i < hotbar.length; i++) {
            if (hotbar[i].stack == null)
                savedata += "null\n";
            else
                savedata += ItemsT.getStringForItem(hotbar[i].stack.item) + "," + hotbar[i].stack.size + "," + hotbar[i].stack.modifier + "," + i + "," + hotbar[i].isFavorite + "\n";
        }

        for (int i = 0; i < armor.length; i++) {
            if (armor[i].stack == null)
                savedata += "null\n";
            else
                savedata += ItemsT.getStringForItem(armor[i].stack.item) + "," + armor[i].stack.size + "," + armor[i].stack.modifier + "," + i +  "\n";
        }

        for (int i = 0; i < armorVanity.length; i++) {
            if (armorVanity[i].stack == null)
                savedata += "null\n";
            else
                savedata += ItemsT.getStringForItem(armorVanity[i].stack.item) + "," + armorVanity[i].stack.size + "," + armorVanity[i].stack.modifier + "," + i +  "\n";
        }

        for (int i = 0; i < armorDyes.length; i++) {
            if (armorDyes[i].stack == null)
                savedata += "null\n";
            else
                savedata += ItemsT.getStringForItem(armorDyes[i].stack.item) + "," + armorDyes[i].stack.size + "," + armorDyes[i].stack.modifier + "," + i +  "\n";
        }

        for (int i = 0; i < accessory.length; i++) {
            if (accessory[i].stack == null)
                savedata += "null\n";
            else
                savedata += ItemsT.getStringForItem(accessory[i].stack.item) + "," + accessory[i].stack.size + "," + accessory[i].stack.modifier + "," + i +  "\n";
        }

        for (int i = 0; i < accessoryVanity.length; i++) {
            if (accessoryVanity[i].stack == null)
                savedata += "null\n";
            else
                savedata += ItemsT.getStringForItem(accessoryVanity[i].stack.item) + "," + accessoryVanity[i].stack.size + "," + accessoryVanity[i].stack.modifier + "," + i +  "\n";
        }

        for (int i = 0; i < accessoryDyes.length; i++) {
            if (accessoryDyes[i].stack == null)
                savedata += "null\n";
            else
                savedata += ItemsT.getStringForItem(accessoryDyes[i].stack.item) + "," + accessoryDyes[i].stack.size + "," + accessoryDyes[i].stack.modifier + "," + i +  "\n";
        }
        if (trash.stack == null)
            savedata += "null\n";
        else
            savedata += ItemsT.getStringForItem(trash.stack.item) + "," + trash.stack.size + "," + trash.stack.modifier +  "\n";
        return savedata;
    }
}
