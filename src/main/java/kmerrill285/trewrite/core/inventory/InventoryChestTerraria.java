package kmerrill285.trewrite.core.inventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import kmerrill285.trewrite.core.inventory.InventorySlot.ItemType;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class InventoryChestTerraria {
	public static int MAIN = 0, HOTBAR = 1, TRASH = 2, CHEST = 3;
	
	public static int MAIN_SLOTS = 30, HOTBAR_SLOTS = 10;
	//equipment: Pet, Light Pet, Minecart, Mount, Hook
	
	public int hotbarSelected = 0;
	
	public InventorySlot holdingSlot;
	
	public InventorySlot[] hotbar = new InventorySlot[HOTBAR_SLOTS];
	public InventorySlot[] main = new InventorySlot[MAIN_SLOTS];
	public InventorySlot[] chest = new InventorySlot[MAIN_SLOTS];

	public InventorySlot trash = new InventorySlot(ItemType.ANY, 7 + 1, 65 + 1, 2, 0);
	public boolean open = false;
	
	public ItemStack[] savedHotbar = new ItemStack[9];
	
	public PlayerEntity player;
	
	public boolean canSave = true;
	
	public InventoryChestTerraria() {
		
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 3; j++)
			main[i + j * 10] = new InventorySlot(ItemType.ANY, 7 + i * 16 + i * 2 + 1, 83 + j * 16 + j * 2 + 1, 0, i + j * 10);
		}
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 3; j++)
			chest[i + j * 10] = new InventorySlot(ItemType.ANY, 7 + i * 16 + i * 2 + 1, 7 + j * 16 + j * 2 + 1, 3, i + j * 10);
		}
		
		for (int i = 0; i < 10; i++) {
			hotbar[i] = new InventorySlot(ItemType.ANY, 7 + i * 16 + i * 2 + 1, 141 + 1, 1, i);
		}
		
		trash.isTrashSlot = true;
		
//		hotbar[0].stack = new ItemStackT(ItemsT.DIRT_BLOCK, 999);
	}
	
	public InventoryChestTerraria(boolean cansave) {
		this();
		this.canSave = cansave;
	}

	public void save(String player, String world) {
		File file = new File("saves/"+world+"/chests/"+player+".inventory");
		File path = new File("saves/"+world+"/chests/");
		if (!path.exists())
		{
			path.mkdirs();
		}
//		try {
//			file.createNewFile();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		
		
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
		File file = new File("saves/"+world+"/chests/"+player+".inventory");
		if (!file.exists()) {
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
		loadFromString(inv);
	}
	
	public void loadFromString(String inventory) {
		//main, hotbar, armor, armor vanity, armor dyes, accessory, accessory vanity, accesory dyes, trash
		
		String[] lines = inventory.split("\n");
		
		int i = 0;
		int j = 0;
		for (String line : lines) {
			String[] data = line.split(",");
			if (j == 0) {
				if (i < MAIN_SLOTS) {
					if (data[0].equals("null") == false || line.equals("null") == false) {
						String item = data[0];
						int stack = Integer.parseInt(data[1]);
						int modifier = Integer.parseInt(data[2]);
						main[i].stack = new ItemStackT(ItemsT.getItemFromString(item), stack, ItemModifier.getModifier(modifier));
					}
					i++;
				} else {
					i = 0;
					j++;
				}
			}
			if (j == 1) {
				if (i < HOTBAR_SLOTS) {
					if (data[0].equals("null") == false || line.equals("null") == false) {
						String item = data[0];
						int stack = Integer.parseInt(data[1]);
						int modifier = Integer.parseInt(data[2]);
						hotbar[i].stack = new ItemStackT(ItemsT.getItemFromString(item), stack, ItemModifier.getModifier(modifier));
					}
					i++;
				} else {
					i = 0;
					j++;
				}
			}
			if (j == 2) {
				if (data[0].equals("null") == false || line.equals("null") == false) {
					String item = data[0];
					int stack = Integer.parseInt(data[1]);
					int modifier = Integer.parseInt(data[2]);
					trash.stack = new ItemStackT(ItemsT.getItemFromString(item), stack, ItemModifier.getModifier(modifier));
				}
				i = 0;
				j++;
			
			}
			if (j == 3) {
				if (i < MAIN_SLOTS) {
					if (data[0].equals("null") == false || line.equals("null") == false) {
						String item = data[0];
						int stack = Integer.parseInt(data[1]);
						int modifier = Integer.parseInt(data[2]);
						chest[i].stack = new ItemStackT(ItemsT.getItemFromString(item), stack, ItemModifier.getModifier(modifier));
					}
					i++;
				} else {
					i = 0;
					j++;
				}
			}
			
		}
		
	}
	
	public String getDataForSave() 
	{
		String savedata = "";
		
		for (int i = 0; i < main.length; i++) {
			if (main[i].stack == null)
				savedata += "null\n";
			else
			savedata += ItemsT.getStringForItem(main[i].stack.item) + "," + main[i].stack.size + "," + main[i].stack.modifier +  "\n";
		}
		
		for (int i = 0; i < hotbar.length; i++) {
			if (hotbar[i].stack == null)
				savedata += "null\n";
			else
			savedata += ItemsT.getStringForItem(hotbar[i].stack.item) + "," + hotbar[i].stack.size + "," + hotbar[i].stack.modifier +  "\n";
		}
		for (int i = 0; i < chest.length; i++) {
			if (chest[i].stack == null)
				savedata += "null\n";
			else
			savedata += ItemsT.getStringForItem(chest[i].stack.item) + "," + chest[i].stack.size + "," + chest[i].stack.modifier +  "\n";
		}
		if (trash.stack == null)
			savedata += "null\n";
		else
		savedata += ItemsT.getStringForItem(trash.stack.item) + "," + trash.stack.size + "," + trash.stack.modifier +  "\n";
		
		
		
		return savedata;
	}
}
