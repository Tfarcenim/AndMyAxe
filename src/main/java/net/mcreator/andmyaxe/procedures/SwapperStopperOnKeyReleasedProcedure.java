package net.mcreator.andmyaxe.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.andmyaxe.network.AndMyAxeModVariables;

public class SwapperStopperOnKeyReleasedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			boolean _setval = false;
			entity.getCapability(AndMyAxeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.CancelToolSwap = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
