package net.potionstudios.woodwevegot.world.level.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWoodSet;
import net.potionstudios.woodwevegot.WoodWeveGot;

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
		this.barrel = WWGBlocks.registerBlockItem(woodSet.get().name() + "_barrel", () -> new WWGBarrelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).setId(key(woodSet.get().name() + "_barrel"))), 300);
		this.ladder = WWGBlocks.registerBlockItem(woodSet.get().name() + "_ladder", () -> new LadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LADDER).setId(key(woodSet.get().name() + "_ladder"))), 300);
		this.chest = WWGBlocks.registerBlockItem(woodSet.get().name() + "_chest", () -> new WWGChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST).setId(key(woodSet.get().name() + "_chest")), woodSet.get().name()), 300);
		this.trappedChest = WWGBlocks.registerBlockItem(woodSet.get().name() + "_trapped_chest", () -> new WWGTrappedChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TRAPPED_CHEST).setId(key(woodSet.get().name() + "_trapped_chest")), woodSet.get().name()), 300);
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

	private static ResourceKey<Block> key(String name) {
		return ResourceKey.create(Registries.BLOCK, WoodWeveGot.id(name));
	}
}
