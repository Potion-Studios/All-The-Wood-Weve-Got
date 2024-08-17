package net.potionstudios.woodwevegot.forge.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.potionstudios.woodwevegot.WoodWeveGot;

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

    }
}
