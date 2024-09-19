package net.potionstudios.woodwevegot.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class WWGBarrelBlockEntity extends BarrelBlockEntity {
	public WWGBarrelBlockEntity(BlockPos pos, BlockState blockState) {
		super(pos, blockState);
	}

	@Override
	public @NotNull BlockEntityType<?> getType() {
		return WWGBlockEntities.BARREL.get();
	}
}
