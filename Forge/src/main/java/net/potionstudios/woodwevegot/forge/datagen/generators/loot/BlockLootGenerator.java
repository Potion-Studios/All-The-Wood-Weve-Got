package net.potionstudios.woodwevegot.forge.datagen.generators.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BlockLootGenerator  extends BlockLootSubProvider {

	private final List<Block> knownBlocks = new ArrayList<>();

	protected BlockLootGenerator() {
		super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
	}

	@Override
	protected void generate() {
		WWGWoodSet.getWoodSets().forEach(wwgWoodSet -> {
			add(wwgWoodSet.barrel(), this::createNameableBlockEntityTable);
			dropSelf(wwgWoodSet.ladder());
			add(wwgWoodSet.chest(), this::createNameableBlockEntityTable);
		});
	}

	@Override
	protected void add(@NotNull Block block, LootTable.@NotNull Builder builder) {
		knownBlocks.add(block);
		super.add(block, builder);
	}

	@Override
	protected @NotNull Iterable<Block> getKnownBlocks() {
		return knownBlocks;
	}
}
