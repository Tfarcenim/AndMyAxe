package net.mcreator.andmyaxe.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;

import net.mcreator.andmyaxe.network.AndMyAxeModVariables;

public class SwapperTogglerOnKeyPressedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(AndMyAxeModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new AndMyAxeModVariables.PlayerVariables())).DisableToolSwap == false) {
			{
				boolean _setval = true;
				entity.getCapability(AndMyAxeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.DisableToolSwap = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("Tool Swapping Disabled"), (false));
		} else if ((entity.getCapability(AndMyAxeModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new AndMyAxeModVariables.PlayerVariables())).DisableToolSwap == true) {
			{
				boolean _setval = false;
				entity.getCapability(AndMyAxeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.DisableToolSwap = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("Tool Swapping Enabled"), (false));
		}
	}
}
