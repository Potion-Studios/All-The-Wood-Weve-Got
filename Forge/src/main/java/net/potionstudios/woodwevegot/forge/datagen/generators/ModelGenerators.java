package net.potionstudios.woodwevegot.forge.datagen.generators;

import net.minecraft.client.model.Model;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.BarrelBlock;
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
                if (models().existingFileHelper.exists(woodBlockTextureFolder(set.getWoodSet().name(), "barrel_top"), PackType.CLIENT_RESOURCES)) {
                    ModelFile modelFile = models().cubeBottomTop(name(set.barrel()), woodBlockTexture(set.getWoodSet().name(), "barrel_side"), woodBlockTexture(set.getWoodSet().name(), "barrel_bottom"), woodBlockTexture(set.getWoodSet().name(), "barrel_top"));
                    ModelFile open =  models().cubeBottomTop(name(set.barrel()) + "_open", woodBlockTexture(set.getWoodSet().name(), "barrel_side"), woodBlockTexture(set.getWoodSet().name(), "barrel_bottom"), woodBlockTexture(set.getWoodSet().name(), "barrel_top_open"));
                    getVariantBuilder(set.barrel()).forAllStates(blockState -> {
                        ModelFile current = modelFile;
                        if (blockState.getValue(BarrelBlock.OPEN))
                            current = open;
                        return switch (blockState.getValue(BarrelBlock.FACING)) {
                            case DOWN -> ConfiguredModel.builder().modelFile(current).rotationX(180).build();
                            case EAST -> ConfiguredModel.builder().modelFile(current).rotationY(90).rotationX(90).build();
                            case SOUTH -> ConfiguredModel.builder().modelFile(current).rotationX(90).rotationY(180).build();
                            case WEST -> ConfiguredModel.builder().modelFile(current).rotationY(270).rotationX(90).build();
                            default -> ConfiguredModel.builder().modelFile(current).build();
                        };
                    });
                    simpleBlockItem(set.barrel(), modelFile);
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
