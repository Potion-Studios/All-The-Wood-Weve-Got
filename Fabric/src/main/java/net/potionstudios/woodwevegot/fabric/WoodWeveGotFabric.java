package net.potionstudios.woodwevegot.fabric;

import net.potionstudios.woodwevegot.WoodWeveGot;
import net.fabricmc.api.ModInitializer;

/**
 * This class is the entrypoint for the mod on the Fabric platform.
 */
public class WoodWeveGotFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        WoodWeveGot.init();
    }
}
