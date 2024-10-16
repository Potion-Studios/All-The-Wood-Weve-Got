package net.potionstudios.woodwevegot.neoforge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.potionstudios.woodwevegot.neoforge.datagen.generators.LangGenerator;
import net.potionstudios.woodwevegot.neoforge.datagen.generators.ModelGenerators;
import net.potionstudios.woodwevegot.neoforge.datagen.generators.RecipeGenerator;
import net.potionstudios.woodwevegot.neoforge.datagen.generators.TagsGenerator;
import net.potionstudios.woodwevegot.neoforge.datagen.generators.loot.LootGenerator;

import java.util.concurrent.CompletableFuture;

/**
 * This class is used to register the data generators for the mod.
 * @see GatherDataEvent
 * @author Joseph T. McQuigg
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = WoodWeveGot.MOD_ID)
class DataGeneratorsRegister {

	@SubscribeEvent
	protected static void gatherData(final GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		ModelGenerators.init(generator, event.includeClient(), output, existingFileHelper);
		generator.addProvider(event.includeServer(), new RecipeGenerator(output, lookupProvider));
		generator.addProvider(event.includeClient(), new LangGenerator(output, "en_us"));
		TagsGenerator.init(generator, event.includeServer(), output, lookupProvider, existingFileHelper);
		generator.addProvider(event.includeServer(), new LootGenerator(output, lookupProvider));
	}
}
