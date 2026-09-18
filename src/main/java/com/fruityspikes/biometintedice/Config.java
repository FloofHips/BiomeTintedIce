package com.fruityspikes.biometintedice;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.DoubleValue COLOR_INTENSITY;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        COLOR_INTENSITY = builder
                .translation("biometintedice.config.color_intensity")
                .comment("How intense the color tinting is.")
                .defineInRange("colorIntensity", 0.5, 0.0, 1.0);

        SPEC = builder.build();
    }
}
