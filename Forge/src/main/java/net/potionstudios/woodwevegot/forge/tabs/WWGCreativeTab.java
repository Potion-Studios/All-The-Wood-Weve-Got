package net.potionstudios.woodwevegot.forge.tabs;

import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.potionstudios.biomeswevegone.world.item.BWGCreativeTabs;
import net.potionstudios.woodwevegot.world.level.block.WWGBlocks;

public class WWGCreativeTab {

    public static void addToCreativeTab(final BuildCreativeModeTabContentsEvent event) {
        // Add items to the creative tab
        if (event.getTab() == BWGCreativeTabs.WOOD_TAB.get()) {
            WWGBlocks.BLOCK_ITEMS.forEach(event::accept);
        }
    }

}
