package ewewukek.nighteyemod;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

public class NightEyeClientMod implements ClientModInitializer {
    public static KeyBinding toggleKey;

    public static boolean allowed = false;
    public static boolean warned = false;
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
            while (toggleKey.wasPressed()) {
                if (!allowed) {
                    ClientPlayerEntity player = client.player;
                    if (player != null && !warned) {
                        player.sendMessage(Text.translatable("nighteyemod.multiplayer_warning"));
                        warned = true;
                    }
                    return;
                }
                enabled = !enabled;
                updateStrength();
            }
        });

        ClientPlayConnectionEvents.INIT.register((networkHandler, client) -> {
            allowed = client.isInSingleplayer();
            warned = false;
            enabled = false;
            updateStrength();
        });

        ClientPlayNetworking.registerGlobalReceiver(NightEyeMod.ALLOW_PACKET_ID, (client, handler, buf, responseSender) -> {
            allowed = true;
        });
    }

    public static void updateStrength() {
        currentStrength = enabled ? (0.01f * Config.strength) : 0.0f;
    }
}
