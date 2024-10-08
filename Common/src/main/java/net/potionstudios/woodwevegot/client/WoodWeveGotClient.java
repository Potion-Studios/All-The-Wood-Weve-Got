package net.potionstudios.woodwevegot.client;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.potionstudios.woodwevegot.world.level.block.entity.WWGBlockEntities;

import java.util.function.BiConsumer;

/**
 * Handles the client side of the mod.
 */
public class WoodWeveGotClient {

    /**
     * Registers the block key renderers.
     * @see BlockEntityRenderers
     * @see WWGBlockEntities
     */
    public static void registerBlockEntityRenderers(BiConsumer<BlockEntityType<? extends BlockEntity>, BlockEntityRendererProvider> consumer) {
        consumer.accept(WWGBlockEntities.CHEST.get(), WWGChestRenderer::new);
        consumer.accept(WWGBlockEntities.TRAPPED_CHEST.get(), WWGChestRenderer::new);
    }
}
