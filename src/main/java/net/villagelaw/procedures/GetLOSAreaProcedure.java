package net.villagelaw.procedures;

import net.minecraft.world.level.ClipContext;
import net.minecraft.world.entity.Entity;

public class GetLOSAreaProcedure {
	public static boolean execute(Entity entity, Entity entity_target, double ax, double ay, double az) {
		if (entity == null || entity_target == null)
			return false;
		double errorSight = 0;
		return Math
				.abs(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(64)), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, entity)).getBlockPos().getX()
						- entity_target.getX()) <= ax
				&& entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(64)), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, entity)).getBlockPos().getY()
						- entity_target.getY() <= ay
				&& Math.abs(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(64)), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ()
						- entity_target.getZ()) <= az;
	}
}
