package net.soundsofthesun.closeall.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

public class ScreenCloseButton extends AbstractWidget {

    public ScreenCloseButton() {
        super(0, 0, 20000, 10000, Component.empty());
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean isDoubleClick) {
        if (this.isActive() && this.isValidClickButton(event.buttonInfo()) && this.isMouseOver(event.x(), event.y())) {
            this.onClick(event, isDoubleClick);
            return true;
        }
        return false;
    }

    @Override
    public void onClick(MouseButtonEvent event, boolean isDoubleClick) {
        if (Minecraft.getInstance().screen != null) Minecraft.getInstance().screen.onClose();
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {}

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {}
}
