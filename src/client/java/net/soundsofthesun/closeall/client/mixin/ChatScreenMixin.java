package net.soundsofthesun.closeall.client.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.soundsofthesun.closeall.client.ScreenCloseButton;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin extends Screen {
    protected ChatScreenMixin(Component title) { super(title); }

    @WrapMethod(method = "init")
    void mod$init(Operation<Void> original) {
        original.call();
        this.addWidget(new ScreenCloseButton());
    }
}
