package com.fruityspikes.biometintedice;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.DoubleValue COLOR_INTENSITY;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        COLOR_INTENSITY = builder
                .translation("biometintedice.config.color_intensity")
                .comment("How intense the color tinting is.")
                .defineInRange("colorIntensity", 0.5, 0.0, 1.0);

        SPEC = builder.build();
    }
}
