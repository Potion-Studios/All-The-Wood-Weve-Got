package net.potionstudios.woodwevegot.forge.item;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.potionstudios.woodwevegot.world.level.block.WWGTrappedChestBlock;
import net.potionstudios.woodwevegot.world.level.block.entity.WWGChestBlockEntity;
import net.potionstudios.woodwevegot.world.level.block.entity.WWGTrappedChestBlockEntity;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ForgeChestItem extends ForgeBlockItem {

	private final Supplier<? extends Block> block;

	public ForgeChestItem(Supplier<? extends Block> block, Properties properties) {
		super(block, properties, 300);
		this.block = block;
	}

	@Override
	public void initializeClient(@NotNull Consumer<IClientItemExtensions> consumer) {
		super.initializeClient(consumer);
		consumer.accept(new IClientItemExtensions() {
			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return new BlockEntityWithoutLevelRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels()) {
					@Override
					public void renderByItem(@NotNull ItemStack stack, @NotNull ItemDisplayContext displayContext, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
						if (block.get() instanceof WWGTrappedChestBlock)
							Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new WWGTrappedChestBlockEntity(BlockPos.ZERO, block.get().defaultBlockState()), poseStack, buffer, packedLight, packedOverlay);
						else Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new WWGChestBlockEntity(BlockPos.ZERO, block.get().defaultBlockState()), poseStack, buffer, packedLight, packedOverlay);
					}
				};
			}
		});
	}
}
