package net.potionstudios.woodwevegot.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.potionstudios.woodwevegot.WoodWeveGot;

public class WWGBlockTags {

    public static final TagKey<Block> BARRELS = create("barrels");
    public static final TagKey<Block> LADDERS = create("ladders");

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, WoodWeveGot.id(name));
    }
}
