package net.villagelaw.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import java.util.List;
import java.util.Comparator;

public class LawGolemSpawningConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean isValid = false;
		boolean isGolem = false;
		boolean isLawGolem = false;
		isGolem = false;
		isLawGolem = false;
		isValid = world instanceof ServerLevel _level0 && _level0.isVillage(BlockPos.containing(x, y, z));
		if (isValid) {
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(32 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if ((ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).contains("iron_golem")) {
						isGolem = true;
					} else if ((ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).contains("law")) {
						isLawGolem = true;
						break;
					}
				}
			}
		}
		isValid = isGolem && !isLawGolem;
		return isValid;
	}
}
