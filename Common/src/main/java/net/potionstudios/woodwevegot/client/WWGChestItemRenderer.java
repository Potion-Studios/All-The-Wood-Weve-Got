package net.potionstudios.woodwevegot.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class WWGChestItemRenderer<T extends BlockEntity> extends BlockEntityWithoutLevelRenderer {

    private final Supplier<T> chest;
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;

    public WWGChestItemRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet, Supplier<T> chest) {
        super(blockEntityRenderDispatcher, entityModelSet);
        this.chest = chest;
        this.blockEntityRenderDispatcher = blockEntityRenderDispatcher;
    }

    @Override
    public void renderByItem(@NotNull ItemStack stack, @NotNull ItemDisplayContext displayContext, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        this.blockEntityRenderDispatcher.renderItem(chest.get(), poseStack, buffer, packedLight, packedOverlay);
    }
}
