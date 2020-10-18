package kmerrill285.trewrite.blocks.ores;

import java.util.Random;
import kmerrill285.trewrite.blocks.BlockT;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.util.Conversions;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class PreOrichalcum1 extends BlockT {
   public PreOrichalcum1(Properties properties) {
      super(properties, BlocksT.ORE_HARDNESS, 20.0F, "stone_block");
      this.pick = true;
      this.setLocation("pre_orichalcum1");
      this.sell = 50;
      this.buy = Conversions.sellToBuy(this.sell);
      this.material = true;
   }

   public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
      if (WorldStateHolder.get(worldIn).demonAltarsDestroyed >= 2) {
         worldIn.setBlockState(pos, BlocksT.ORICHALCUM_ORE.getDefaultState());
      }

   }

   public boolean getTickRandomly(BlockState state) {
      return true;
   }
}
