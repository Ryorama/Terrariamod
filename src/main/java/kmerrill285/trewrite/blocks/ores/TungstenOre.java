package kmerrill285.trewrite.blocks.ores;

import kmerrill285.trewrite.blocks.BlockT;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.block.Block.Properties;

public class TungstenOre extends BlockT {
   public TungstenOre(Properties properties) {
      super(properties, BlocksT.ORE_HARDNESS, 20.0F, "tungsten_ore");
      this.pick = true;
      this.setLocation("tungsten_ore");
      this.sell = 300;
      this.buy = Conversions.sellToBuy(this.sell);
      this.material = true;
   }
}
