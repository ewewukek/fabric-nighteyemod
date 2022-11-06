package ewewukek.nighteyemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import ewewukek.nighteyemod.NightEyeMod;
import net.minecraft.client.render.BackgroundRenderer;

// sadly @At does not support multiple ordinal values

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
    @ModifyVariable(
        method = "render(Lnet/minecraft/client/render/Camera;FLnet/minecraft/client/world/ClientWorld;IF)V",
        at = @At(value = "STORE"),
        index = 8
    )
    private static float lightLevel(float x) {
        return x + (1 - x) * NightEyeMod.currentStrength;
    }
}
