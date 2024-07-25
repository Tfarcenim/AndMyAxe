
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.andmyaxe.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class AndMyAxeModKeyMappings {
//	public static final KeyMapping SWAPPER_STOPPER = new KeyMapping("key.and_my_axe.swapper_stopper", GLFW.GLFW_KEY_LEFT_SHIFT,
	//		"key.categories.gameplay");
	public static final KeyMapping SWAPPER_TOGGLER = new KeyMapping("key.and_my_axe.swapper_toggler", GLFW.GLFW_KEY_H, "key.categories.gameplay");

	@SubscribeEvent
	public static void registerKeyBindings(FMLClientSetupEvent event) {
		//ClientRegistry.registerKeyBinding(SWAPPER_STOPPER);
		ClientRegistry.registerKeyBinding(SWAPPER_TOGGLER);
	}

	public static boolean ACTIVE = true;

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onKeyInput(InputEvent.KeyInputEvent event) {
			if (Minecraft.getInstance().screen == null) {
				if (event.getKey() == SWAPPER_TOGGLER.getKey().getValue()) {
					if (event.getAction() == GLFW.GLFW_PRESS) {
						ACTIVE = !ACTIVE;
					}
				}
			}
		}
	}
}
