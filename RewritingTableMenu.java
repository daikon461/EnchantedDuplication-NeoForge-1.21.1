package com.enchantedduplication.menu;

import com.enchantedduplication.blockentity.RewritingTableBlockEntity;
import com.enchantedduplication.registry.ModMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.SimpleContainer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

public class RewritingTableMenu extends AbstractContainerMenu {
    private final net.minecraft.world.Container container;
    private final ContainerLevelAccess access;
    private final boolean clientDummy;
    private final Player player;

    public RewritingTableMenu(int id, Inventory playerInventory) {
        this(id, playerInventory, new SimpleContainer(RewritingTableBlockEntity.SIZE), ContainerLevelAccess.NULL, true);
    }

    public RewritingTableMenu(int id, Inventory playerInventory, FriendlyByteBuf ignored) {
        this(id, playerInventory);
    }

    public RewritingTableMenu(int id, Inventory playerInventory, RewritingTableBlockEntity blockEntity) {
        this(id, playerInventory, blockEntity, ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), false);
    }

    private RewritingTableMenu(int id, Inventory playerInventory, net.minecraft.world.Container container, ContainerLevelAccess access, boolean clientDummy) {
        super(ModMenus.REWRITING_TABLE.get(), id);
        this.container = container;
        this.access = access;
        this.clientDummy = clientDummy;
        this.player = playerInventory.player;

        this.addSlot(new InputSlot(container, 0, 23, 15, stack -> true));
        this.addSlot(new LapisSlot(container, 1, 41, 51));
        this.addSlot(new BookSlot(container, 2, 113, 15));
        this.addSlot(new OutputSlot(container, 3, 113, 51, this));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    public void refreshOutput(Player player) {
        if (clientDummy) return;
        ItemStack source = container.getItem(0);
        ItemStack lapis = container.getItem(1);
        ItemStack book = container.getItem(2);
        int cost = source.is(Items.ENCHANTED_BOOK) ? 10 : 3;
        boolean validSource = source.is(Items.WRITABLE_BOOK) || source.is(Items.WRITTEN_BOOK) || source.is(Items.ENCHANTED_BOOK);
        boolean valid = validSource && lapis.is(Items.LAPIS_LAZULI) && book.is(Items.BOOK)
                && lapis.getCount() >= 1 && book.getCount() >= 1 && player.experienceLevel >= cost;
        ItemStack result = valid ? source.copyWithCount(1) : ItemStack.EMPTY;
        if (!ItemStack.matches(container.getItem(3), result)) {
            container.setItem(3, result);
            broadcastChanges();
        }
    }

    public int getExperienceCost() {
        return container.getItem(0).is(Items.ENCHANTED_BOOK) ? 10 : 3;
    }

    public boolean canCraft(Player player) {
        ItemStack result = container.getItem(3);
        return !result.isEmpty() && player.experienceLevel >= getExperienceCost();
    }

    @Override
    public void broadcastChanges() {
        if (!clientDummy) {
            Player p = player;
            if (p != null) {
                ItemStack source = container.getItem(0);
                ItemStack lapis = container.getItem(1);
                ItemStack book = container.getItem(2);
                int cost = source.is(Items.ENCHANTED_BOOK) ? 10 : 3;
                boolean valid = (source.is(Items.WRITABLE_BOOK) || source.is(Items.WRITTEN_BOOK) || source.is(Items.ENCHANTED_BOOK))
                        && lapis.is(Items.LAPIS_LAZULI) && book.is(Items.BOOK)
                        && p.experienceLevel >= cost;
                ItemStack result = valid ? source.copyWithCount(1) : ItemStack.EMPTY;
                if (!ItemStack.matches(container.getItem(3), result)) container.setItem(3, result);
            }
        }
        super.broadcastChanges();
    }

    @Override
    public boolean stillValid(Player player) {
        return AbstractContainerMenu.stillValid(access, player, com.enchantedduplication.registry.ModBlocks.REWRITING_TABLE.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack empty = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (!slot.hasItem()) return empty;
        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();
        if (index == 3) {
            if (!moveItemStackTo(stack, 4, slots.size(), true)) return empty;
            slot.onQuickCraft(stack, copy);
            slot.onTake(player, stack);
        } else if (index >= 4) {
            if (stack.is(Items.LAPIS_LAZULI)) {
                if (!moveItemStackTo(stack, 1, 2, false)) return empty;
            } else if (stack.is(Items.BOOK)) {
                if (!moveItemStackTo(stack, 2, 3, false)) return empty;
            } else if (stack.is(Items.WRITABLE_BOOK) || stack.is(Items.WRITTEN_BOOK) || stack.is(Items.ENCHANTED_BOOK)) {
                if (!moveItemStackTo(stack, 0, 1, false)) return empty;
            } else if (!moveItemStackTo(stack, 4, slots.size(), false)) return empty;
        } else {
            if (!moveItemStackTo(stack, 4, slots.size(), false)) return empty;
        }
        if (stack.isEmpty()) slot.setByPlayer(ItemStack.EMPTY); else slot.setChanged();
        return copy;
    }

    private static class InputSlot extends Slot {
        private final java.util.function.Predicate<ItemStack> predicate;
        InputSlot(net.minecraft.world.Container c, int i, int x, int y, java.util.function.Predicate<ItemStack> predicate) { super(c,i,x,y); this.predicate=predicate; }
        @Override public boolean mayPlace(ItemStack stack) { return predicate.test(stack); }
    }
    private static class LapisSlot extends Slot {
        LapisSlot(net.minecraft.world.Container c, int i, int x, int y) { super(c,i,x,y); }
        @Override public boolean mayPlace(ItemStack stack) { return stack.is(Items.LAPIS_LAZULI); }
    }
    private static class BookSlot extends Slot {
        BookSlot(net.minecraft.world.Container c, int i, int x, int y) { super(c,i,x,y); }
        @Override public boolean mayPlace(ItemStack stack) { return stack.is(Items.BOOK); }
    }
    private static class OutputSlot extends Slot {
        private final RewritingTableMenu menu;
        OutputSlot(net.minecraft.world.Container c, int i, int x, int y, RewritingTableMenu menu) { super(c,i,x,y); this.menu=menu; }
        @Override public boolean mayPlace(ItemStack stack) { return false; }
        @Override public void onTake(Player player, ItemStack stack) {
            super.onTake(player, stack);
            if (menu.container.getItem(1).is(Items.LAPIS_LAZULI)) menu.container.removeItem(1, 1);
            if (menu.container.getItem(2).is(Items.BOOK)) menu.container.removeItem(2, 1);
            int cost = menu.getExperienceCost();
            player.giveExperienceLevels(-cost);
            if (player.level() != null) player.level().playSound(null, player.blockPosition(), net.minecraft.sounds.SoundEvents.PLAYER_LEVELUP, net.minecraft.sounds.SoundSource.NEUTRAL, 1.0f, 1.0f);
            menu.container.setItem(3, ItemStack.EMPTY);
            menu.broadcastChanges();
        }
    }
}
