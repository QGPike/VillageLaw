
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.villagelaw.init;

import net.villagelaw.client.renderer.LawGolemRenderer;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VillagelawModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(VillagelawModEntities.LAW_GOLEM.get(), LawGolemRenderer::new);
		event.registerEntityRenderer(VillagelawModEntities.LAW_CHARGE.get(), ThrownItemRenderer::new);
	}
}
