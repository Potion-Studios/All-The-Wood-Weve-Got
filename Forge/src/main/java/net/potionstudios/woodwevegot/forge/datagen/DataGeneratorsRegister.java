package net.potionstudios.woodwevegot.forge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.potionstudios.woodwevegot.forge.datagen.generators.LangGenerator;
import net.potionstudios.woodwevegot.forge.datagen.generators.ModelGenerators;
import net.potionstudios.woodwevegot.forge.datagen.generators.RecipeGenerator;

import java.util.concurrent.CompletableFuture;

/**
 * This class is used to register the data generators for the mod.
 * @see GatherDataEvent
 * @author Joseph T. McQuigg
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = WoodWeveGot.MOD_ID)
class DataGeneratorsRegister {

	@SubscribeEvent
	protected static void gatherData(final GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		ModelGenerators.init(generator, event.includeClient(), output, existingFileHelper);
		generator.addProvider(event.includeServer(), new RecipeGenerator(output));
		generator.addProvider(event.includeClient(), new LangGenerator(output, "en_us"));
	}

}
