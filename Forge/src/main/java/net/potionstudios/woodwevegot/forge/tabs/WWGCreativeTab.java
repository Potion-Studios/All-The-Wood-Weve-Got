package net.potionstudios.woodwevegot.forge.tabs;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.potionstudios.biomeswevegone.world.item.BWGCreativeTabs;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;

public class WWGCreativeTab {

    public static void addToCreativeTab(final BuildCreativeModeTabContentsEvent event) {
        // Add items to the creative tab
        if (event.getTabKey() == BWGCreativeTabs.WOOD_TAB)
            WWGWoodSet.getWoodSets().forEach((wwgWoodSet -> {
                event.getEntries().putAfter(wwgWoodSet.getWoodSet().chestBoatItem().get().getDefaultInstance(), wwgWoodSet.ladder().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                event.getEntries().putAfter(wwgWoodSet.ladder().asItem().getDefaultInstance(), wwgWoodSet.barrel().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                event.getEntries().putAfter(wwgWoodSet.barrel().asItem().getDefaultInstance(), wwgWoodSet.chest().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                event.getEntries().putAfter(wwgWoodSet.chest().asItem().getDefaultInstance(), wwgWoodSet.trappedChest().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }));
    }

}
