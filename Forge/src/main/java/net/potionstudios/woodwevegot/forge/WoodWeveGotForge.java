package net.potionstudios.woodwevegot.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.minecraftforge.fml.common.Mod;
import net.potionstudios.woodwevegot.forge.client.WoodWeveGotClientForge;

/**
 * Main class for the mod on the Forge platform.
 */
@Mod(WoodWeveGot.MOD_ID)
public class WoodWeveGotForge {
    public WoodWeveGotForge() {
        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus EVENT_BUS = MinecraftForge.EVENT_BUS;
        WoodWeveGot.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> WoodWeveGotClientForge.init(MOD_BUS));
    }
}
