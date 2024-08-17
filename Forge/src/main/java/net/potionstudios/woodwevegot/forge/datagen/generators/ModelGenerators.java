package net.potionstudios.woodwevegot.forge.datagen.generators;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.potionstudios.woodwevegot.WoodWeveGot;

public class ModelGenerators {

    public static void init(DataGenerator generator, boolean run, PackOutput output, ExistingFileHelper exFileHelper) {
        generator.addProvider(run, new BlockModelGenerators(output, exFileHelper));
        generator.addProvider(run, new ItemModelGenerators(output, exFileHelper));
    }

    /**
     * Used to generate models for items.
     * @see ItemModelProvider
     */
    private static class ItemModelGenerators extends ItemModelProvider {

        public ItemModelGenerators(PackOutput output, ExistingFileHelper existingFileHelper) {
            super(output, WoodWeveGot.MOD_ID, existingFileHelper);
        }

        @Override
        protected void registerModels() {

        }
    }

    /**
     * Used to generate models for blocks.
     * @see BlockStateProvider
     */
    private static class BlockModelGenerators extends BlockStateProvider {

        public BlockModelGenerators(PackOutput output, ExistingFileHelper exFileHelper) {
            super(output, WoodWeveGot.MOD_ID, exFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {

        }
    }
}
