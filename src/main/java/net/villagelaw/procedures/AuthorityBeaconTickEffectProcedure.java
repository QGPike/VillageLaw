package net.villagelaw.procedures;

import net.villagelaw.init.VillagelawModMobEffects;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.ArrayList;

public class AuthorityBeaconTickEffectProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		for (Entity entityiterator : new ArrayList<>(world.players())) {
			if (GetmagnitudeProcedure.execute(entityiterator.getX(), x, entityiterator.getY(), y, entityiterator.getZ(), z) <= 32) {
				if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(VillagelawModMobEffects.AUTHORITY_EFFECT.get(), 60, 1, false, false));
			}
		}
	}
}
