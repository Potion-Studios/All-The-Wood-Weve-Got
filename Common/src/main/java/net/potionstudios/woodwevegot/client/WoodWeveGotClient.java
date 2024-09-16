package net.potionstudios.woodwevegot.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.potionstudios.woodwevegot.world.level.block.entities.WWGBlockEntities;

import java.util.function.BiConsumer;

public class WoodWeveGotClient {

    public static void onInitialize(Minecraft minecraft) {

    }

    /**
     * Registers the block key renderers.
     * @see BlockEntityRenderers
     * @see WWGBlockEntities
     */
    public static void registerBlockEntityRenderers(BiConsumer<BlockEntityType<? extends BlockEntity>, BlockEntityRendererProvider> consumer) {
        consumer.accept(WWGBlockEntities.CHEST.get(), WWGChestRenderer::new);
    }

}
