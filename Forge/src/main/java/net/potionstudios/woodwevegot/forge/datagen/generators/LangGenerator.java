package net.potionstudios.woodwevegot.forge.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.potionstudios.woodwevegot.WoodWeveGot;
import net.potionstudios.woodwevegot.world.level.block.WWGBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * This class is used to generate the language file for the mod.
 * @see LanguageProvider
 * @author Joseph T. McQuigg
 */
public class LangGenerator extends LanguageProvider {
    public LangGenerator(PackOutput output, String locale) {
        super(output, WoodWeveGot.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        WWGBlocks.BLOCKS.forEach(block -> add(block.get(), getBlockName(block)));
    }

    private String getBlockName(Supplier<? extends Block> item) {
        return getId((ForgeRegistries.BLOCKS.getKey(item.get()).getPath()));
    }

    @NotNull
    private String getId(String name) {
        name = name.substring(name.indexOf(":") + 1);  //Removes Mod Tag from front of name
        name = name.replace('_', ' ');
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        for (int i = 0; i < name.length(); i++)
            if (name.charAt(i) == ' ')
                name = name.substring(0, i + 1) + name.substring(i + 1, i + 2).toUpperCase() + name.substring(i + 2);
        return name;
    }
}
