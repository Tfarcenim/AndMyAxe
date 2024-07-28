package tfar.andmyaxe;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ModClient {
    //	public static final KeyMapping SWAPPER_STOPPER = new KeyMapping("key.and_my_axe.swapper_stopper", GLFW.GLFW_KEY_LEFT_SHIFT,
    //		"key.categories.gameplay");
    public static final KeyBinding SWAPPER_TOGGLER = new KeyBinding("key.and_my_axe.swapper_toggler", GLFW.GLFW_KEY_H, "key.categories.gameplay");

    @SubscribeEvent
    public static void registerKeyBindings(FMLClientSetupEvent event) {
        //ClientRegistry.registerKeyBinding(SWAPPER_STOPPER);
        ClientRegistry.registerKeyBinding(SWAPPER_TOGGLER);
    }

    public static boolean ACTIVE = true;

    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Minecraft.getInstance().currentScreen == null) {
            if (event.getKey() == SWAPPER_TOGGLER.getKey().getKeyCode()) {
                if (event.getAction() == GLFW.GLFW_PRESS) {
                    ACTIVE = !ACTIVE;
                }
            }
        }
    }
}
