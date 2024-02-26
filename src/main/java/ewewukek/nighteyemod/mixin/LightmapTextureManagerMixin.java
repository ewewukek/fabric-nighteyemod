package ewewukek.nighteyemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import ewewukek.nighteyemod.NightEyeClientMod;
import net.minecraft.client.render.LightmapTextureManager;

// apply to all 'fstore 8' instructions
// because they all belong to same variable

@Mixin(LightmapTextureManager.class)
public class LightmapTextureManagerMixin {
    @ModifyVariable(
        method = "update(F)V",
        at = @At(value = "STORE"),
        index = 8
    )
    private float lightLevel(float x) {
        return x + (1 - x) * NightEyeClientMod.currentStrength;
    }
}
