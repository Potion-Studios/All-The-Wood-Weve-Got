package net.potionstudios.woodwevegot.world.level.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WWGChestBlockEntity extends ChestBlockEntity {
	protected WWGChestBlockEntity(BlockPos pos, BlockState blockState) {
		super(WWGBlockEntities.CHEST.get(), pos, blockState);
	}
}
