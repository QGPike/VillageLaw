
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.villagelaw.init;

import net.villagelaw.block.TotemOfLawCoreBlock;
import net.villagelaw.block.AuthorityBeaconBlock;
import net.villagelaw.VillagelawMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

public class VillagelawModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, VillagelawMod.MODID);
	public static final RegistryObject<Block> TOTEM_OF_LAW_CORE = REGISTRY.register("totem_of_law_core", () -> new TotemOfLawCoreBlock());
	public static final RegistryObject<Block> AUTHORITY_BEACON = REGISTRY.register("authority_beacon", () -> new AuthorityBeaconBlock());

	// Start of user code block custom blocks
	// End of user code block custom blocks
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class BlocksClientSideHandler {
		@SubscribeEvent
		public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
			TotemOfLawCoreBlock.blockColorLoad(event);
			AuthorityBeaconBlock.blockColorLoad(event);
		}

		@SubscribeEvent
		public static void itemColorLoad(RegisterColorHandlersEvent.Item event) {
			TotemOfLawCoreBlock.itemColorLoad(event);
			AuthorityBeaconBlock.itemColorLoad(event);
		}
	}
}
