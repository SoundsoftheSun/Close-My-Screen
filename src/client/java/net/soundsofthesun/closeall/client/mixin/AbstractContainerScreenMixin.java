package net.soundsofthesun.closeall.client.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Objects;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin {
    @WrapMethod(method = "onMouseClickAction")
    void mod$onMouseClickAction(Slot slot, ClickType type, Operation<Void> original) {
        // Minecraft already checks if you're clicking outside the gui for item throwing, so I'm using that.
        // Throwing items by clicking with them outside the gui still works and does not close the screen.
        if (Objects.isNull(slot) && type == ClickType.THROW) Minecraft.getInstance().setScreen(null);
        original.call(slot, type);
    }
}
