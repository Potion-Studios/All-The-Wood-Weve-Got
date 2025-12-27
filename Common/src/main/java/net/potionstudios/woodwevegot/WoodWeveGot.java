package net.potionstudios.woodwevegot;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.Identifier;
import net.potionstudios.woodwevegot.world.level.block.WWGBlocks;
import net.potionstudios.woodwevegot.world.level.block.entity.WWGBlockEntityType;
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
        WWGBlocks.blocks();
        WWGBlockEntityType.blockEntities();
    }

    public static Identifier id(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }
}
