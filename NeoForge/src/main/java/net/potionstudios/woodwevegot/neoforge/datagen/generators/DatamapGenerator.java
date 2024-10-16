package net.potionstudios.woodwevegot.neoforge.datagen.generators;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.potionstudios.woodwevegot.tags.WWGItemTags;

import java.util.concurrent.CompletableFuture;

public class DatamapGenerator extends DataMapProvider {
	/**
	 * Create a new provider.
	 *
	 * @param packOutput     the output location
	 * @param lookupProvider a {@linkplain CompletableFuture} supplying the registries
	 */
	public DatamapGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider);
	}

	@Override
	protected void gather() {
		builder(NeoForgeDataMaps.FURNACE_FUELS)
				.add(WWGItemTags.BARRELS, new FurnaceFuel(300), false)
				.add(WWGItemTags.LADDERS, new FurnaceFuel(300), false)
				.add(WWGItemTags.CHESTS, new FurnaceFuel(300), false)
				.add(WWGItemTags.TRAPPED_CHESTS, new FurnaceFuel(300), false);
	}
}
