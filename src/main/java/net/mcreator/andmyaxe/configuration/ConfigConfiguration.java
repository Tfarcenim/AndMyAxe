package net.mcreator.andmyaxe.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Boolean> CHECK_WOOD;
	public static final ForgeConfigSpec.ConfigValue<Boolean> CHECK_LEAVES;
	public static final ForgeConfigSpec.ConfigValue<Boolean> CHECK_EARTH;
	public static final ForgeConfigSpec.ConfigValue<Boolean> CHECK_SAND;
	public static final ForgeConfigSpec.ConfigValue<Boolean> CHECK_IRON;
	public static final ForgeConfigSpec.ConfigValue<Boolean> CHECK_ANVIL;
	public static final ForgeConfigSpec.ConfigValue<Boolean> CHECK_ICE;
	public static final ForgeConfigSpec.ConfigValue<Boolean> CHECK_SNOW;
	public static final ForgeConfigSpec.ConfigValue<Boolean> CHECK_WEB;
	public static final ForgeConfigSpec.ConfigValue<Boolean> CHECK_PISTON;
	public static final ForgeConfigSpec.ConfigValue<Boolean> CHECK_ROCK;
	static {
		BUILDER.push("When left-clicked, which block materials should be allowed to run checks for the appropriate tool in the player's inventory");
		CHECK_WOOD = BUILDER.define("check_when_wood", true);
		CHECK_LEAVES = BUILDER.define("check_when_leaves", true);
		CHECK_EARTH = BUILDER.define("check_when_dirt_or_gravel", true);
		CHECK_SAND = BUILDER.define("check_when_sand", true);
		CHECK_IRON = BUILDER.define("check_when_iron", true);
		CHECK_ANVIL = BUILDER.define("check_when_anvil", true);
		CHECK_ICE = BUILDER.define("check_when_ice", true);
		CHECK_SNOW = BUILDER.define("check_when_snow", true);
		CHECK_WEB = BUILDER.define("check_when_cobweb", true);
		CHECK_PISTON = BUILDER.comment("").define("check_when_piston", true);
		CHECK_ROCK = BUILDER.define("check_when_rock", true);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
