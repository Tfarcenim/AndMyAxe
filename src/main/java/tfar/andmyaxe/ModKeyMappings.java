package tfar.andmyaxe;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ModKeyMappings {
    //	public static final KeyMapping SWAPPER_STOPPER = new KeyMapping("key.and_my_axe.swapper_stopper", GLFW.GLFW_KEY_LEFT_SHIFT,
    //		"key.categories.gameplay");
    public static final KeyMapping SWAPPER_TOGGLER = new KeyMapping("key.and_my_axe.swapper_toggler", GLFW.GLFW_KEY_H, "key.categories.gameplay");

    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        //ClientRegistry.registerKeyBinding(SWAPPER_STOPPER);
        event.register(SWAPPER_TOGGLER);
    }

    public static boolean ACTIVE = true;

    public static void onKeyInput(InputEvent.Key event) {
        if (Minecraft.getInstance().screen == null) {
            if (event.getKey() == SWAPPER_TOGGLER.getKey().getValue()) {
                if (event.getAction() == GLFW.GLFW_PRESS) {
                    ACTIVE = !ACTIVE;
                }
            }
        }
    }
}