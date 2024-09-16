package net.potionstudios.woodwevegot.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.potionstudios.woodwevegot.world.level.block.WWGChestBlock;
import net.potionstudios.woodwevegot.world.level.block.entities.WWGChestBlockEntity;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class WWGChestRenderer extends ChestRenderer<WWGChestBlockEntity> {
	private final ModelPart lid;
	private final ModelPart bottom;
	private final ModelPart lock;
	private final ModelPart doubleLeftLid;
	private final ModelPart doubleLeftBottom;
	private final ModelPart doubleLeftLock;
	private final ModelPart doubleRightLid;
	private final ModelPart doubleRightBottom;
	private final ModelPart doubleRightLock;

	public WWGChestRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
		ModelPart modelPart = context.bakeLayer(ModelLayers.CHEST);
		this.bottom = modelPart.getChild("bottom");
		this.lid = modelPart.getChild("lid");
		this.lock = modelPart.getChild("lock");
		ModelPart modelPart2 = context.bakeLayer(ModelLayers.DOUBLE_CHEST_LEFT);
		this.doubleLeftBottom = modelPart2.getChild("bottom");
		this.doubleLeftLid = modelPart2.getChild("lid");
		this.doubleLeftLock = modelPart2.getChild("lock");
		ModelPart modelPart3 = context.bakeLayer(ModelLayers.DOUBLE_CHEST_RIGHT);
		this.doubleRightBottom = modelPart3.getChild("bottom");
		this.doubleRightLid = modelPart3.getChild("lid");
		this.doubleRightLock = modelPart3.getChild("lock");
	}

	@Override
	public void render(@NotNull WWGChestBlockEntity blockEntity, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
		Level level = blockEntity.getLevel();
		boolean bl = level != null;
		BlockState blockState = bl ? blockEntity.getBlockState() : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
		ChestType chestType = blockState.hasProperty(ChestBlock.TYPE) ? blockState.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
		Block block = blockState.getBlock();
		if (block instanceof ChestBlock moreChestBlock) {
			boolean bl2 = chestType != ChestType.SINGLE;
			poseStack.pushPose();
			float g = blockState.getValue(ChestBlock.FACING).toYRot();
			poseStack.translate(0.5F, 0.5F, 0.5F);
			poseStack.mulPose(Axis.YP.rotationDegrees(-g));
			poseStack.translate(-0.5F, -0.5F, -0.5F);
			DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> neighborCombineResult;
			if (bl) {
				neighborCombineResult = moreChestBlock.combine(blockState, level, blockEntity.getBlockPos(), true);
			} else {
				neighborCombineResult = DoubleBlockCombiner.Combiner::acceptNone;
			}
			float h = neighborCombineResult.apply(ChestBlock.opennessCombiner(blockEntity)).get(partialTick);
			h = 1.0f - h;
			h = 1.0f - h * h * h;
			int k = neighborCombineResult.apply(new BrightnessCombiner<>()).applyAsInt(packedLight);
			Material material = getChestMaterial(blockEntity, chestType);
			VertexConsumer vertexConsumer = material.buffer(buffer, RenderType::entityCutout);
			if (bl2) {
				if (chestType == ChestType.LEFT) {
					this.render(poseStack, vertexConsumer, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, h, k, packedOverlay);
				} else {
					this.render(poseStack, vertexConsumer, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, h, k, packedOverlay);
				}
			} else {
				this.render(poseStack, vertexConsumer, this.lid, this.lock, this.bottom, h, k, packedOverlay);
			}
			poseStack.popPose();
		}
	}

	private void render(PoseStack poseStack, VertexConsumer consumer, ModelPart lidPart, ModelPart lockPart, ModelPart bottomPart, float lidAngle, int packedLight, int packedOverlay) {
		lidPart.xRot = -(lidAngle * (float) (Math.PI / 2));
		lockPart.xRot = lidPart.xRot;
		lidPart.render(poseStack, consumer, packedLight, packedOverlay);
		lockPart.render(poseStack, consumer, packedLight, packedOverlay);
		bottomPart.render(poseStack, consumer, packedLight, packedOverlay);
	}

	public static Material chooseMaterial(ChestType type, Material left, Material right, Material single) {
		return switch (type) {
			case LEFT -> left;
			case RIGHT -> right;
			default -> single;
		};
	}

	private Material getChestMaterial(WWGChestBlockEntity blockEntity, ChestType type) {
		String set = ((WWGChestBlock) blockEntity.getBlockState().getBlock()).getSet();
		return chooseMaterial(type, getChestPath(set, "normal_left"),
				getChestPath(set, "normal_right"), getChestPath(set, "normal"));
	}

	public static Material getChestPath(String set, String type) {
		return new Material(Sheets.CHEST_SHEET, WoodWeveGot.id("entity/chest/" + set + "/" + type));
	}
}
