package net.potionstudios.woodwevegot.neoforge.datagen.generators.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class BlockLootGenerator  extends BlockLootSubProvider {

	private final List<Block> knownBlocks = new ArrayList<>();

	protected BlockLootGenerator(HolderLookup.Provider registries) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
	}

	@Override
	protected void generate() {
		WWGWoodSet.getWoodSets().forEach(wwgWoodSet -> {
			add(wwgWoodSet.barrel(), this::createNameableBlockEntityTable);
			dropSelf(wwgWoodSet.ladder());
			add(wwgWoodSet.chest(), this::createNameableBlockEntityTable);
			add(wwgWoodSet.trappedChest(), this::createNameableBlockEntityTable);
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
