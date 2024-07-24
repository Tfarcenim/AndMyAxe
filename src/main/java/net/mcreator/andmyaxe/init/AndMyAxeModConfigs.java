package net.mcreator.andmyaxe.init;

import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.mcreator.andmyaxe.configuration.ConfigConfiguration;
import net.mcreator.andmyaxe.AndMyAxeMod;

@Mod.EventBusSubscriber(modid = AndMyAxeMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AndMyAxeModConfigs {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigConfiguration.SPEC, "andmyaxe.toml");
		});
	}
}
