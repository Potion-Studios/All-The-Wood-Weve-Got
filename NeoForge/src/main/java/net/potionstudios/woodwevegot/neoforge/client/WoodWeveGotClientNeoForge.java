package net.potionstudios.woodwevegot.neoforge.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.potionstudios.woodwevegot.client.WoodWeveGotClient;

@Mod(value = WoodWeveGot.MOD_ID, dist = Dist.CLIENT)
public class WoodWeveGotClientNeoForge {

    /**
     * Constructor for the client side of the NeoForge mod.
     * @param eventBus The event bus to register the client side of the mod to.
     */
    public WoodWeveGotClientNeoForge(final IEventBus eventBus) {
        eventBus.addListener((EntityRenderersEvent.RegisterRenderers event) -> WoodWeveGotClient.registerBlockEntityRenderers(event::registerBlockEntityRenderer));
    }
}
