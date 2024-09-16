package net.potionstudios.woodwevegot.world.level.block;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWoodSet;
import net.potionstudios.woodwevegot.PlatformHandler;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.potionstudios.woodwevegot.world.item.WWGItems;

import java.util.ArrayList;
import java.util.function.Supplier;

public class WWGBlocks {

    public static ArrayList<Supplier<? extends Block>> BLOCKS = new ArrayList<>();
    public static ArrayList<Supplier<? extends Item>> BLOCK_ITEMS = new ArrayList<>();

    public static <B extends Block> Supplier<B> registerBlockItem(String key, Supplier<B> blockSupplier, int burnTime) {
        Supplier<B> block = registerBlock(key, blockSupplier);
        Supplier<Item> item = WWGItems.register(key, PlatformHandler.PLATFORM_HANDLER.createBlockItem(block, burnTime));
        BLOCK_ITEMS.add(item);
        return block;
    }

    public static <B extends Block> Supplier<B> registerBlock(String id, Supplier<B> block) {
        Supplier<B> blockSupplier = register(id, block);
        BLOCKS.add(blockSupplier);
        return blockSupplier;
    }

    public static <B extends Block> Supplier<B> register(String id, Supplier<B> block) {
        return PlatformHandler.PLATFORM_HANDLER.register(BuiltInRegistries.BLOCK, id, block);
    }

    public static void blocks() {
        WoodWeveGot.LOGGER.info("Registering All The Wood We've Got Blocks");
        BWGWood.wood();
        BWGWoodSet.woodsets().forEach(bwgWoodSet -> new WWGWoodSet(() -> bwgWoodSet));
    }
}
