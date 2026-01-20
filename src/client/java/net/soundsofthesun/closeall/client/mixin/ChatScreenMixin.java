package net.soundsofthesun.closeall.client.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin extends Screen {
    protected ChatScreenMixin(Component title) { super(title); }

    @WrapMethod(method = "init")
    void mod$init(Operation<Void> original) {
        // Creates a button last which closes the window, text box still accessible
        // TODO Make sure modded guis still accessible
        // TODO Investigate side effect: chat box stores draft when .setScreen(null)
        original.call();
        this.addWidget(Button.builder(Component.literal(""), onPress -> {
                            Minecraft.getInstance().setScreen(null);
                        })
                        .bounds(0, 0, 20000, 10000)//This doesn't seem to cause any problems.
                        .build()
        );
    }
}
