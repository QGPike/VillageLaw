package net.villagelaw.procedures;

import org.checkerframework.checker.units.qual.s;

import net.villagelaw.init.VillagelawModBlocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

public class CheckBlockTemplateProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean villageDetect = false;
		double xDetect = 0;
		double yDetect = 0;
		double zDetect = 0;
		String stringDetect = "";
		xDetect = 0;
		yDetect = 0;
		zDetect = 0;
		stringDetect = CheckAreaForBlockProcedure.execute(world, x, y, z, VillagelawModBlocks.TOTEM_OF_LAW_CORE.get().defaultBlockState(), Blocks.BELL.defaultBlockState(), false, false, 24, 8, 24, 24);
		xDetect = new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((stringDetect.substring((int) stringDetect.indexOf("X", 0), (int) stringDetect.indexOf("Y", 0))).replace("X:", ""));
		yDetect = new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((stringDetect.substring((int) stringDetect.indexOf("Y", 0), (int) stringDetect.indexOf("Z", 0))).replace("Y:", ""));
		zDetect = new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((stringDetect.substring((int) stringDetect.indexOf("Z", 0))).replace("Z:", ""));
		if ((world.getBlockState(BlockPos.containing(xDetect, yDetect, zDetect))).getBlock() == Blocks.BELL) {
			if (!world.isClientSide() && world.getServer() != null)
				world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("bell detected"), false);
			return true;
		}
		return false;
	}
}
