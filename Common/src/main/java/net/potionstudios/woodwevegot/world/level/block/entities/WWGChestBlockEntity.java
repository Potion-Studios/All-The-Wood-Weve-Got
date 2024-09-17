package net.potionstudios.woodwevegot.world.level.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WWGChestBlockEntity extends ChestBlockEntity {

	protected WWGChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
		super(type, pos, blockState);
	}

	protected WWGChestBlockEntity(BlockPos pos, BlockState blockState) {
		this(WWGBlockEntities.CHEST.get(), pos, blockState);
	}
}
