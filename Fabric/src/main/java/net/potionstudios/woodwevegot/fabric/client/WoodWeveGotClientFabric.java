package net.potionstudios.woodwevegot.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.potionstudios.woodwevegot.client.WoodWeveGotClient;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;

@Environment(EnvType.CLIENT)
public class WoodWeveGotClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        WoodWeveGotClient.onInitialize(Minecraft.getInstance());
        registerRenderTypes();
    }

    private static void registerRenderTypes() {
        WWGWoodSet.getWoodSets().forEach(set -> {
            BlockRenderLayerMap.INSTANCE.putBlock(set.ladder(), RenderType.cutout());
        });
    }
}
