package net.potionstudios.woodwevegot.forge;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
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
    public WoodWeveGotForge(final FMLJavaModLoadingContext context) {
        BusGroup busGroup = context.getModBusGroup();
        WoodWeveGot.init();
        ForgePlatformHandler.register(busGroup);
        if (FMLEnvironment.dist.isClient()) WoodWeveGotClientForge.init(busGroup);
        BuildCreativeModeTabContentsEvent.getBus(busGroup).addListener(WoodWeveGotForge::addToCreativeTab);
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
