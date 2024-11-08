package net.villagelaw.procedures;

import net.villagelaw.network.VillagelawModVariables;
import net.villagelaw.init.VillagelawModEntities;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;

public class CheckLOSProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, String crime) {
		if (entity == null || crime == null)
			return;
		Entity sacrifice = null;
		boolean isIG = false;
		boolean isProtected = false;
		boolean tooClose = false;
		double yScalar = 0;
		double distance = 0;
		double aggroRange = 0;
		isIG = false;
		isProtected = false;
		aggroRange = 0;
		if ((crime).equals("break")) {
			aggroRange = 2;
		} else if ((crime).equals("Theft")) {
			aggroRange = 1;
		} else if ((crime).equals("use")) {
			aggroRange = 3;
		}
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(64 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
			for (Entity entityiterator : _entfound) {
				yScalar = 1;
				distance = GetmagnitudeProcedure.execute(x, entityiterator.getX(), y, entityiterator.getY(), z, entityiterator.getZ());
				tooClose = distance <= aggroRange;
				if ((ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).contains("villager") || (ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).contains("law_golem")
						|| (ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).contains("iron_golem")) {
					if (!tooClose) {
						if (world.canSeeSkyFromBelowWater(BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()))) {
							yScalar = (Getmagnitude2dProcedure.execute(x, entityiterator.getX(), z, entityiterator.getZ()) / 2) % 32 + 1;
						}
					}
					if ((tooClose || GetLOSAreaProcedure.execute(entityiterator, entity, 1, yScalar, 1)) && !VillagelawModVariables.pImmunity) {
						DoAggroProcedure.execute(world, entity, entityiterator);
						VillagelawModVariables.isNoticed = true;
					}
				}
				if (VillagelawModVariables.isNoticed && (ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).contains("iron_golem")) {
					sacrifice = entityiterator;
					isIG = true;
				}
			}
		}
		if (isIG) {
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(64 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if ((ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).contains("law_golem")) {
						isProtected = true;
						break;
					}
				}
			}
			if (!isProtected) {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = VillagelawModEntities.LAW_GOLEM.get().spawn(_level, BlockPos.containing(sacrifice.getX(), sacrifice.getY(), sacrifice.getZ()), MobSpawnType.MOB_SUMMONED);
					if (entityToSpawn != null) {
						entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
					}
				}
				if (sacrifice instanceof LivingEntity _entity)
					_entity.setHealth(0);
				for (Entity entityiterator : new ArrayList<>(world.players())) {
					if (Getmagnitude2dProcedure.execute(sacrifice.getX(), entityiterator.getX(), sacrifice.getZ(), entityiterator.getZ()) <= 64) {
						if (entityiterator instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal("A new Law Golem has emerged!"), true);
					}
				}
			}
		}
	}
}
