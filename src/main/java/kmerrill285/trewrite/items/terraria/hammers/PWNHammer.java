package kmerrill285.trewrite.items.terraria.hammers;

import java.util.List;
import kmerrill285.trewrite.items.Hammer;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class PWNHammer extends Hammer {
   public PWNHammer() {
      this.hammer = 80.0F;
      this.damage = 26;
      this.knockback = 7.5F;
      this.useTime = 27;
      this.speed = 23;
      this.sellPrice = 80;
      this.buyPrice = Conversions.sellToBuy(this.sellPrice);
      this.setLocation("pwnhammer");
      this.range = -1;
	  this.setLightValue(13);
   }

   public void addInformation(ItemStack stack, World worldIn, List tooltip, ITooltipFlag flagIn) {
      super.addInformation(stack, worldIn, tooltip, flagIn);
      tooltip.add(new StringTextComponent("Bonus: -1 range"));
   }
}
