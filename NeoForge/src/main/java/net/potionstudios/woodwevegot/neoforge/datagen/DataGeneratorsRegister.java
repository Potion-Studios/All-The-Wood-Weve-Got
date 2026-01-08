package net.potionstudios.woodwevegot.neoforge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.potionstudios.woodwevegot.neoforge.datagen.generators.*;
import net.potionstudios.woodwevegot.neoforge.datagen.generators.loot.LootGenerator;

import java.util.concurrent.CompletableFuture;

/**
 * This class is used to register the data generators for the mod.
 * @see GatherDataEvent
 * @author Joseph T. McQuigg
 */
@EventBusSubscriber(modid = WoodWeveGot.MOD_ID)
class DataGeneratorsRegister {

	@SubscribeEvent
	protected static void gatherData(final GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(true, new ModelGenerators(output));
		generator.addProvider(true, new RecipeGenerator.RecipeGeneratorRunner(output, lookupProvider));
		generator.addProvider(true, new LangGenerator(output, "en_us"));
		TagsGenerator.init(generator, true, output, lookupProvider);
		generator.addProvider(true, new LootGenerator(output, lookupProvider));
		generator.addProvider(true, new DatamapGenerator(output, lookupProvider));
	}
}
