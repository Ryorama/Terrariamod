package kmerrill285.trewrite.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.blocks.Door;
import kmerrill285.trewrite.blocks.Torch;
import kmerrill285.trewrite.entities.npc.EntityGuide;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class CheckHouse {

	public static final short MAX_HOME_SIZE = 5000;
	  
	public static boolean isValidHouseFromInside(World world, BlockPos pos, List<String> problems) {
		if (world.isBlockLoaded(pos)) {
			
			  Set<Long> visited = new HashSet<>();
		      visited.add(Long.valueOf(pos.toLong()));
		      Set<Long> toVisit = new HashSet<>();
		      House house = new House();
		      int lastSize = visited.size();
		      while (visited.size() < 5000) {
		    	  for (Iterator<Long> iterator = visited.iterator(); iterator.hasNext(); ) {
		              long lnpos = ((Long)iterator.next()).longValue();
		              BlockPos npos = BlockPos.fromLong(lnpos);
		              for (Direction f : Direction.values()) {
		                BlockPos mpos = npos.offset(f);
		                if (world.isBlockLoaded(mpos)) {
		                  BlockState state = world.getBlockState(mpos);
		                  if (state.getBlock() == Blocks.AIR) {
		                    toVisit.add(Long.valueOf(mpos.toLong()));
		                  } else if (state.getBlock() instanceof Torch && ((Torch)state.getBlock()) != null) {
		                    house.lights.add(Long.valueOf(mpos.toLong()));
		                    toVisit.add(Long.valueOf(mpos.toLong()));
		                  } else if (state.getBlock() instanceof Door) {
		                    house.exits.add(Long.valueOf(mpos.toLong()));
		                  } else if (BlocksT.TABLE.test(state.getBlock()) || state.getBlock() == BlocksT.WORKBENCH) {
		                    house.tables.add(Long.valueOf(mpos.toLong()));
		                    toVisit.add(Long.valueOf(mpos.toLong()));
		                  } 
		                } 
		              } 
		            }
			    	  visited.addAll(toVisit);
			          toVisit.clear();
			          if (visited.size() == lastSize) {
			            if (house.exits.size() == 0)
			              problems.add(TextFormatting.RED + "No doors found!"); 
			            if (house.lights.size() == 0)
			              problems.add(TextFormatting.RED + "No lights found!"); 
			            if (house.tables.size() == 0)
			              problems.add(TextFormatting.RED + "No tables found!"); 
			            if (lastSize < 24)
			              problems.add(TextFormatting.RED + "House it too small!"); 
			            boolean valid = (house.exits.size() > 0 && house.lights.size() > 0 && house.tables.size() > 0);
			            boolean occupied = false;
			            if (valid) {
			              int minx = 0, miny = 0, minz = 0, maxx = 0, maxy = 0, maxz = 0;
			              for (Long lnpos : visited) {
			                BlockPos npos = BlockPos.fromLong(lnpos.longValue());
			                minx = Math.min(minx, npos.getX());
			                miny = Math.min(miny, npos.getY());
			                minz = Math.min(minz, npos.getZ());
			                maxx = Math.max(maxx, npos.getX());
			                maxy = Math.max(maxy, npos.getY());
			                maxz = Math.max(maxz, npos.getZ());
			              } 
			              AxisAlignedBB aabb = new AxisAlignedBB(minx + 0.5D, miny + 0.5D, minz + 0.5D, maxx + 0.5D, maxy + 0.5D, maxz + 0.5D);
			              for (EntityGuide npc : world.getEntitiesWithinAABB(EntityGuide.class, aabb)) {
			                if (npc.isAlive())
			                  occupied = true; 
			              } 
			              if (occupied)
			                problems.add(TextFormatting.RED + "This house is already occupied!"); 
			            } 
			            return (valid && !occupied);
			          } 
			          lastSize = visited.size();
			        } 
			        if (visited.size() > 5000)
			          problems.add(TextFormatting.RED + "Home size limit exceeded: " + visited.size() + "/"); 
			        return (visited.size() < 5000);
			      } 
			      return true;
			    }
			  }
	
			      