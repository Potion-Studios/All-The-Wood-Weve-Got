package net.potionstudios.woodwevegot.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.potionstudios.woodwevegot.WoodWeveGot;

public class WWGItemTags {

    public static final TagKey<Item> BARRELS = create("barrels");
    public static final TagKey<Item> LADDERS = create("ladders");

    private static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM, WoodWeveGot.id(name));
    }

}
