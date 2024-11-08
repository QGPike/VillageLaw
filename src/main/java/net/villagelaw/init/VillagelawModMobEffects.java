
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.villagelaw.init;

import net.villagelaw.potion.AuthorityEffectMobEffect;
import net.villagelaw.VillagelawMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

public class VillagelawModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, VillagelawMod.MODID);
	public static final RegistryObject<MobEffect> AUTHORITY_EFFECT = REGISTRY.register("authority_effect", () -> new AuthorityEffectMobEffect());
}
