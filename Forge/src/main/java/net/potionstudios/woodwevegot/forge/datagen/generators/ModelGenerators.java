package net.potionstudios.woodwevegot.forge.datagen.generators;

import net.minecraft.client.model.Model;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWoodSet;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;

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
            WWGWoodSet.getWoodSets().forEach(set -> {
                if (models().existingFileHelper.exists(woodBlockTextureFolder(set.getWoodSet().name(), "ladder"), PackType.CLIENT_RESOURCES)) {
                    ModelFile modelFile = models().withExistingParent(name(set.ladder()), "ladder")
                            .texture("texture", woodBlockTexture(set.getWoodSet().name(), "ladder"))
                            .texture("particle", woodBlockTexture(set.getWoodSet().name(), "ladder"))
                            .renderType("cutout");
                    getVariantBuilder(set.ladder()).forAllStatesExcept(blockState -> {
                        if (blockState.getValue(LadderBlock.FACING) == Direction.EAST)
                            return ConfiguredModel.builder().modelFile(modelFile).rotationY(90).build();
                        else if (blockState.getValue(LadderBlock.FACING) == Direction.SOUTH)
                            return ConfiguredModel.builder().modelFile(modelFile).rotationY(180).build();
                        else if (blockState.getValue(LadderBlock.FACING) == Direction.WEST)
                            return ConfiguredModel.builder().modelFile(modelFile).rotationY(270).build();
                        else return ConfiguredModel.builder().modelFile(modelFile).build();
                    }, LadderBlock.WATERLOGGED);
                    simpleBlockItem(set.ladder(), modelFile);
                }
            });
        }

        private ConfiguredModel[] createRotatedModels(ModelFile model) {
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .nextModel().modelFile(model).rotationY(90)
                    .nextModel().modelFile(model).rotationY(180)
                    .nextModel().modelFile(model).rotationY(270)
                    .build();
        }

        private ResourceLocation woodBlockTexture(String type, String name) {
            return WoodWeveGot.id(ModelProvider.BLOCK_FOLDER + "/" + type + "/" + name);
        }

        private ResourceLocation woodBlockTextureFolder(String type, String name) {
            return WoodWeveGot.id("textures/" + ModelProvider.BLOCK_FOLDER + "/" + type + "/" + name + ".png");
        }

        private String name(Block block) {
            return key(block).getPath();
        }

        private ResourceLocation key(Block block) {
            return ForgeRegistries.BLOCKS.getKey(block);
        }

    }
}
