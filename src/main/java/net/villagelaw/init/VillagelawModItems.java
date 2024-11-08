
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.villagelaw.init;

import net.villagelaw.item.LawchargetexItem;
import net.villagelaw.item.LawCoreItem;
import net.villagelaw.VillagelawMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

public class VillagelawModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, VillagelawMod.MODID);
	public static final RegistryObject<Item> TOTEM_OF_LAW_CORE = block(VillagelawModBlocks.TOTEM_OF_LAW_CORE);
	public static final RegistryObject<Item> AUTHORITY_BEACON = block(VillagelawModBlocks.AUTHORITY_BEACON);
	public static final RegistryObject<Item> LAW_GOLEM_SPAWN_EGG = REGISTRY.register("law_golem_spawn_egg", () -> new ForgeSpawnEggItem(VillagelawModEntities.LAW_GOLEM, -10797527, -13990092, new Item.Properties()));
	public static final RegistryObject<Item> LAW_CORE = REGISTRY.register("law_core", () -> new LawCoreItem());
	public static final RegistryObject<Item> LAWCHARGETEX = REGISTRY.register("lawchargetex", () -> new LawchargetexItem());

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
