package net.potionstudios.woodwevegot.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.minecraftforge.fml.common.Mod;
import net.potionstudios.woodwevegot.forge.client.WoodWeveGotClientForge;
import net.potionstudios.woodwevegot.forge.tabs.WWGCreativeTab;

/**
 * Main class for the mod on the Forge platform.
 */
@Mod(WoodWeveGot.MOD_ID)
public class WoodWeveGotForge {
    public WoodWeveGotForge() {
        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        WoodWeveGot.init();
        ForgePlatformHandler.register(MOD_BUS);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> WoodWeveGotClientForge.init(MOD_BUS));
        MOD_BUS.addListener(WWGCreativeTab::addToCreativeTab);
    }
}
