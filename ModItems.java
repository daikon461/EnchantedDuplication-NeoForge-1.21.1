package com.enchantedduplication.registry;

import com.enchantedduplication.EnchantedDuplication;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EnchantedDuplication.MOD_ID);
    public static final DeferredItem<BlockItem> REWRITING_TABLE = ITEMS.register("rewriting_table", () -> new BlockItem(ModBlocks.REWRITING_TABLE.get(), new Item.Properties()));
    private ModItems() {}
}
