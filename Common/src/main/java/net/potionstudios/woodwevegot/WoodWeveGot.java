package net.potionstudios.woodwevegot;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.potionstudios.woodwevegot.world.item.WWGItems;
import net.potionstudios.woodwevegot.world.level.block.WWGBlocks;
import net.potionstudios.woodwevegot.world.level.block.entity.WWGBlockEntities;
import org.slf4j.Logger;

public class WoodWeveGot {

    /** The mod id for All The Wood We've Got. */
    public static final String MOD_ID = "woodwevegot";

    /** The logger for All The Wood We've Got. */
    public static final Logger LOGGER = LogUtils.getLogger();

    /**
     * Initializes the mod.
     */
    public static void init() {
        WWGItems.items();
        WWGBlocks.blocks();
        WWGBlockEntities.blockEntities();
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}
