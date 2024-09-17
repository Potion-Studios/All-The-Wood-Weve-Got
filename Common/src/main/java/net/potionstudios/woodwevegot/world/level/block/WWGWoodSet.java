package net.potionstudios.woodwevegot.world.level.block;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWoodSet;

import java.util.ArrayList;
import java.util.function.Supplier;

public class WWGWoodSet {

	private static final ArrayList<WWGWoodSet> woodSets = new ArrayList<>();

	private final Supplier<BWGWoodSet> woodSet;
	private final Supplier<BarrelBlock> barrel;
	private final Supplier<LadderBlock> ladder;
	private final Supplier<ChestBlock> chest;
	private final Supplier<ChestBlock> trappedChest;

	public WWGWoodSet(Supplier<BWGWoodSet> woodSet) {
		this.woodSet = woodSet;
		this.barrel = WWGBlocks.registerBlockItem(woodSet.get().name() + "_barrel", WWGBarrelBlock::new, 300);
		this.ladder = WWGBlocks.registerBlockItem(woodSet.get().name() + "_ladder", () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)), 300);
		this.chest = WWGBlocks.registerBlockItem(woodSet.get().name() + "_chest", () -> new WWGChestBlock(woodSet.get().name()), 300);
		this.trappedChest = WWGBlocks.registerBlockItem(woodSet.get().name() + "_trapped_chest", () -> new WWGTrappedChestBlock(woodSet.get().name()), 300);
		woodSets.add(this);
	}

	public BWGWoodSet getWoodSet() {
		return woodSet.get();
	}

	public BarrelBlock barrel() {
		return barrel.get();
	}

	public LadderBlock ladder() {
		return ladder.get();
	}

	public ChestBlock chest() {
		return chest.get();
	}

	public ChestBlock trappedChest() {
		return trappedChest.get();
	}

	public String name() {
		return woodSet.get().name();
	}

	public static ArrayList<WWGWoodSet> getWoodSets() {
		return woodSets;
	}
}
