package net.potionstudios.woodwevegot.neoforge.datagen.generators;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Generates recipes for the mod.
 * @see RecipeProvider
 * @author Joseph T. McQuigg
 */
public class RecipeGenerator extends RecipeProvider {
    public RecipeGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        WWGWoodSet.getWoodSets().forEach(wwgWoodSet -> {
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, wwgWoodSet.barrel())
                    .define('P', wwgWoodSet.getWoodSet().planks())
                    .define('S', wwgWoodSet.getWoodSet().slab())
                    .pattern("PSP")
                    .pattern("P P")
                    .pattern("PSP")
                    .unlockedBy(getHasName(wwgWoodSet.getWoodSet().planks()), has(wwgWoodSet.getWoodSet().planks()))
                    .unlockedBy(getHasName(wwgWoodSet.getWoodSet().slab()), has(wwgWoodSet.getWoodSet().slab()))
                    .save(recipeOutput);

            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, wwgWoodSet.ladder(), 3)
                    .define('#', Items.STICK)
                    .define('P', wwgWoodSet.getWoodSet().planks())
                    .pattern("# #")
                    .pattern("#P#")
                    .pattern("# #")
                    .unlockedBy("has_stick", has(Items.STICK))
                    .save(recipeOutput);

            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, wwgWoodSet.chest())
                    .define('#', wwgWoodSet.getWoodSet().planks())
                    .pattern("###")
                    .pattern("# #")
                    .pattern("###")
                    .unlockedBy("has_lots_of_items", CriteriaTriggers.INVENTORY_CHANGED.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(), new InventoryChangeTrigger.TriggerInstance.Slots(MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY), List.of())))
                    .save(recipeOutput);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, wwgWoodSet.trappedChest())
                    .requires(wwgWoodSet.chest())
                    .requires(Blocks.TRIPWIRE_HOOK)
                    .unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK))
                    .save(recipeOutput);
        });
    }
}
