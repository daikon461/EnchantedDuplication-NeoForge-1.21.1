package com.enchantedduplication.registry;

import com.enchantedduplication.EnchantedDuplication;
import com.enchantedduplication.blockentity.RewritingTableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;

public final class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, EnchantedDuplication.MOD_ID);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RewritingTableBlockEntity>> REWRITING_TABLE = BLOCK_ENTITIES.register("rewriting_table", () -> BlockEntityType.Builder.of(RewritingTableBlockEntity::new, ModBlocks.REWRITING_TABLE.get()).build(null));
    private ModBlockEntities() {}
}
