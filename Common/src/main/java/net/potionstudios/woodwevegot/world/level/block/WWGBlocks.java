package net.potionstudios.woodwevegot.world.level.block;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWoodSet;
import net.potionstudios.woodwevegot.PlatformHandler;
import net.potionstudios.woodwevegot.WoodWeveGot;

import java.util.ArrayList;
import java.util.function.Supplier;

public class WWGBlocks {

    public static final ArrayList<Supplier<? extends Block>> BLOCKS = new ArrayList<>();

    protected static <B extends Block> Supplier<B> registerChestBlockItem(String key, Supplier<B> blockSupplier) {
        Supplier<B> block = registerBlock(key, blockSupplier);
        registerItem(key, PlatformHandler.PLATFORM_HANDLER.createChestBlockItem(block));
        return block;
    }

    protected static <B extends Block> Supplier<B> registerBlockItem(String key, Supplier<B> blockSupplier, int burnTime) {
        Supplier<B> block = registerBlock(key, blockSupplier);
        registerItem(key, PlatformHandler.PLATFORM_HANDLER.createBlockItem(block, burnTime));
        return block;
    }

    private static <B extends Block> Supplier<B> registerBlock(String id, Supplier<B> block) {
        Supplier<B> blockSupplier = register(id, block);
        BLOCKS.add(blockSupplier);
        return blockSupplier;
    }

    private static <B extends Block> Supplier<B> register(String id, Supplier<B> block) {
        return PlatformHandler.PLATFORM_HANDLER.register(BuiltInRegistries.BLOCK, id, block);
    }

    private static <I extends Item> void registerItem(String id, Supplier<I> item) {
        PlatformHandler.PLATFORM_HANDLER.register(BuiltInRegistries.ITEM, id, item);
    }

    public static void blocks() {
        WoodWeveGot.LOGGER.info("Registering All The Wood We've Got Blocks");
        if (PlatformHandler.PLATFORM_HANDLER.isModLoaded(BiomesWeveGone.MOD_ID)) {
            BWGWood.wood();
            BWGWoodSet.woodsets().forEach(bwgWoodSet -> new WWGWoodSet(() -> bwgWoodSet));
        }
    }
}
