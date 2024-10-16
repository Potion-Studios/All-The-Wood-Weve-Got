package net.potionstudios.woodwevegot.neoforge.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class NeoForgeBlockItem extends BlockItem implements IItemExtension {

	private final int burnTime;

	public NeoForgeBlockItem(Supplier<? extends Block> block, Properties properties, int burnTime) {
		super(block.get(), properties);
		this.burnTime = burnTime;
	}

	@Override
	public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
		return burnTime;
	}
}
