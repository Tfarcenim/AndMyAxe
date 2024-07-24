/*
 *    MCreator note:
 *
 *    If you lock base mod element files, you can edit this file and it won't get overwritten.
 *    If you change your modid or package, you need to apply these changes to this file MANUALLY.
 *
 *    Settings in @Mod annotation WON'T be changed in case of the base mod element
 *    files lock too, so you need to set them manually here in such case.
 *
 *    If you do not lock base mod element files in Workspace settings, this file
 *    will be REGENERATED on each build.
 *
 */
package net.mcreator.andmyaxe;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;

@Mod(AndMyAxeMod.MODID)
public class AndMyAxeMod {
	public static final Logger LOGGER = LogManager.getLogger(AndMyAxeMod.class);
	public static final String MODID = "and_my_axe";

    public AndMyAxeMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		MinecraftForge.EVENT_BUS.addListener(this::onBreakSpeed);
		MinecraftForge.EVENT_BUS.addListener(this::clientTick);
	}

	private void onBreakSpeed(PlayerEvent.BreakSpeed event) {
		Player player = event.getPlayer();
		BlockState state = event.getState();

		Level level = player.level;
		if (level.isClientSide) {
			if (event.getNewSpeed() <= 1) {
				boolean found = false;
				for (int i = 0;i < 9;i++) {
					ItemStack potentialTool = player.getInventory().getItem(i);
					if (potentialTool.getDestroySpeed(state) > 1) {
						player.getInventory().selected = i;
						found = true;
						break;
					}
				}
				if (!found) {

				}
			}
		}
	}

	static boolean needsSwap;
	static int swapTo;

	private void clientTick(TickEvent.ClientTickEvent event) {

	}
}
