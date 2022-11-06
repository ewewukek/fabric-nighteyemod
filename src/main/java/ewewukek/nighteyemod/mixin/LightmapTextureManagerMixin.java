package ewewukek.nighteyemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import ewewukek.nighteyemod.NightEyeMod;
import net.minecraft.client.render.LightmapTextureManager;

@Mixin(LightmapTextureManager.class)
public class LightmapTextureManagerMixin {
    @ModifyVariable(
        method = "update(F)V",
        at = @At(value = "STORE"),
        index = 5
    )
    private float lightLevel(float x) {
        return x + (1 - x) * NightEyeMod.currentStrength;
    }
}
