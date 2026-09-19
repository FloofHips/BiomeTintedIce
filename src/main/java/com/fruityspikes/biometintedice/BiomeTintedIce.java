package com.fruityspikes.biometintedice;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(value = BiomeTintedIce.MODID)
public class BiomeTintedIce {
    public static final String MODID = "biometintedice";

    public BiomeTintedIce() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
    }
}
