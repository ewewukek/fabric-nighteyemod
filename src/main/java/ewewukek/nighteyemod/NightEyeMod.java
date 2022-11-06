package ewewukek.nighteyemod;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

public class NightEyeMod implements ClientModInitializer {
    public static KeyBinding toggleKey;

    public static boolean enabled = false;
    public static float currentStrength = 0.0f;

    @Override
    public void onInitializeClient() {
        Config.path = FabricLoader.getInstance().getConfigDir().resolve("nighteyemod.txt");
        Config.load();

        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.nighteyemod.toggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "key.categories.misc"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!client.isInSingleplayer()) {
                if (enabled) {
                    enabled = false;
                    updateStrength();
                }
                return;
            }
            while (toggleKey.wasPressed()) {
                if (!client.isInSingleplayer()) {
                    ClientPlayerEntity player = client.player;
                    if (player != null) {
                        player.sendMessage(Text.translatable("nighteyemod.multiplayer_warning"));
                    }
                }
                enabled = !enabled;
                updateStrength();
            }
        });
    }

    public static void updateStrength() {
        currentStrength = enabled ? (0.01f * Config.strength) : 0.0f;
    }
}
