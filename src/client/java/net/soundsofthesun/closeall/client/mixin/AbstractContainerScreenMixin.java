package net.soundsofthesun.closeall.client.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin {
    @WrapOperation(method = "slotClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;handleInventoryMouseClick(IIILnet/minecraft/world/inventory/ClickType;Lnet/minecraft/world/entity/player/Player;)V"))
    void mod$handleMouseClick(MultiPlayerGameMode instance, int containerId, int slotId, int mouseButton, ClickType clickType, Player player, Operation<Void> original, @Local Slot slot) {
        if (Objects.isNull(slot) && clickType == ClickType.THROW) {
            if (Minecraft.getInstance().player != null) Minecraft.getInstance().player.closeContainer();
        } else {
            original.call(instance, containerId, slotId, mouseButton, clickType, player);
        }
    }
}
