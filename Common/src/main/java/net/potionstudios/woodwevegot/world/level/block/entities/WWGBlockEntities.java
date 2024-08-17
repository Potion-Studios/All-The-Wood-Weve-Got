package net.potionstudios.woodwevegot.world.level.block.entities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.potionstudios.woodwevegot.PlatformHandler;
import net.potionstudios.woodwevegot.WoodWeveGot;

import java.util.function.Supplier;

public class WWGBlockEntities {

    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String key, Supplier<BlockEntityType.Builder<T>> builder) {
        return PlatformHandler.PLATFORM_HANDLER.registerBlockEntity(key, builder);
    }

    public static void blockEntities() {
        WoodWeveGot.LOGGER.info("Registering All The Wood We've Got Block Entities");
    }
}
