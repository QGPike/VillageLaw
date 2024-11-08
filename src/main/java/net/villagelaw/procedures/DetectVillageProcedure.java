package net.villagelaw.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class DetectVillageProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, double radius) {
		boolean isValid = false;
		double xCount = 0;
		double yCount = 0;
		double zCount = 0;
		double countMax = 0;
		isValid = !(world instanceof ServerLevel _level0 && _level0.isVillage(BlockPos.containing(x, y, z)));
		xCount = 0;
		yCount = 0;
		zCount = 0;
		countMax = radius;
		if (isValid == true) {
			while (xCount < countMax && isValid == true) {
				while (zCount < countMax) {
					while (yCount < countMax) {
						if (world instanceof ServerLevel _level1 && _level1.isVillage(BlockPos.containing(x + xCount, y + yCount, z + zCount))) {
							isValid = false;
						} else if (world instanceof ServerLevel _level2 && _level2.isVillage(BlockPos.containing(x + xCount, y + yCount, z + zCount))) {
							isValid = false;
						}
						yCount = yCount + 1;
					}
					zCount = zCount + 1;
				}
				xCount = xCount + 1;
			}
		}
		return isValid;
	}
}
