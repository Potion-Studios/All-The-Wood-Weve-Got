package net.potionstudios.woodwevegot.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.potionstudios.woodwevegot.world.level.block.entity.WWGBarrelBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WWGBarrelBlock extends BarrelBlock {
	public WWGBarrelBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return new WWGBarrelBlockEntity(pos, state);
	}
}
