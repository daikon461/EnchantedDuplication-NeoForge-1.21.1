package com.enchantedduplication.blockentity;

import com.enchantedduplication.registry.ModBlockEntities;
import com.enchantedduplication.menu.RewritingTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.Level;

public class RewritingTableBlockEntity extends BaseContainerBlockEntity {
    public static final int SIZE = 4;
    private NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);

    public RewritingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.REWRITING_TABLE.get(), pos, state);
    }

    @Override
    public int getContainerSize() { return SIZE; }

    @Override
    protected NonNullList<ItemStack> getItems() { return items; }

    @Override
    protected void setItems(NonNullList<ItemStack> items) { this.items = items; }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.enchanted_duplication.rewriting_table");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new RewritingTableMenu(containerId, inventory, this);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, RewritingTableBlockEntity be) {
        // Menu handles output calculation while it is open.
    }
}
