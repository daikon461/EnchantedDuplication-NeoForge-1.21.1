package com.enchantedduplication.registry;

import com.enchantedduplication.EnchantedDuplication;
import com.enchantedduplication.block.RewritingTableBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EnchantedDuplication.MOD_ID);
    public static final DeferredBlock<Block> REWRITING_TABLE = BLOCKS.register("rewriting_table", () -> new RewritingTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.5f).sound(net.minecraft.world.level.block.SoundType.WOOD)));
    private ModBlocks() {}
}
