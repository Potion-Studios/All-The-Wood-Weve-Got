package net.potionstudios.woodwevegot.neoforge;

import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.potionstudios.biomeswevegone.world.item.BWGCreativeTabs;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;

/**
 * Main class for the mod on the NeoForge platform.
 */
@Mod(WoodWeveGot.MOD_ID)
public class WoodWeveGotNeoForge {
    public WoodWeveGotNeoForge(final IEventBus eventBus) {
        WoodWeveGot.init();
        NeoForgePlatformHandler.register(eventBus);
        eventBus.addListener(WoodWeveGotNeoForge::addToCreativeTab);
    }

    private static void addToCreativeTab(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == BWGCreativeTabs.WOOD_TAB)
            WWGWoodSet.getWoodSets().forEach((wwgWoodSet -> {
                event.insertAfter(wwgWoodSet.getWoodSet().chestBoatItem().get().getDefaultInstance(), wwgWoodSet.ladder().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                event.insertAfter(wwgWoodSet.ladder().asItem().getDefaultInstance(), wwgWoodSet.barrel().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                event.insertAfter(wwgWoodSet.barrel().asItem().getDefaultInstance(), wwgWoodSet.chest().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                event.insertAfter(wwgWoodSet.chest().asItem().getDefaultInstance(), wwgWoodSet.trappedChest().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }));
    }
}
