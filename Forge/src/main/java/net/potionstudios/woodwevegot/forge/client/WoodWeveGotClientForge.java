package net.potionstudios.woodwevegot.forge.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.potionstudios.woodwevegot.client.WoodWeveGotClient;

import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class WoodWeveGotClientForge {

    /**
     * Initializes the client side of the Forge mod.
     * @param eventBus The event bus to register the client side of the mod to.
     */
    public static void init(IEventBus eventBus) {
        eventBus.addListener(WoodWeveGotClientForge::forgeClientSetup);
        eventBus.addListener((Consumer<EntityRenderersEvent.RegisterRenderers>) event -> WoodWeveGotClient.registerBlockEntityRenderers(event::registerBlockEntityRenderer));
    }

    /**
     * Sets up the client side of the mod.
     * @param event The event to set up the client side of the mod.
     * @see FMLClientSetupEvent
     */
    private static void forgeClientSetup(final FMLClientSetupEvent event) {
        WoodWeveGotClient.onInitialize(Minecraft.getInstance());
    }
}
