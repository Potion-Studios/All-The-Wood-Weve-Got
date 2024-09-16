package net.potionstudios.woodwevegot.fabric.integration;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.potionstudios.woodwevegot.tags.WWGItemTags;

public class BlockIntegration {

	public static void init() {
		registerFuels();
	}


	private static void registerFuels() {
		FuelRegistry.INSTANCE.add(WWGItemTags.BARRELS, 300);
		FuelRegistry.INSTANCE.add(WWGItemTags.LADDERS, 300);
	}
}
