package net.potionstudios.woodwevegot.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.object.chest.ChestModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.blockentity.state.ChestRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.potionstudios.woodwevegot.world.level.block.WWGChestBlock;
import net.potionstudios.woodwevegot.world.level.block.WWGTrappedChestBlock;
import net.potionstudios.woodwevegot.world.level.block.entity.WWGChestBlockEntity;
import org.jetbrains.annotations.NotNull;


public class WWGChestRenderer extends ChestRenderer<WWGChestBlockEntity> {
	private final MaterialSet materials;
	private final ChestModel singleModel;
	private final ChestModel doubleLeftModel;
	private final ChestModel doubleRightModel;

	public WWGChestRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
		materials = context.materials();
		singleModel = new ChestModel(context.bakeLayer(ModelLayers.CHEST));
		doubleLeftModel = new ChestModel(context.bakeLayer(ModelLayers.DOUBLE_CHEST_LEFT));
		doubleRightModel = new ChestModel(context.bakeLayer(ModelLayers.DOUBLE_CHEST_RIGHT));
	}

	@Override
	public void submit(@NotNull ChestRenderState chestRenderState, @NotNull PoseStack poseStack, @NotNull SubmitNodeCollector submitNodeCollector, @NotNull CameraRenderState cameraRenderState) {
		poseStack.pushPose();
		poseStack.translate(0.5F, 0.5F, 0.5F);
		poseStack.mulPose(Axis.YP.rotationDegrees(-chestRenderState.angle));
		poseStack.translate(-0.5F, -0.5F, -0.5F);
		float f = chestRenderState.open;
		f = 1.0F - f;
		f = 1.0F - f * f * f;
		Material material = getChestMaterial(chestRenderState);
		RenderType renderType = material.renderType(RenderTypes::entityCutout);
		TextureAtlasSprite textureAtlasSprite = this.materials.get(material);
		if (chestRenderState.type != ChestType.SINGLE) {
			if (chestRenderState.type == ChestType.LEFT) {
				submitNodeCollector.submitModel(
						this.doubleLeftModel,
						f,
						poseStack,
						renderType,
						chestRenderState.lightCoords,
						OverlayTexture.NO_OVERLAY,
						-1,
						textureAtlasSprite,
						0,
						chestRenderState.breakProgress
				);
			} else {
				submitNodeCollector.submitModel(
						this.doubleRightModel,
						f,
						poseStack,
						renderType,
						chestRenderState.lightCoords,
						OverlayTexture.NO_OVERLAY,
						-1,
						textureAtlasSprite,
						0,
						chestRenderState.breakProgress
				);
			}
		} else {
			submitNodeCollector.submitModel(
					this.singleModel,
					f,
					poseStack,
					renderType,
					chestRenderState.lightCoords,
					OverlayTexture.NO_OVERLAY,
					-1,
					textureAtlasSprite,
					0,
					chestRenderState.breakProgress
			);
		}

		poseStack.popPose();
	}

	private static Material chooseMaterial(ChestType type, Material left, Material right, Material single) {
		return switch (type) {
			case LEFT -> left;
			case RIGHT -> right;
			default -> single;
		};
	}

	private Material getChestMaterial(ChestRenderState chestRenderState) {
		WWGChestBlock block = (WWGChestBlock) chestRenderState.blockState.getBlock();
		String set = block.getSet();
		if (block instanceof WWGTrappedChestBlock)
			return chooseMaterial(chestRenderState.type, getChestPath(set, "trapped_left"),
					getChestPath(set, "trapped_right"), getChestPath(set, "trapped"));

		return chooseMaterial(chestRenderState.type, getChestPath(set, "normal_left"),
				getChestPath(set, "normal_right"), getChestPath(set, "normal"));
	}

	private static Material getChestPath(String set, String type) {
		return new Material(Sheets.CHEST_SHEET, WoodWeveGot.id("entity/chest/" + set + "/" + type));
	}
}
