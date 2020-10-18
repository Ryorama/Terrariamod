package kmerrill285.trewrite.blocks;

import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class Altar extends BlockT {
   public Altar(float hardness, float difficulty, String name) {
      super(Properties.create(Material.ROCK).sound(SoundType.STONE).lightValue(1), hardness, difficulty, name);
      this.setDefaultState((BlockState)this.stateContainer.getBaseState());
      this.setLocation(name);
      BlockRenderLayer var10001 = this.renderLayer;
      this.setRenderLayer(BlockRenderLayer.CUTOUT);
      this.hammer = true;
   }

   public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
	  WorldStateHolder.get(world).demonAltarsDestroyed += 1;
      if (WorldStateHolder.get(world).demonAltarsDestroyed == 1) {
         world.getServer().getPlayerList().sendMessage((new StringTextComponent("Your world has been blessed with cobalt and pallidum!")).applyTextStyles(new TextFormatting[]{TextFormatting.GREEN, TextFormatting.BOLD}));
      }

      if (WorldStateHolder.get(world).demonAltarsDestroyed == 2) {
         world.getServer().getPlayerList().sendMessage((new StringTextComponent("Your world has been blessed with mithril and orichalcum!")).applyTextStyles(new TextFormatting[]{TextFormatting.GREEN, TextFormatting.BOLD}));
      }

      if (WorldStateHolder.get(world).demonAltarsDestroyed == 3) {
         world.getServer().getPlayerList().sendMessage((new StringTextComponent("Your world has been blessed with adamantite and titanium!")).applyTextStyles(new TextFormatting[]{TextFormatting.GREEN, TextFormatting.BOLD}));
      }

   }
}
