package ewewukek.nighteyemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import ewewukek.nighteyemod.NightEyeClientMod;
import net.minecraft.client.render.BackgroundRenderer;

// sadly @At does not support multiple ordinal values

// have to specify exact ordinals
// because not all 'fstore 9' instructions
// belong to targeted variable

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
    @ModifyVariable(
        method = "render(Lnet/minecraft/client/render/Camera;FLnet/minecraft/client/world/ClientWorld;IF)V",
        at = @At(value = "STORE", ordinal = 1),
        index = 9
    )
    private static float getUnderwaterVisibility(float x) {
        return x + (1 - x) * NightEyeClientMod.currentStrength;
    }

    @ModifyVariable(
        method = "render(Lnet/minecraft/client/render/Camera;FLnet/minecraft/client/world/ClientWorld;IF)V",
        at = @At(value = "STORE", ordinal = 3),
        index = 9
    )
    private static float getNightVisionStrength(float x) {
        return x + (1 - x) * NightEyeClientMod.currentStrength;
    }

    @ModifyVariable(
        method = "render(Lnet/minecraft/client/render/Camera;FLnet/minecraft/client/world/ClientWorld;IF)V",
        at = @At(value = "STORE", ordinal = 4),
        index = 9
    )
    private static float noEffect(float x) {
        return x + (1 - x) * NightEyeClientMod.currentStrength;
    }
}
