package net.potionstudios.woodwevegot.neoforge.datagen.generators.loot;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class LootGenerator extends LootTableProvider {
    public LootGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Collections.emptySet(), ImmutableList.of(
                new SubProviderEntry(BlockLootGenerator::new, LootContextParamSets.BLOCK)
        ), registries);
    }
}
