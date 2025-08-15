package net.potionstudios.woodwevegot.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.potionstudios.woodwevegot.client.WoodWeveGotClient;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;

@Environment(EnvType.CLIENT)
public class WoodWeveGotClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerRenderTypes();
        WoodWeveGotClient.registerBlockEntityRenderers(BlockEntityRenderers::register);
    }

    private static void registerRenderTypes() {
        WWGWoodSet.getWoodSets().forEach(set -> BlockRenderLayerMap.putBlock(set.ladder(), ChunkSectionLayer.CUTOUT));
    }
}
