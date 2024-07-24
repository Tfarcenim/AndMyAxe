package net.mcreator.andmyaxe.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

import net.mcreator.andmyaxe.network.AndMyAxeModVariables;

import java.util.concurrent.atomic.AtomicReference;

public class SwapperProcedure {
	public static void execute(LevelAccessor world, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		double toolCounter = 0;
		ItemStack chosenItem = ItemStack.EMPTY;
		ItemStack oldMainHand = ItemStack.EMPTY;
		if (new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayer _serverPlayer) {
					return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL;
				} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
					return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance()
							.getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SURVIVAL;
				}
				return false;
			}
		}.checkGamemode(entity)) {
			if ((entity.getCapability(AndMyAxeModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new AndMyAxeModVariables.PlayerVariables())).CancelToolSwap == false
					&& (entity.getCapability(AndMyAxeModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new AndMyAxeModVariables.PlayerVariables())).DisableToolSwap == false
					&& ((blockstate.getMaterial() == net.minecraft.world.level.material.Material.WOOD
							|| blockstate.getMaterial() == net.minecraft.world.level.material.Material.NETHER_WOOD)
							&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof AxeItem
									|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.is(ItemTags.create(new ResourceLocation("forge:tools/mattocks"))))
							|| blockstate.getMaterial() == net.minecraft.world.level.material.Material.LEAVES
									&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getItem() instanceof HoeItem
											|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
													.getItem() == Items.SHEARS)
							|| blockstate.getMaterial() == net.minecraft.world.level.material.Material.DIRT
									&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getItem() instanceof ShovelItem
											|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
													.is(ItemTags.create(new ResourceLocation("forge:tools/mattocks"))))
							|| blockstate.getMaterial() == net.minecraft.world.level.material.Material.GRASS
									&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getItem() instanceof ShovelItem
											|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
													.is(ItemTags.create(new ResourceLocation("forge:tools/mattocks"))))
							|| blockstate.getMaterial() == net.minecraft.world.level.material.Material.SAND
									&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getItem() instanceof ShovelItem
											|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
													.is(ItemTags.create(new ResourceLocation("forge:tools/mattocks"))))
							|| blockstate.getMaterial() == net.minecraft.world.level.material.Material.METAL
									&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()
											.isCorrectToolForDrops(blockstate))
							|| blockstate.getMaterial() == net.minecraft.world.level.material.Material.HEAVY_METAL
									&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()
											.isCorrectToolForDrops(blockstate))
							|| blockstate.getMaterial() == net.minecraft.world.level.material.Material.ICE
									&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getItem() instanceof PickaxeItem)
							|| blockstate.getMaterial() == net.minecraft.world.level.material.Material.STONE
									&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getItem() instanceof PickaxeItem)
							|| blockstate.getMaterial() == net.minecraft.world.level.material.Material.STONE
									&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()
											.isCorrectToolForDrops(blockstate))
							|| blockstate.getMaterial() == net.minecraft.world.level.material.Material.SNOW
									&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()
											.isCorrectToolForDrops(blockstate))
							|| blockstate.getMaterial() == net.minecraft.world.level.material.Material.STONE
									&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
											.getItem() instanceof PickaxeItem)
							|| blockstate.getMaterial() == net.minecraft.world.level.material.Material.STONE
									&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()
											.isCorrectToolForDrops(blockstate))
							|| blockstate.getMaterial() == net.minecraft.world.level.material.Material.WEB
									&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()
											.isCorrectToolForDrops(blockstate)))) {
				oldMainHand = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
				chosenItem = (ItemStack.EMPTY);
				toolCounter = 0;
				if (blockstate.getMaterial() == net.minecraft.world.level.material.Material.WOOD
						|| blockstate.getMaterial() == net.minecraft.world.level.material.Material.NETHER_WOOD) {
					{
						AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _iitemhandlerref.set(capability));
						if (_iitemhandlerref.get() != null) {
							for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
								ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
								if (itemstackiterator.getItem() instanceof AxeItem
										|| itemstackiterator.is(ItemTags.create(new ResourceLocation("forge:tools/mattocks")))) {
									toolCounter = toolCounter + 1;
									if (toolCounter == 1) {
										chosenItem = itemstackiterator;
										if (entity instanceof Player _player) {
											ItemStack _stktoremove = chosenItem;
											_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(),
													(chosenItem).getCount(), _player.inventoryMenu.getCraftSlots());
										}
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = chosenItem;
											_setstack.setCount((chosenItem).getCount());
											_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
										if (entity instanceof Player _player) {
											ItemStack _setstack = oldMainHand;
											_setstack.setCount((oldMainHand).getCount());
											ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
										}
									}
								}
							}
						}
					}
				}
				if (blockstate.getMaterial() == net.minecraft.world.level.material.Material.LEAVES) {
					{
						AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _iitemhandlerref.set(capability));
						if (_iitemhandlerref.get() != null) {
							for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
								ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
								if (itemstackiterator.getItem() instanceof HoeItem || itemstackiterator.getItem() == Items.SHEARS) {
									toolCounter = toolCounter + 1;
									if (toolCounter == 1) {
										chosenItem = itemstackiterator;
										if (entity instanceof Player _player) {
											ItemStack _stktoremove = chosenItem;
											_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(),
													(chosenItem).getCount(), _player.inventoryMenu.getCraftSlots());
										}
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = chosenItem;
											_setstack.setCount((chosenItem).getCount());
											_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
										if (entity instanceof Player _player) {
											ItemStack _setstack = oldMainHand;
											_setstack.setCount((oldMainHand).getCount());
											ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
										}
									}
								}
							}
						}
					}
				}
				if (blockstate.getMaterial() == net.minecraft.world.level.material.Material.DIRT) {
					{
						AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _iitemhandlerref.set(capability));
						if (_iitemhandlerref.get() != null) {
							for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
								ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
								if (itemstackiterator.getItem() instanceof ShovelItem
										|| itemstackiterator.is(ItemTags.create(new ResourceLocation("forge:tools/mattocks")))) {
									toolCounter = toolCounter + 1;
									if (toolCounter == 1) {
										chosenItem = itemstackiterator;
										if (entity instanceof Player _player) {
											ItemStack _stktoremove = chosenItem;
											_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(),
													(chosenItem).getCount(), _player.inventoryMenu.getCraftSlots());
										}
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = chosenItem;
											_setstack.setCount((chosenItem).getCount());
											_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
										if (entity instanceof Player _player) {
											ItemStack _setstack = oldMainHand;
											_setstack.setCount((oldMainHand).getCount());
											ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
										}
									}
								}
							}
						}
					}
				}
				if (blockstate.getMaterial() == net.minecraft.world.level.material.Material.GRASS) {
					{
						AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _iitemhandlerref.set(capability));
						if (_iitemhandlerref.get() != null) {
							for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
								ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
								if (itemstackiterator.getItem() instanceof ShovelItem
										|| itemstackiterator.is(ItemTags.create(new ResourceLocation("forge:tools/mattocks")))) {
									toolCounter = toolCounter + 1;
									if (toolCounter == 1) {
										chosenItem = itemstackiterator;
										if (entity instanceof Player _player) {
											ItemStack _stktoremove = chosenItem;
											_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(),
													(chosenItem).getCount(), _player.inventoryMenu.getCraftSlots());
										}
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = chosenItem;
											_setstack.setCount((chosenItem).getCount());
											_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
										if (entity instanceof Player _player) {
											ItemStack _setstack = oldMainHand;
											_setstack.setCount((oldMainHand).getCount());
											ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
										}
									}
								}
							}
						}
					}
				}
				if (blockstate.getMaterial() == net.minecraft.world.level.material.Material.SAND) {
					{
						AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _iitemhandlerref.set(capability));
						if (_iitemhandlerref.get() != null) {
							for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
								ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
								if (itemstackiterator.getItem() instanceof ShovelItem
										|| itemstackiterator.is(ItemTags.create(new ResourceLocation("forge:tools/mattocks")))) {
									toolCounter = toolCounter + 1;
									if (toolCounter == 1) {
										chosenItem = itemstackiterator;
										if (entity instanceof Player _player) {
											ItemStack _stktoremove = chosenItem;
											_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(),
													(chosenItem).getCount(), _player.inventoryMenu.getCraftSlots());
										}
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = chosenItem;
											_setstack.setCount((chosenItem).getCount());
											_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
										if (entity instanceof Player _player) {
											ItemStack _setstack = oldMainHand;
											_setstack.setCount((oldMainHand).getCount());
											ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
										}
									}
								}
							}
						}
					}
				}
				if (blockstate.getMaterial() == net.minecraft.world.level.material.Material.METAL) {
					{
						AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _iitemhandlerref.set(capability));
						if (_iitemhandlerref.get() != null) {
							for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
								ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
								if (itemstackiterator.getItem().isCorrectToolForDrops(blockstate)) {
									toolCounter = toolCounter + 1;
									if (toolCounter == 1) {
										chosenItem = itemstackiterator;
										if (entity instanceof Player _player) {
											ItemStack _stktoremove = chosenItem;
											_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(),
													(chosenItem).getCount(), _player.inventoryMenu.getCraftSlots());
										}
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = chosenItem;
											_setstack.setCount((chosenItem).getCount());
											_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
										if (entity instanceof Player _player) {
											ItemStack _setstack = oldMainHand;
											_setstack.setCount((oldMainHand).getCount());
											ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
										}
									}
								}
							}
						}
					}
				}
				if (blockstate.getMaterial() == net.minecraft.world.level.material.Material.HEAVY_METAL) {
					{
						AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _iitemhandlerref.set(capability));
						if (_iitemhandlerref.get() != null) {
							for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
								ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
								if (itemstackiterator.getItem().isCorrectToolForDrops(blockstate)) {
									toolCounter = toolCounter + 1;
									if (toolCounter == 1) {
										chosenItem = itemstackiterator;
										if (entity instanceof Player _player) {
											ItemStack _stktoremove = chosenItem;
											_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(),
													(chosenItem).getCount(), _player.inventoryMenu.getCraftSlots());
										}
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = chosenItem;
											_setstack.setCount((chosenItem).getCount());
											_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
										if (entity instanceof Player _player) {
											ItemStack _setstack = oldMainHand;
											_setstack.setCount((oldMainHand).getCount());
											ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
										}
									}
								}
							}
						}
					}
				}
				if (blockstate.getMaterial() == net.minecraft.world.level.material.Material.ICE) {
					{
						AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _iitemhandlerref.set(capability));
						if (_iitemhandlerref.get() != null) {
							for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
								ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
								if (itemstackiterator.getItem() instanceof PickaxeItem) {
									toolCounter = toolCounter + 1;
									if (toolCounter == 1) {
										chosenItem = itemstackiterator;
										if (entity instanceof Player _player) {
											ItemStack _stktoremove = chosenItem;
											_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(),
													(chosenItem).getCount(), _player.inventoryMenu.getCraftSlots());
										}
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = chosenItem;
											_setstack.setCount((chosenItem).getCount());
											_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
										if (entity instanceof Player _player) {
											ItemStack _setstack = oldMainHand;
											_setstack.setCount((oldMainHand).getCount());
											ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
										}
									}
								}
							}
						}
					}
				}
				if (blockstate.getMaterial() == net.minecraft.world.level.material.Material.STONE) {
					{
						AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _iitemhandlerref.set(capability));
						if (_iitemhandlerref.get() != null) {
							for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
								ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
								if (itemstackiterator.getItem() instanceof PickaxeItem) {
									toolCounter = toolCounter + 1;
									if (toolCounter == 1) {
										chosenItem = itemstackiterator;
										if (entity instanceof Player _player) {
											ItemStack _stktoremove = chosenItem;
											_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(),
													(chosenItem).getCount(), _player.inventoryMenu.getCraftSlots());
										}
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = chosenItem;
											_setstack.setCount((chosenItem).getCount());
											_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
										if (entity instanceof Player _player) {
											ItemStack _setstack = oldMainHand;
											_setstack.setCount((oldMainHand).getCount());
											ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
										}
									}
								}
							}
						}
					}
				}
				if (blockstate.getMaterial() == net.minecraft.world.level.material.Material.TOP_SNOW) {
					{
						AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _iitemhandlerref.set(capability));
						if (_iitemhandlerref.get() != null) {
							for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
								ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
								if (itemstackiterator.getItem().isCorrectToolForDrops(blockstate)) {
									toolCounter = toolCounter + 1;
									if (toolCounter == 1) {
										chosenItem = itemstackiterator;
										if (entity instanceof Player _player) {
											ItemStack _stktoremove = chosenItem;
											_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(),
													(chosenItem).getCount(), _player.inventoryMenu.getCraftSlots());
										}
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = chosenItem;
											_setstack.setCount((chosenItem).getCount());
											_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
										if (entity instanceof Player _player) {
											ItemStack _setstack = oldMainHand;
											_setstack.setCount((oldMainHand).getCount());
											ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
										}
									}
								}
							}
						}
					}
				}
				if (blockstate.getMaterial() == net.minecraft.world.level.material.Material.STONE) {
					{
						AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _iitemhandlerref.set(capability));
						if (_iitemhandlerref.get() != null) {
							for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
								ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
								if (itemstackiterator.getItem().isCorrectToolForDrops(blockstate)) {
									toolCounter = toolCounter + 1;
									if (toolCounter == 1) {
										chosenItem = itemstackiterator;
										if (entity instanceof Player _player) {
											ItemStack _stktoremove = chosenItem;
											_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(),
													(chosenItem).getCount(), _player.inventoryMenu.getCraftSlots());
										}
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = chosenItem;
											_setstack.setCount((chosenItem).getCount());
											_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
										if (entity instanceof Player _player) {
											ItemStack _setstack = oldMainHand;
											_setstack.setCount((oldMainHand).getCount());
											ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
										}
									}
								}
							}
						}
					}
				}
				if (blockstate.getMaterial() == net.minecraft.world.level.material.Material.WEB) {
					{
						AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _iitemhandlerref.set(capability));
						if (_iitemhandlerref.get() != null) {
							for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
								ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
								if (itemstackiterator.getItem().isCorrectToolForDrops(blockstate)) {
									toolCounter = toolCounter + 1;
									if (toolCounter == 1) {
										chosenItem = itemstackiterator;
										if (entity instanceof Player _player) {
											ItemStack _stktoremove = chosenItem;
											_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(),
													(chosenItem).getCount(), _player.inventoryMenu.getCraftSlots());
										}
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = chosenItem;
											_setstack.setCount((chosenItem).getCount());
											_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
										if (entity instanceof Player _player) {
											ItemStack _setstack = oldMainHand;
											_setstack.setCount((oldMainHand).getCount());
											ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
										}
									}
								}
							}
						}
					}
				}
				if (blockstate.getMaterial() == net.minecraft.world.level.material.Material.PISTON) {
					{
						AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _iitemhandlerref.set(capability));
						if (_iitemhandlerref.get() != null) {
							for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
								ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
								if (itemstackiterator.getItem() instanceof PickaxeItem) {
									toolCounter = toolCounter + 1;
									if (toolCounter == 1) {
										chosenItem = itemstackiterator;
										if (entity instanceof Player _player) {
											ItemStack _stktoremove = chosenItem;
											_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(),
													(chosenItem).getCount(), _player.inventoryMenu.getCraftSlots());
										}
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = chosenItem;
											_setstack.setCount((chosenItem).getCount());
											_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
										if (entity instanceof Player _player) {
											ItemStack _setstack = oldMainHand;
											_setstack.setCount((oldMainHand).getCount());
											ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
										}
									}
								}
							}
						}
					}
				}
				if (blockstate.getMaterial() == net.minecraft.world.level.material.Material.STONE) {
					{
						AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _iitemhandlerref.set(capability));
						if (_iitemhandlerref.get() != null) {
							for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
								ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
								if (itemstackiterator.getItem().isCorrectToolForDrops(blockstate)) {
									toolCounter = toolCounter + 1;
									if (toolCounter == 1) {
										chosenItem = itemstackiterator;
										if (entity instanceof Player _player) {
											ItemStack _stktoremove = chosenItem;
											_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(),
													(chosenItem).getCount(), _player.inventoryMenu.getCraftSlots());
										}
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = chosenItem;
											_setstack.setCount((chosenItem).getCount());
											_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
										if (entity instanceof Player _player) {
											ItemStack _setstack = oldMainHand;
											_setstack.setCount((oldMainHand).getCount());
											ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
