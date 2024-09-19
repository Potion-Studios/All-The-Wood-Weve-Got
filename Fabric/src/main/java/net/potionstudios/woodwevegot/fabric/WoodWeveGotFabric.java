package net.potionstudios.woodwevegot.fabric;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.potionstudios.biomeswevegone.fabric.BiomesWeveGoneFabric;
import net.potionstudios.biomeswevegone.world.item.BWGCreativeTabs;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.fabricmc.api.ModInitializer;
import net.potionstudios.woodwevegot.tags.WWGItemTags;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;

/**
 * This class is the entrypoint for the mod on the Fabric platform.
 */
public class WoodWeveGotFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        BiomesWeveGoneFabric.initializeBiomesWeveGone(WoodWeveGot.MOD_ID);
        WoodWeveGot.init();
        registerFuels();
        ItemGroupEvents.modifyEntriesEvent(BWGCreativeTabs.WOOD_TAB).register((group) -> WWGWoodSet.getWoodSets()
                .forEach((wwgWoodSet -> group.addAfter(wwgWoodSet.getWoodSet().chestBoatItem().get(), wwgWoodSet.ladder(), wwgWoodSet.barrel(), wwgWoodSet.chest(), wwgWoodSet.trappedChest()))));
    }

    private static void registerFuels() {
        FuelRegistry.INSTANCE.add(WWGItemTags.BARRELS, 300);
        FuelRegistry.INSTANCE.add(WWGItemTags.LADDERS, 300);
        FuelRegistry.INSTANCE.add(WWGItemTags.CHESTS, 300);
        FuelRegistry.INSTANCE.add(WWGItemTags.TRAPPED_CHESTS, 300);
    }

}
