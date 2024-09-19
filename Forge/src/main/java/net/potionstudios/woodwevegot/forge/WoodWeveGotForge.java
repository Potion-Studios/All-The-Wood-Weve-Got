package net.potionstudios.woodwevegot.forge;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.potionstudios.biomeswevegone.world.item.BWGCreativeTabs;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.minecraftforge.fml.common.Mod;
import net.potionstudios.woodwevegot.forge.client.WoodWeveGotClientForge;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;

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
        MOD_BUS.addListener(WoodWeveGotForge::addToCreativeTab);
    }

    private static void addToCreativeTab(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == BWGCreativeTabs.WOOD_TAB)
            WWGWoodSet.getWoodSets().forEach((wwgWoodSet -> {
                event.getEntries().putAfter(wwgWoodSet.getWoodSet().chestBoatItem().get().getDefaultInstance(), wwgWoodSet.ladder().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                event.getEntries().putAfter(wwgWoodSet.ladder().asItem().getDefaultInstance(), wwgWoodSet.barrel().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                event.getEntries().putAfter(wwgWoodSet.barrel().asItem().getDefaultInstance(), wwgWoodSet.chest().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                event.getEntries().putAfter(wwgWoodSet.chest().asItem().getDefaultInstance(), wwgWoodSet.trappedChest().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }));
    }
}
