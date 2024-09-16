package net.potionstudios.woodwevegot.forge.datagen.generators;

import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * Generates recipes for the mod.
 * @see RecipeProvider
 * @author Joseph T. McQuigg
 */
public class RecipeGenerator extends RecipeProvider {
    public RecipeGenerator(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
        WWGWoodSet.getWoodSets().forEach(wwgWoodSet -> {
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, wwgWoodSet.barrel())
                    .define('P', wwgWoodSet.getWoodSet().planks())
                    .define('S', wwgWoodSet.getWoodSet().slab())
                    .pattern("PSP")
                    .pattern("P P")
                    .pattern("PSP")
                    .unlockedBy(getHasName(wwgWoodSet.getWoodSet().planks()), has(wwgWoodSet.getWoodSet().planks()))
                    .unlockedBy(getHasName(wwgWoodSet.getWoodSet().slab()), has(wwgWoodSet.getWoodSet().slab()))
                    .save(writer);

            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, wwgWoodSet.ladder(), 3)
                    .define('#', Items.STICK)
                    .pattern("# #")
                    .pattern("###")
                    .pattern("# #")
                    .unlockedBy("has_stick", has(Items.STICK))
                    .save(writer);

            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, wwgWoodSet.chest())
                    .define('#', wwgWoodSet.getWoodSet().planks())
                    .pattern("###")
                    .pattern("# #")
                    .pattern("###")
                    .unlockedBy(
                            "has_lots_of_items",
                            new InventoryChangeTrigger.TriggerInstance(
                                    ContextAwarePredicate.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0]
                            )
                    )
                    .save(writer);
        });
    }
}
