package net.potionstudios.woodwevegot.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.potionstudios.woodwevegot.tags.WWGBlockTags;

import java.util.concurrent.CompletableFuture;

/**
 * DataGeneratorEntrypoint for Fabric
 * @see DataGeneratorEntrypoint
 * @author Joseph T. McQuigg
 */
public class FabricDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		FabricTagProvider.BlockTagProvider blockProvider = pack.addProvider(FabricBlockTagGenerator::new);
		pack.addProvider((output, completableFuture) -> new FabricItemTagGenerator(output, completableFuture, blockProvider));
	}

	private static class FabricBlockTagGenerator extends FabricTagProvider.BlockTagProvider {

		public FabricBlockTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
			super(output, registriesFuture);
		}

		@Override
		protected void addTags(HolderLookup.Provider arg) {
			getOrCreateTagBuilder(ConventionalBlockTags.WOODEN_BARRELS).forceAddTag(WWGBlockTags.BARRELS);
			getOrCreateTagBuilder(ConventionalBlockTags.CHESTS).forceAddTag(WWGBlockTags.CHESTS);
		}
	}

	private static class FabricItemTagGenerator extends FabricTagProvider.ItemTagProvider {

		public FabricItemTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture, FabricTagProvider.BlockTagProvider blockTags) {
			super(output, registriesFuture, blockTags);
		}

		@Override
		protected void addTags(HolderLookup.Provider arg) {
			copy(ConventionalBlockTags.WOODEN_BARRELS, ConventionalItemTags.WOODEN_BARRELS);
			copy(ConventionalBlockTags.CHESTS, ConventionalItemTags.CHESTS);
		}
	}
}


