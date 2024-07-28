package tfar.andmyaxe;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AndMyAxe.MODID)
public class AndMyAxe {
    public static final Logger LOGGER = LogManager.getLogger(AndMyAxe.class);
    public static final String MODID = "and_my_axe";

    public AndMyAxe() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.addListener(this::onBreakSpeed);
        MinecraftForge.EVENT_BUS.addListener(ModClient::onKeyInput);
    }

    private void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!ModClient.ACTIVE) return;
        PlayerEntity player = event.getPlayer();
        BlockState state = event.getState();

        World level = player.world;
        if (level.isRemote) {
            if (event.getNewSpeed() <= 1) {
                boolean found = false;
                for (int i = 0; i < 9; i++) {
                    ItemStack potentialTool = player.inventory.getStackInSlot(i);
                    if (potentialTool.getDestroySpeed(state) > 1) {
                        player.inventory.currentItem = i;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    NonNullList<ItemStack> items = player.inventory.mainInventory;
                    int change = -1;
                    for (int i = 9; i < items.size(); i++) {
                        ItemStack potentialTool = items.get(i);
                        if (potentialTool.getDestroySpeed(state) > 1) {
                            change = i;
                            break;
                        }
                    }
                    if (change > -1) {
                        PlayerContainer menu = player.container;
                        Minecraft.getInstance().playerController.windowClick(menu.windowId, change, player.inventory.currentItem, ClickType.SWAP, player);
                    }
                }
            }
        }
    }
}