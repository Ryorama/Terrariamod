package kmerrill285.trewrite.blocks.ores;

import kmerrill285.trewrite.blocks.BlockT;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.block.Block.Properties;

public class AdamantiteOre extends BlockT {
   public AdamantiteOre(Properties properties) {
      super(properties, BlocksT.ORE_HARDNESS, 20.0F, "adamantite_ore");
      this.pick = true;
      this.setLocation("adamantite_ore");
      this.sell = 50;
      this.buy = Conversions.sellToBuy(this.sell);
      this.material = true;
   }
}
