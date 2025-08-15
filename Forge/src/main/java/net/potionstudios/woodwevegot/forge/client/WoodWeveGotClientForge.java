package net.potionstudios.woodwevegot.forge.client;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.potionstudios.woodwevegot.client.WoodWeveGotClient;

public class WoodWeveGotClientForge {

    /**
     * Initializes the client side of the Forge mod.
     * @param busGroup The bus group to register event listeners to.
     */
    public static void init(final BusGroup busGroup) {
        EntityRenderersEvent.RegisterRenderers.getBus(busGroup).addListener((EntityRenderersEvent.RegisterRenderers event) -> WoodWeveGotClient.registerBlockEntityRenderers(event::registerBlockEntityRenderer));
    }
}
