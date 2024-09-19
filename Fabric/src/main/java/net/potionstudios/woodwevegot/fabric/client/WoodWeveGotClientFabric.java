package net.potionstudios.woodwevegot.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.potionstudios.woodwevegot.client.WoodWeveGotClient;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;
import net.potionstudios.woodwevegot.world.level.block.entity.WWGChestBlockEntity;
import net.potionstudios.woodwevegot.world.level.block.entity.WWGTrappedChestBlockEntity;

@Environment(EnvType.CLIENT)
public class WoodWeveGotClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerRenderTypes();
        WoodWeveGotClient.registerBlockEntityRenderers(BlockEntityRenderers::register);
        registerItemRenderers();
    }

    private static void registerRenderTypes() {
        WWGWoodSet.getWoodSets().forEach(set -> BlockRenderLayerMap.INSTANCE.putBlock(set.ladder(), RenderType.cutout()));
    }

    private static void registerItemRenderers() {
        WWGWoodSet.getWoodSets().forEach(set -> {
            BuiltinItemRendererRegistry.INSTANCE.register(set.chest(), (stack, mode, matrices, vertexConsumers, light, overlay) ->
                    Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new WWGChestBlockEntity(BlockPos.ZERO, set.chest().defaultBlockState()), matrices, vertexConsumers, light, overlay));
            BuiltinItemRendererRegistry.INSTANCE.register(set.trappedChest(), (stack, mode, matrices, vertexConsumers, light, overlay) ->
                    Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new WWGTrappedChestBlockEntity(BlockPos.ZERO, set.trappedChest().defaultBlockState()), matrices, vertexConsumers, light, overlay));
        });
    }
}
