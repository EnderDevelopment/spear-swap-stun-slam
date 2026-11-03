package com.enderdevelopment.spearstunslam.mixin;

import com.enderdevelopment.spearstunslam.SpearStunSlamMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class KeyInputHandlerMixin {
    @Inject(method = "handleInputEvents", at = @At("HEAD"))
    private void onHandleInputEvents(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && SpearStunSlamMod.isSpearSwapKeyPressed()) {
            SpearStunSlamMod.swapSpear(client.player);
        }
    }
}