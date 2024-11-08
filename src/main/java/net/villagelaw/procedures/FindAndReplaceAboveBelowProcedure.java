package net.villagelaw.procedures;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

public class FindAndReplaceAboveBelowProcedure {
	public static boolean execute(LevelAccessor world, double x, double z, BlockState replacement, BlockState target, boolean DEBUG, boolean replace, double xSize, double ynSize, double ypSize, double zSize) {
		boolean isPresent = false;
		double xCount = 0;
		double yCount = 0;
		double zCount = 0;
		double xPos = 0;
		double zPos = 0;
		double yPos = 0;
		double yPos2 = 0;
		double yOffset = 0;
		xCount = 0;
		isPresent = false;
		while (xCount < xSize) {
			if (isPresent) {
				break;
			}
			xPos = x + xCount - Math.round(xSize / 2);
			zCount = 0;
			while (zCount < zSize) {
				if (isPresent) {
					break;
				}
				zPos = z + zCount - Math.round(zSize / 2);
				yCount = 0;
				while (yCount < ypSize) {
					if (isPresent) {
						break;
					}
					yOffset = 0;
					yPos = yOffset + world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) (x + xCount), (int) (z + zCount)) + yCount;
					yPos2 = yOffset + world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) (x + xCount), (int) (z + zCount)) - yCount;
					if ((world.getBlockState(BlockPos.containing(xPos, yPos, zPos))).getBlock() == target.getBlock()) {
						if (replace) {
							if (!((world.getBlockState(BlockPos.containing(xPos, yPos + 1, zPos))).getBlock() == Blocks.AIR)) {
								world.setBlock(BlockPos.containing(xPos, yPos + 1, zPos), replacement, 3);
							} else if (!((world.getBlockState(BlockPos.containing(xPos, yPos - 1, zPos))).getBlock() == Blocks.AIR)) {
								world.setBlock(BlockPos.containing(xPos, yPos - 1, zPos), replacement, 3);
							}
						}
						if (!world.isClientSide() && world.getServer() != null)
							world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("[WORLDGEN DEBUG] Check returned: True"), false);
						isPresent = true;
						break;
					} else if ((world.getBlockState(BlockPos.containing(xPos, yPos2, zPos))).getBlock() == target.getBlock() && yCount < ynSize) {
						if (replace) {
							if (!((world.getBlockState(BlockPos.containing(xPos, yPos2 + 1, zPos))).getBlock() == Blocks.AIR)) {
								world.setBlock(BlockPos.containing(xPos, yPos2 + 1, zPos), replacement, 3);
							} else if (!((world.getBlockState(BlockPos.containing(xPos, yPos2 - 1, zPos))).getBlock() == Blocks.AIR)) {
								world.setBlock(BlockPos.containing(xPos, yPos2 - 1, zPos), replacement, 3);
							}
						}
						if (!world.isClientSide() && world.getServer() != null)
							world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("[WORLDGEN DEBUG] Check returned: True"), false);
						isPresent = true;
						break;
					} else {
						isPresent = false;
					}
					yCount = 1 + yCount;
				}
				zCount = 1 + zCount;
			}
			xCount = 1 + xCount;
		}
		if (DEBUG == true) {
			if (!world.isClientSide() && world.getServer() != null)
				world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("[WORLDGEN DEBUG] Checking cube of: "), false);
			if (!world.isClientSide() && world.getServer() != null)
				world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("X: " + (x - Math.round(xSize / 2)) + "->" + (x + (xSize - 1) - Math.round(xSize / 2)))), false);
			if (!world.isClientSide() && world.getServer() != null)
				world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("Y: " + (yOffset + world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) (x + xCount), (int) (z + zCount)) - (ynSize - 1)) + "->"
						+ (yOffset + world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) (x + xCount), (int) (z + zCount)) + ypSize - 1))), false);
			if (!world.isClientSide() && world.getServer() != null)
				world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("Z: " + (z - Math.round(zSize / 2)) + "->" + (z + (zSize - 1) - Math.round(zSize / 2)))), false);
		}
		return isPresent;
	}
}
