package net.villagelaw.procedures;

import net.villagelaw.network.VillagelawModVariables;
import net.villagelaw.init.VillagelawModMobEffects;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class VillageForbiddenItems2Procedure {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double errorSight = 0;
		boolean isNoticed = false;
		boolean isProtected = false;
		String pCrime = "";
		String fItem = "";
		if (VillagelawModVariables.isItemCrime) {
			isProtected = false;
			fItem = "null";
			if ((ForgeRegistries.ITEMS.getKey((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()).toString()).equals(ForgeRegistries.ITEMS.getKey(Items.FLINT_AND_STEEL).toString())) {
				isProtected = true;
				fItem = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getDisplayName().getString();
			} else if ((ForgeRegistries.ITEMS.getKey((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem()).toString()).equals(ForgeRegistries.ITEMS.getKey(Items.FLINT_AND_STEEL).toString())) {
				isProtected = true;
				fItem = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getDisplayName().getString();
			}
			if (isProtected && !(entity instanceof LivingEntity _livEnt10 && _livEnt10.hasEffect(VillagelawModMobEffects.AUTHORITY_EFFECT.get()))) {
				if (entity instanceof Player && !(new Object() {
					public boolean checkGamemode(Entity _ent) {
						if (_ent instanceof ServerPlayer _serverPlayer) {
							return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
						} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
							return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
									&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
						}
						return false;
					}
				}.checkGamemode(entity))) {
					if (world instanceof ServerLevel _level13 && _level13.isVillage(BlockPos.containing(x, y, z))) {
						InvestigatebreakProcedure.execute(world, x, y, z, entity);
						CheckLOSProcedure.execute(world, x, y, z, entity, "use");
						if (VillagelawModVariables.isNoticed) {
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal(("You have been noticed using a " + fItem + "!")), false);
						}
						VillagelawModVariables.isNoticed = false;
					}
				}
			}
		}
	}
}
