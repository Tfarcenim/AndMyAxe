package net.mcreator.andmyaxe.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	static {
		BUILDER.push("When left-clicked, which block materials should be allowed to run checks for the appropriate tool in the player's inventory");

		SPEC = BUILDER.build();
	}

}
