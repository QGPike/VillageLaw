package net.villagelaw.procedures;

import net.villagelaw.init.VillagelawModMobEffects;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;

public class LawGolemDeathTriggerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double y1, double y2) {
		if (entity == null)
			return;
		Entity closest_player = null;
		Entity affected = null;
		double lastMag = 0;
		double Mag = 0;
		lastMag = 100;
		for (Entity entityiterator : new ArrayList<>(world.players())) {
			if (!(entityiterator instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(VillagelawModMobEffects.AUTHORITY_EFFECT.get()))) {
				Mag = GetmagnitudeProcedure.execute(entityiterator.getX(), x, y1, y2, entityiterator.getY(), y);
				if (Mag <= 64) {
					if (Mag < Mag) {
						lastMag = Mag;
						closest_player = entityiterator;
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.lightning_bolt.thunder")), SoundSource.NEUTRAL, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.lightning_bolt.thunder")), SoundSource.NEUTRAL, 1, 1, false);
						}
					}
					if (entityiterator instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("The Gaurdian of Law for this Land has fallen!"), true);
				}
			}
		}
		if (entity instanceof Player) {
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(64 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (lastMag != 100) {
						if ((ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).contains("villager") || (ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).contains("law_golem")
								|| (ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).contains("iron_golem")) {
							entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK), closest_player), 4);
							if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 1));
						}
					}
				}
			}
			if (lastMag != 100) {
				if (closest_player instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("A Gaurdian of Law has fallen. The Villagers blame you!"), true);
			}
		}
	}
}
