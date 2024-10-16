package net.potionstudios.woodwevegot.neoforge.client;


import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.potionstudios.woodwevegot.client.WoodWeveGotClient;
import net.potionstudios.woodwevegot.world.level.block.WWGTrappedChestBlock;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;
import net.potionstudios.woodwevegot.world.level.block.entity.WWGChestBlockEntity;
import net.potionstudios.woodwevegot.world.level.block.entity.WWGTrappedChestBlockEntity;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

@Mod(value = WoodWeveGot.MOD_ID, dist = Dist.CLIENT)
public class WoodWeveGotClientNeoForge {

    /**
     * Constructor for the client side of the NeoForge mod.
     * @param eventBus The event bus to register the client side of the mod to.
     */
    public WoodWeveGotClientNeoForge(IEventBus eventBus) {
        eventBus.addListener((Consumer<EntityRenderersEvent.RegisterRenderers>) event -> WoodWeveGotClient.registerBlockEntityRenderers(event::registerBlockEntityRenderer));
        eventBus.addListener(WoodWeveGotClientNeoForge::registerClientExtensions);
    }

    private static void registerClientExtensions(final RegisterClientExtensionsEvent event) {
        WWGWoodSet.getWoodSets().forEach(set -> {
            event.registerItem(makeItemExtension(set.chest()), set.chest().asItem());
            event.registerItem(makeItemExtension(set.trappedChest()), set.trappedChest().asItem());
        });
    }

    private static IClientItemExtensions makeItemExtension(Block block) {
        return new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new BlockEntityWithoutLevelRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels()) {
                    @Override
                    public void renderByItem(@NotNull ItemStack stack, @NotNull ItemDisplayContext displayContext, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
                        if (block instanceof WWGTrappedChestBlock)
                            Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new WWGTrappedChestBlockEntity(BlockPos.ZERO, block.defaultBlockState()), poseStack, buffer, packedLight, packedOverlay);
                        else Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new WWGChestBlockEntity(BlockPos.ZERO, block.defaultBlockState()), poseStack, buffer, packedLight, packedOverlay);
                    }
                };
            }
        };
    }
}
