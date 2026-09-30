package com.enchantedduplication.block;

import com.enchantedduplication.blockentity.RewritingTableBlockEntity;
import com.enchantedduplication.registry.ModBlockEntities;
import com.enchantedduplication.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RewritingTableBlock extends BaseEntityBlock {
    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 19, 16);

    public RewritingTableBlock(Properties properties) {
        super(properties.noOcclusion());
        registerDefaultState(stateDefinition.any().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HorizontalDirectionalBlock.FACING);
    }

    @Override
    public BlockState rotate(BlockState state, net.minecraft.world.level.block.Rotation rotation) {
        return state.setValue(HorizontalDirectionalBlock.FACING, rotation.rotate(state.getValue(HorizontalDirectionalBlock.FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, net.minecraft.world.level.block.Mirror mirror) {
        return rotate(state, mirror.getRotation(state.getValue(HorizontalDirectionalBlock.FACING)));
    }

    @Override
    public VoxelShape getShape(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos, CollisionContext context) { return SHAPE; }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new RewritingTableBlockEntity(pos, state); }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof RewritingTableBlockEntity table) {
            player.openMenu(table);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return null;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) super.onRemove(state, level, pos, newState, movedByPiston);
        else super.onRemove(state, level, pos, newState, movedByPiston);
    }
}
