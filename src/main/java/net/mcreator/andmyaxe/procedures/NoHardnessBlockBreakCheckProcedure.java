package net.mcreator.andmyaxe.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.andmyaxe.network.AndMyAxeModVariables;

public class NoHardnessBlockBreakCheckProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world.getBlockState(new BlockPos(x, y, z)).getDestroySpeed(world, new BlockPos(x, y, z)) < 0.1) {
			if ((entity.getCapability(AndMyAxeModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new AndMyAxeModVariables.PlayerVariables())).NoHardnessBlockLclickCheck == false) {
				{
					boolean _setval = true;
					entity.getCapability(AndMyAxeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.NoHardnessBlockLclickCheck = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
		new Object() {
			private int ticks = 0;
			private float waitTicks;
			private LevelAccessor world;

			public void start(LevelAccessor world, int waitTicks) {
				this.waitTicks = waitTicks;
				MinecraftForge.EVENT_BUS.register(this);
				this.world = world;
			}

			@SubscribeEvent
			public void tick(TickEvent.ServerTickEvent event) {
				if (event.phase == TickEvent.Phase.END) {
					this.ticks += 1;
					if (this.ticks >= this.waitTicks)
						run();
				}
			}

			private void run() {
				if (world.getBlockState(new BlockPos(x, y, z)).getDestroySpeed(world, new BlockPos(x, y, z)) < 0.1) {
					if ((entity.getCapability(AndMyAxeModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new AndMyAxeModVariables.PlayerVariables())).NoHardnessBlockLclickCheck == true) {
						{
							boolean _setval = false;
							entity.getCapability(AndMyAxeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.NoHardnessBlockLclickCheck = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
				}
				MinecraftForge.EVENT_BUS.unregister(this);
			}
		}.start(world, 2);
	}
}
