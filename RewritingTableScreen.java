package com.enchantedduplication.client;

import com.enchantedduplication.EnchantedDuplication;
import com.enchantedduplication.menu.RewritingTableMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class RewritingTableScreen extends AbstractContainerScreen<RewritingTableMenu> {
    private static final ResourceLocation BG = ResourceLocation.fromNamespaceAndPath(EnchantedDuplication.MOD_ID, "textures/screens/rewritingtable_gui.png");
    private static final ResourceLocation ARROW = ResourceLocation.fromNamespaceAndPath(EnchantedDuplication.MOD_ID, "textures/screens/arrow_gui_rewriting_table.png");
    private static final ResourceLocation ARROW_BLUE = ResourceLocation.fromNamespaceAndPath(EnchantedDuplication.MOD_ID, "textures/screens/arrow_gui_rewriting_table_blue.png");
    private static final ResourceLocation ARROW_FUSION = ResourceLocation.fromNamespaceAndPath(EnchantedDuplication.MOD_ID, "textures/screens/arrow_gui_rewriting_table_fusion.png");
    private static final ResourceLocation ARROW_PURPLE = ResourceLocation.fromNamespaceAndPath(EnchantedDuplication.MOD_ID, "textures/screens/arrow_gui_rewriting_table_purple.png");
    private static final ResourceLocation ARROW_RED = ResourceLocation.fromNamespaceAndPath(EnchantedDuplication.MOD_ID, "textures/screens/arrow_gui_rewriting_table_red.png");

    public RewritingTableScreen(RewritingTableMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int x = leftPos, y = topPos;
        graphics.blit(BG, x, y, 0, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
        graphics.blit(selectArrow(), x + 48, y + 61, 0, 0, 0, 80, 46, 80, 46);
    }

    private ResourceLocation selectArrow() {
        var src = menu.getSlot(0).getItem();
        var lapis = menu.getSlot(1).getItem();
        var book = menu.getSlot(2).getItem();
        if (src.is(net.minecraft.world.item.Items.ENCHANTED_BOOK) && lapis.is(net.minecraft.world.item.Items.LAPIS_LAZULI) && book.is(net.minecraft.world.item.Items.BOOK)) return ARROW_PURPLE;
        if (!src.isEmpty() && lapis.is(net.minecraft.world.item.Items.LAPIS_LAZULI) && book.is(net.minecraft.world.item.Items.BOOK)) return ARROW_BLUE;
        if (!src.isEmpty() && book.is(net.minecraft.world.item.Items.BOOK)) return ARROW_FUSION;
        if (!src.isEmpty()) return ARROW_RED;
        return ARROW;
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        super.renderLabels(graphics, mouseX, mouseY);
        int cost = menu.getExperienceCost();
        graphics.drawString(this.font, Component.translatable(cost == 10 ? "gui.enchanted_duplication.rewritingtable_gui.label_10_experience_levels_required" : "gui.enchanted_duplication.rewritingtable_gui.label_3_experience_levels_required"), 8, 6, 0x404040, false);
    }
}
