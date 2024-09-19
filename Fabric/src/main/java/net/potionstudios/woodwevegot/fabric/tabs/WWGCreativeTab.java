package net.potionstudios.woodwevegot.fabric.tabs;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.potionstudios.biomeswevegone.world.item.BWGCreativeTabs;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;

public class WWGCreativeTab {

    public static void addToCreativeTab() {
        // Add items to the creative tab
        ItemGroupEvents.modifyEntriesEvent(BWGCreativeTabs.WOOD_TAB).register((group) -> WWGWoodSet.getWoodSets().forEach((wwgWoodSet -> group.addAfter(wwgWoodSet.getWoodSet().chestBoatItem().get(), wwgWoodSet.ladder(), wwgWoodSet.barrel(), wwgWoodSet.chest(), wwgWoodSet.trappedChest()))));
    }

}
