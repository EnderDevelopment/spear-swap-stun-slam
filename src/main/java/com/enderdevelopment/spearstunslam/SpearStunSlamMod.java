package com.enderdevelopment.spearstunslam;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

public class SpearStunSlamMod implements ModInitializer {
    private static KeyBinding spearSwapKey;

    @Override
    public void onInitialize() {
        spearSwapKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.spearstunslam.spear_swap",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                "category.spearstunslam.spear_stun_slam"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && spearSwapKey.wasPressed()) {
                swapSpear(client.player);
            }
        });
    }

    private void swapSpear(PlayerEntity player) {
                if (player.getMainHandStack().getItem() == Items.TRIDENT) {
            player.setStackInHand(Hand.MAIN_HAND, player.getOffHandStack());
            player.setStackInHand(Hand.OFF_HAND, player.getMainHandStack().getItem().getDefaultStack());
        }
    }

    private void stunSlam(PlayerEntity player) {
                if (player.getTarget() != null && player.getTarget().isHolding(Items.SHIELD)) {
            player.getTarget().addVelocity(0, 1, 0);
            player.getTarget().setVelocity(0, 1, 0);
        }
    }
}