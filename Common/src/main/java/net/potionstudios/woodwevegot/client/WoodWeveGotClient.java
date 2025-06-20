package net.potionstudios.woodwevegot.client;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.potionstudios.woodwevegot.client.renderer.blockentity.WWGChestRenderer;
import net.potionstudios.woodwevegot.world.level.block.entity.WWGBlockEntityType;

import java.util.function.BiConsumer;

/**
 * Handles the client side of the mod.
 */
public class WoodWeveGotClient {

    /**
     * Registers the block key renderers.
     * @see BlockEntityRenderers
     * @see WWGBlockEntityType
     */
    public static void registerBlockEntityRenderers(BiConsumer<BlockEntityType<? extends BlockEntity>, BlockEntityRendererProvider> consumer) {
        consumer.accept(WWGBlockEntityType.CHEST.get(), WWGChestRenderer::new);
        consumer.accept(WWGBlockEntityType.TRAPPED_CHEST.get(), WWGChestRenderer::new);
    }
}
