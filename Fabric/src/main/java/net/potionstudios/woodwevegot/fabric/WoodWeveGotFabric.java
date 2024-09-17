package net.potionstudios.woodwevegot.fabric;

import net.potionstudios.biomeswevegone.fabric.BiomesWeveGoneFabric;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.fabricmc.api.ModInitializer;
import net.potionstudios.woodwevegot.fabric.integration.BlockIntegration;

/**
 * This class is the entrypoint for the mod on the Fabric platform.
 */
public class WoodWeveGotFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        BiomesWeveGoneFabric.initializeBiomesWeveGone(WoodWeveGot.MOD_ID);
        WoodWeveGot.init();
        BlockIntegration.init();
    }
}
