package net.potionstudios.woodwevegot.world.level.block.entities;

import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.potionstudios.woodwevegot.PlatformHandler;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;

import java.util.function.Supplier;

public class WWGBlockEntities {

    public static final Supplier<BlockEntityType<WWGBarrelBlockEntity>> BARREL = register("barrel", () -> BlockEntityType.Builder.of(WWGBarrelBlockEntity::new,
            WWGWoodSet.getWoodSets().stream().map(WWGWoodSet::barrel).toArray(BarrelBlock[]::new)));

    public static final Supplier<BlockEntityType<WWGChestBlockEntity>> CHEST = register("chest", () -> BlockEntityType.Builder.of(WWGChestBlockEntity::new,
            WWGWoodSet.getWoodSets().stream().map(WWGWoodSet::chest).toArray(ChestBlock[]::new)));

    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String key, Supplier<BlockEntityType.Builder<T>> builder) {
        return PlatformHandler.PLATFORM_HANDLER.registerBlockEntity(key, builder);
    }

    public static void blockEntities() {
        WoodWeveGot.LOGGER.info("Registering All The Wood We've Got Block Entities");
    }
}
