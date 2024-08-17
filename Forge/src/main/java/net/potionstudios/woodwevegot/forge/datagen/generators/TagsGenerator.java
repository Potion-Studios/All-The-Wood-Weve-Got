package net.potionstudios.woodwevegot.forge.datagen.generators;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.potionstudios.woodwevegot.WoodWeveGot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * Used to generate tags for blocks and items.
 * @author Joseph T. McQuigg
 */
public class TagsGenerator {

    public static void init(DataGenerator generator, boolean run, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper helper) {
        BlockTagGenerator BlockTags = generator.addProvider(run, new BlockTagGenerator(output, lookupProvider, helper));
        generator.addProvider(run, new ItemTagGenerator(output, lookupProvider, BlockTags, helper));
    }

    /**
     * Used to generate tags for blocks.
     * @see BlockTagsProvider
     */
    private static class BlockTagGenerator extends BlockTagsProvider {
        private BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, WoodWeveGot.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider provider) {

        }
    }

    /**
     * Used to generate tags for items.
     * @see ItemTagsProvider
     */
    private static class ItemTagGenerator extends ItemTagsProvider {

        private ItemTagGenerator(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, BlockTagGenerator blockTagGenerator, @Nullable ExistingFileHelper existingFileHelper) {
            super(arg, completableFuture, blockTagGenerator.contentsGetter(), WoodWeveGot.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider provider) {

        }
    }
}
