package net.potionstudios.woodwevegot.forge.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.extensions.IForgeItem;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ForgeBlockItem extends BlockItem implements IForgeItem {

	private final int burnTime;

	public ForgeBlockItem(Supplier<? extends Block> block, Properties properties, int burnTime) {
		super(block.get(), properties);
		this.burnTime = burnTime;
	}

	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
		return burnTime;
	}
}
