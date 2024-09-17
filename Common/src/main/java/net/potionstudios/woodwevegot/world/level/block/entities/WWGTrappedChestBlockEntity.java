package net.potionstudios.woodwevegot.world.level.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class WWGTrappedChestBlockEntity extends WWGChestBlockEntity {
    public WWGTrappedChestBlockEntity(BlockPos pos, BlockState blockState) {
        super(WWGBlockEntities.TRAPPED_CHEST.get(), pos, blockState);
    }

    @Override
    protected void signalOpenCount(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, int eventId, int eventParam) {
        super.signalOpenCount(level, pos, state, eventId, eventParam);
        if (eventId != eventParam) {
            Block block = state.getBlock();
            level.updateNeighborsAt(pos, block);
            level.updateNeighborsAt(pos.below(), block);
        }
    }
}
