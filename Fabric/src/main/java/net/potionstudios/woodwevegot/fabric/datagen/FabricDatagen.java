package net.potionstudios.woodwevegot.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

/**
 * DataGeneratorEntrypoint for Fabric
 * @see DataGeneratorEntrypoint
 * @author Joseph T. McQuigg
 */
public class FabricDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
	}
}
