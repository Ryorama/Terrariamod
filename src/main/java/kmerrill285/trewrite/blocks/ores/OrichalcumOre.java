package kmerrill285.trewrite.blocks.ores;

import kmerrill285.trewrite.blocks.BlockT;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.block.Block.Properties;

public class OrichalcumOre extends BlockT {
   public OrichalcumOre(Properties properties) {
      super(properties, BlocksT.ORE_HARDNESS, 20.0F, "orichalcum_ore");
      this.pick = true;
      this.difficulty = 110;
      this.setLocation("orichalcum_ore");
      this.sell = 50;
      this.buy = Conversions.sellToBuy(this.sell);
      this.material = true;
   }
}
