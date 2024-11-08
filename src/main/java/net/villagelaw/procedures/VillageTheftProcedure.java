package net.villagelaw.procedures;

import net.villagelaw.network.VillagelawModVariables;
import net.villagelaw.init.VillagelawModMobEffects;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class VillageTheftProcedure {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getLevel().getBlockState(event.getPos()), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		execute(null, world, x, y, z, blockstate, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		double errorSight = 0;
		String pCrime = "";
		boolean isNoticed = false;
		boolean isProtected = false;
		if ((ForgeRegistries.BLOCKS.getKey(blockstate.getBlock()).toString()).contains("chest") || (ForgeRegistries.BLOCKS.getKey(blockstate.getBlock()).toString()).contains("barrel")) {
			if (!(entity instanceof LivingEntity _livEnt4 && _livEnt4.hasEffect(VillagelawModMobEffects.AUTHORITY_EFFECT.get()))) {
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
					if (world instanceof ServerLevel _level7 && _level7.isVillage(BlockPos.containing(x, y, z))) {
						InvestigatebreakProcedure.execute(world, x, y, z, entity);
						isProtected = false;
						if (VillagelawModVariables.isBlockProtection) {
							{
								final Vec3 _center = new Vec3(x, y, z);
								List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(128 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
								for (Entity entityiterator : _entfound) {
									if ((ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).contains("law_golem")) {
										isProtected = true;
										if (entity instanceof Player _player && !_player.level().isClientSide())
											_player.displayClientMessage(Component.literal(("This "
													+ ((ForgeRegistries.BLOCKS.getKey(blockstate.getBlock()).toString()).substring((int) (1 + (ForgeRegistries.BLOCKS.getKey(blockstate.getBlock()).toString()).indexOf(":", 0)))).replace("_", " ")
													+ " is protected by the local Guardian of Law")), true);
										if (event != null && event.isCancelable()) {
											event.setCanceled(true);
										} else if (event != null && event.hasResult()) {
											event.setResult(Event.Result.DENY);
										}
										break;
									}
								}
							}
						}
						if (VillagelawModVariables.isTheftCrime) {
							CheckLOSProcedure.execute(world, x, y, z, entity, "theft");
						}
						if (VillagelawModVariables.isNoticed) {
							if (isProtected) {
								if (entity instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal("You have been noticed attempting Theft!"), false);
							} else {
								if (entity instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal("You have been noticed committing Theft!"), false);
							}
						}
						VillagelawModVariables.isNoticed = false;
					}
				}
			}
		}
	}
}
