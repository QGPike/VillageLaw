package net.villagelaw.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import java.util.List;
import java.util.Comparator;

public class InvestigatebreakProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean isNoticed = false;
		String pCrime = "";
		double errorSight = 0;
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(64 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
			for (Entity entityiterator : _entfound) {
				if ((ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).contains("villager") || (ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).contains("iron_golem")
						|| (ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).contains("law_golem")) {
					errorSight = GetmagnitudeProcedure.execute(entityiterator.getX(), x, entityiterator.getY(), y, entityiterator.getZ(), z);
					entityiterator.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())));
					if (errorSight <= 9) {
						if (entityiterator instanceof Mob _entity)
							_entity.getNavigation().moveTo((entity.getX()), (entity.getY()), (entity.getZ()), 0.8);
					}
				}
			}
		}
	}
}
