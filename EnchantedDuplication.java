package com.enchantedduplication;

import com.enchantedduplication.registry.ModBlocks;
import com.enchantedduplication.registry.ModBlockEntities;
import com.enchantedduplication.registry.ModMenus;
import com.enchantedduplication.registry.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(EnchantedDuplication.MOD_ID)
public final class EnchantedDuplication {
    public static final String MOD_ID = "enchanted_duplication";

    public EnchantedDuplication(IEventBus modBus) {
        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modBus);
        ModMenus.MENUS.register(modBus);
    }
}
