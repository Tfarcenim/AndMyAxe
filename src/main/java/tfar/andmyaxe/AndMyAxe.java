package tfar.andmyaxe;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AndMyAxe.MODID)
public class AndMyAxe
{

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "and_my_axe";

    public AndMyAxe() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.addListener(this::onBreakSpeed);
        MinecraftForge.EVENT_BUS.addListener(ModKeyMappings::onKeyInput);
    }

    private void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!ModKeyMappings.ACTIVE) return;
        Player player = event.getEntity();
        BlockState state = event.getState();

        Level level = player.level;
        if (level.isClientSide) {
            if (event.getNewSpeed() <= 1) {
                boolean found = false;
                for (int i = 0; i < 9; i++) {
                    ItemStack potentialTool = player.getInventory().getItem(i);
                    if (potentialTool.getDestroySpeed(state) > 1) {
                        player.getInventory().selected = i;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    NonNullList<ItemStack> items = player.getInventory().items;
                    int change = -1;
                    for (int i = 9; i < items.size(); i++) {
                        ItemStack potentialTool = items.get(i);
                        if (potentialTool.getDestroySpeed(state) > 1) {
                            change = i;
                            break;
                        }
                    }
                    if (change > -1) {
                        InventoryMenu menu = player.inventoryMenu;
                        Minecraft.getInstance().gameMode.handleInventoryMouseClick(menu.containerId, change, player.getInventory().selected, ClickType.SWAP, player);
                    }
                }
            }
        }
    }
}
