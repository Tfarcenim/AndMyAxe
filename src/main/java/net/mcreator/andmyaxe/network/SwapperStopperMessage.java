
package net.mcreator.andmyaxe.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.andmyaxe.procedures.SwapperStopperOnKeyReleasedProcedure;
import net.mcreator.andmyaxe.procedures.SwapperStopperOnKeyPressedProcedure;
import net.mcreator.andmyaxe.AndMyAxeMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SwapperStopperMessage {
	int type, pressedms;

	public SwapperStopperMessage(int type, int pressedms) {
		this.type = type;
		this.pressedms = pressedms;
	}

	public SwapperStopperMessage(FriendlyByteBuf buffer) {
		this.type = buffer.readInt();
		this.pressedms = buffer.readInt();
	}

	public static void buffer(SwapperStopperMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.type);
		buffer.writeInt(message.pressedms);
	}

	public static void handler(SwapperStopperMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			pressAction(context.getSender(), message.type, message.pressedms);
		});
		context.setPacketHandled(true);
	}

	public static void pressAction(Player entity, int type, int pressedms) {
		Level world = entity.level;
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(entity.blockPosition()))
			return;
		if (type == 0) {

			SwapperStopperOnKeyPressedProcedure.execute(entity);
		}
		if (type == 1) {

			SwapperStopperOnKeyReleasedProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		AndMyAxeMod.addNetworkMessage(SwapperStopperMessage.class, SwapperStopperMessage::buffer, SwapperStopperMessage::new,
				SwapperStopperMessage::handler);
	}
}
