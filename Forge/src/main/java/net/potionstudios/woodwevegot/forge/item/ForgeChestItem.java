package net.potionstudios.woodwevegot.forge.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.potionstudios.woodwevegot.client.WWGChestItemRenderer;
import net.potionstudios.woodwevegot.world.level.block.entities.WWGChestBlockEntity;
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
				return new WWGChestItemRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels(), () -> new WWGChestBlockEntity(BlockPos.ZERO, block.get().defaultBlockState()));
			}
		});
	}
}
