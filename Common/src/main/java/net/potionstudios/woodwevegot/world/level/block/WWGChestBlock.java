package net.potionstudios.woodwevegot.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.potionstudios.woodwevegot.world.level.block.entity.WWGBlockEntities;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class WWGChestBlock extends ChestBlock {

	private final String set;

	protected WWGChestBlock(Supplier<BlockEntityType<? extends ChestBlockEntity>> blockEntityType, String set) {
		super(Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.5F).sound(SoundType.WOOD).ignitedByLava(), blockEntityType);
		this.set = set;
	}
	protected WWGChestBlock(String set) {
		this(() -> WWGBlockEntities.CHEST.get(), set);
	}

	public String getSet() {
		return set;
	}

	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return blockEntityType.get().create(pos, state);
	}
}
