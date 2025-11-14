package net.potionstudios.woodwevegot.forge.client;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.potionstudios.woodwevegot.client.WoodWeveGotClient;

public class WoodWeveGotClientForge {

    /**
     * Initializes the client side of the Forge mod.
     */
    public static void init() {
        EntityRenderersEvent.RegisterRenderers.BUS.addListener((EntityRenderersEvent.RegisterRenderers event) -> WoodWeveGotClient.registerBlockEntityRenderers(event::registerBlockEntityRenderer));
    }
}
