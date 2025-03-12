package net.potionstudios.woodwevegot.world.level.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.potionstudios.woodwevegot.PlatformHandler;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;

import java.util.function.Supplier;
import java.util.stream.Collectors;

public class WWGBlockEntities {

    public static final Supplier<BlockEntityType<WWGBarrelBlockEntity>> BARREL = register("barrel", () -> new BlockEntityType<>(WWGBarrelBlockEntity::new,
            WWGWoodSet.getWoodSets().stream().map(WWGWoodSet::barrel).collect(Collectors.toSet())));

    public static final Supplier<BlockEntityType<WWGChestBlockEntity>> CHEST = register("chest", () -> new BlockEntityType<>(WWGChestBlockEntity::new,
            WWGWoodSet.getWoodSets().stream().map(WWGWoodSet::chest).collect(Collectors.toSet())));

    public static final Supplier<BlockEntityType<WWGTrappedChestBlockEntity>> TRAPPED_CHEST = register("trapped_chest", () -> new BlockEntityType<>(WWGTrappedChestBlockEntity::new,
            WWGWoodSet.getWoodSets().stream().map(WWGWoodSet::trappedChest).collect(Collectors.toSet())));

    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String key, Supplier<BlockEntityType<T>> blockEntity) {
        return PlatformHandler.PLATFORM_HANDLER.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, key, blockEntity);
    }

    public static void blockEntities() {
        WoodWeveGot.LOGGER.info("Registering All The Wood We've Got Block Entities");
    }
}
