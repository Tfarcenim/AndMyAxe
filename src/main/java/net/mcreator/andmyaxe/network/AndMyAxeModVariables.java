package net.mcreator.andmyaxe.network;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import net.mcreator.andmyaxe.AndMyAxeMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AndMyAxeModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		AndMyAxeMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new,
				PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getPlayer().level.isClientSide())
				((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
						.syncPlayerVariables(event.getPlayer());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getPlayer().level.isClientSide())
				((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
						.syncPlayerVariables(event.getPlayer());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getPlayer().level.isClientSide())
				((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
						.syncPlayerVariables(event.getPlayer());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new PlayerVariables()));
			clone.LeftClickCheck = original.LeftClickCheck;
			clone.CancelToolSwap = original.CancelToolSwap;
			clone.DisableToolSwap = original.DisableToolSwap;
			clone.NoHardnessBlockLclickCheck = original.NoHardnessBlockLclickCheck;
			clone.StartingSlotNo = original.StartingSlotNo;
			clone.MaihHand = original.MaihHand;
			clone.NewSlotNumber = original.NewSlotNumber;
			clone.NewMainHand = original.NewMainHand;
			if (!event.isWasDeath()) {
			}
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("and_my_axe", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public boolean LeftClickCheck = false;
		public boolean CancelToolSwap = false;
		public boolean DisableToolSwap = false;
		public boolean NoHardnessBlockLclickCheck = false;
		public double StartingSlotNo = 0;
		public ItemStack MaihHand = ItemStack.EMPTY;
		public double NewSlotNumber = 0;
		public ItemStack NewMainHand = ItemStack.EMPTY;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				AndMyAxeMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putBoolean("LeftClickCheck", LeftClickCheck);
			nbt.putBoolean("CancelToolSwap", CancelToolSwap);
			nbt.putBoolean("DisableToolSwap", DisableToolSwap);
			nbt.putBoolean("NoHardnessBlockLclickCheck", NoHardnessBlockLclickCheck);
			nbt.putDouble("StartingSlotNo", StartingSlotNo);
			nbt.put("MaihHand", MaihHand.save(new CompoundTag()));
			nbt.putDouble("NewSlotNumber", NewSlotNumber);
			nbt.put("NewMainHand", NewMainHand.save(new CompoundTag()));
			return nbt;
		}

		public void readNBT(Tag Tag) {
			CompoundTag nbt = (CompoundTag) Tag;
			LeftClickCheck = nbt.getBoolean("LeftClickCheck");
			CancelToolSwap = nbt.getBoolean("CancelToolSwap");
			DisableToolSwap = nbt.getBoolean("DisableToolSwap");
			NoHardnessBlockLclickCheck = nbt.getBoolean("NoHardnessBlockLclickCheck");
			StartingSlotNo = nbt.getDouble("StartingSlotNo");
			MaihHand = ItemStack.of(nbt.getCompound("MaihHand"));
			NewSlotNumber = nbt.getDouble("NewSlotNumber");
			NewMainHand = ItemStack.of(nbt.getCompound("NewMainHand"));
		}
	}

	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new PlayerVariables()));
					variables.LeftClickCheck = message.data.LeftClickCheck;
					variables.CancelToolSwap = message.data.CancelToolSwap;
					variables.DisableToolSwap = message.data.DisableToolSwap;
					variables.NoHardnessBlockLclickCheck = message.data.NoHardnessBlockLclickCheck;
					variables.StartingSlotNo = message.data.StartingSlotNo;
					variables.MaihHand = message.data.MaihHand;
					variables.NewSlotNumber = message.data.NewSlotNumber;
					variables.NewMainHand = message.data.NewMainHand;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
