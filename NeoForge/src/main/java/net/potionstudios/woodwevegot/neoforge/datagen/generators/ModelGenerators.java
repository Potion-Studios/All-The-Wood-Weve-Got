package net.potionstudios.woodwevegot.neoforge.datagen.generators;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.special.ChestSpecialRenderer;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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
        Identifier planks = BiomesWeveGone.id("block/" + set + "/planks");
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(chestBlock, BlockModelGenerators.plainVariant(ModelTemplates.PARTICLE_ONLY.create(chestBlock, new TextureMapping().put(TextureSlot.PARTICLE, planks), blockModels.modelOutput))));
        Item item = chestBlock.asItem();
        Identifier Identifier = ModelTemplates.CHEST_INVENTORY.create(item, TextureMapping.particle(planks), blockModels.modelOutput);
        ItemModel.Unbaked itemmodel$unbaked = ItemModelUtils.specialModel(Identifier, new ChestSpecialRenderer.Unbaked(WoodWeveGot.id(set + "/" + chestType)));
        itemModels.itemModelOutput.accept(item, itemmodel$unbaked);
    }

    private void createLadder(BlockModelGenerators blockModels, Block horizontalBlock, String set) {
        Identifier model = new ModelTemplate(Optional.of(mcLocation("block/ladder")), Optional.empty()).extend().renderType(mcLocation("cutout")).build()
                .create(horizontalBlock, new TextureMapping().putForced(TextureSlot.PARTICLE, WoodWeveGot.id("block/" + set + "/ladder")).putForced(TextureSlot.TEXTURE, WoodWeveGot.id("block/" + set + "/ladder")), blockModels.modelOutput);
        blockModels.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(horizontalBlock, BlockModelGenerators.plainVariant(model))
                                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
                );
        blockModels.itemModelOutput.accept(horizontalBlock.asItem(), ItemModelUtils.plainModel(ModelTemplates.FLAT_ITEM.create(horizontalBlock.asItem(), TextureMapping.layer0(WoodWeveGot.id("block/" + set + "/ladder")), blockModels.modelOutput)));
    }

    private void createBarrel(BlockModelGenerators blockModels, Block barrel, String set) {
        Identifier Identifier = WoodWeveGot.id("block/" + set + "/barrel_top_open");
        Identifier Identifier1 = WoodWeveGot.id("block/" + set + "/barrel_bottom");
        Identifier Identifier2 = WoodWeveGot.id("block/" + set + "/barrel_side");
        Identifier Identifier3 = WoodWeveGot.id("block/" + set + "/barrel_top");
        blockModels.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(barrel)
                                .with(
                                        PropertyDispatch.initial(BlockStateProperties.OPEN)
                                                .select(false, BlockModelGenerators.plainVariant(TexturedModel.CUBE_TOP_BOTTOM.updateTexture(textureMapping -> {
                                                    textureMapping.put(TextureSlot.TOP, Identifier3);
                                                    textureMapping.put(TextureSlot.BOTTOM, Identifier1);
                                                    textureMapping.put(TextureSlot.SIDE, Identifier2);
                                                }).create(barrel, blockModels.modelOutput)))
                                                .select(
                                                        true,
                                                        BlockModelGenerators.plainVariant(
                                                                        TexturedModel.CUBE_TOP_BOTTOM
                                                                                .get(barrel)
                                                                                .updateTextures(textureMapping -> {
                                                                                    textureMapping.put(TextureSlot.TOP, Identifier);
                                                                                    textureMapping.put(TextureSlot.BOTTOM, Identifier1);
                                                                                    textureMapping.put(TextureSlot.SIDE, Identifier2);
                                                                                })
                                                                                .createWithSuffix(barrel, "_open", blockModels.modelOutput)
                                                                )
                                                )
                                ).with(BlockModelGenerators.ROTATIONS_COLUMN_WITH_FACING)
                );
    }
}
