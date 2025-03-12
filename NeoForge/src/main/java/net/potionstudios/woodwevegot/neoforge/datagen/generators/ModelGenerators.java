package net.potionstudios.woodwevegot.neoforge.datagen.generators;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.special.ChestSpecialRenderer;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.potionstudios.woodwevegot.world.level.block.WWGWoodSet;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ModelGenerators extends ModelProvider {
    public ModelGenerators(PackOutput arg) {
        super(arg, WoodWeveGot.MOD_ID);
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels, @NotNull ItemModelGenerators itemModels) {
        WWGWoodSet.getWoodSets().forEach(wwgWoodSet -> {
            createChest(blockModels, itemModels, wwgWoodSet.chest(), wwgWoodSet.name(), "normal");
            createChest(blockModels, itemModels, wwgWoodSet.trappedChest(), wwgWoodSet.name(), "trapped");
            createBarrel(blockModels, wwgWoodSet.barrel(), wwgWoodSet.name());
            createLadder(blockModels, wwgWoodSet.ladder(), wwgWoodSet.name());
        });
    }

    private void createChest(BlockModelGenerators blockModels, ItemModelGenerators itemModels, Block chestBlock, String set, String chestType) {
        ResourceLocation planks = BiomesWeveGone.id("block/" + set + "/planks");
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(chestBlock, ModelTemplates.PARTICLE_ONLY.create(chestBlock, new TextureMapping().put(TextureSlot.PARTICLE, planks), blockModels.modelOutput)));
        Item item = chestBlock.asItem();
        ResourceLocation resourcelocation = ModelTemplates.CHEST_INVENTORY.create(item, TextureMapping.particle(planks), blockModels.modelOutput);
        ItemModel.Unbaked itemmodel$unbaked = ItemModelUtils.specialModel(resourcelocation, new ChestSpecialRenderer.Unbaked(WoodWeveGot.id(set + "/" + chestType)));
        itemModels.itemModelOutput.accept(item, itemmodel$unbaked);
    }

    private void createLadder(BlockModelGenerators blockModels, Block horizontalBlock, String set) {
        ResourceLocation model = new ModelTemplate(Optional.of(mcLocation("block/ladder")), Optional.empty()).extend().renderType(mcLocation("cutout")).build()
                .create(horizontalBlock, new TextureMapping().putForced(TextureSlot.PARTICLE, WoodWeveGot.id("block/" + set + "/ladder")).putForced(TextureSlot.TEXTURE, WoodWeveGot.id("block/" + set + "/ladder")), blockModels.modelOutput);
        blockModels.blockStateOutput
                .accept(
                        MultiVariantGenerator.multiVariant(horizontalBlock, Variant.variant().with(VariantProperties.MODEL, model))
                                .with(BlockModelGenerators.createHorizontalFacingDispatch())
                );
        blockModels.itemModelOutput.accept(horizontalBlock.asItem(), ItemModelUtils.plainModel(ModelTemplates.FLAT_ITEM.create(horizontalBlock.asItem(), TextureMapping.layer0(WoodWeveGot.id("block/" + set + "/ladder")), blockModels.modelOutput)));
    }

    private void createBarrel(BlockModelGenerators blockModels, Block barrel, String set) {
        ResourceLocation resourceLocation = WoodWeveGot.id("block/" + set + "/barrel_top_open");
        ResourceLocation resourceLocation1 = WoodWeveGot.id("block/" + set + "/barrel_bottom");
        ResourceLocation resourceLocation2 = WoodWeveGot.id("block/" + set + "/barrel_side");
        ResourceLocation resourceLocation3 = WoodWeveGot.id("block/" + set + "/barrel_top");
        blockModels.blockStateOutput
                .accept(
                        MultiVariantGenerator.multiVariant(barrel)
                                .with(blockModels.createColumnWithFacing())
                                .with(
                                        PropertyDispatch.property(BlockStateProperties.OPEN)
                                                .select(false, Variant.variant().with(VariantProperties.MODEL, TexturedModel.CUBE_TOP_BOTTOM.updateTexture(textureMapping -> {
                                                    textureMapping.put(TextureSlot.TOP, resourceLocation3);
                                                    textureMapping.put(TextureSlot.BOTTOM, resourceLocation1);
                                                    textureMapping.put(TextureSlot.SIDE, resourceLocation2);
                                                }).create(barrel, blockModels.modelOutput)))
                                                .select(
                                                        true,
                                                        Variant.variant()
                                                                .with(
                                                                        VariantProperties.MODEL,
                                                                        TexturedModel.CUBE_TOP_BOTTOM
                                                                                .get(barrel)
                                                                                .updateTextures(textureMapping -> {
                                                                                    textureMapping.put(TextureSlot.TOP, resourceLocation);
                                                                                    textureMapping.put(TextureSlot.BOTTOM, resourceLocation1);
                                                                                    textureMapping.put(TextureSlot.SIDE, resourceLocation2);
                                                                                })
                                                                                .createWithSuffix(barrel, "_open", blockModels.modelOutput)
                                                                )
                                                )
                                )
                );
    }
}
