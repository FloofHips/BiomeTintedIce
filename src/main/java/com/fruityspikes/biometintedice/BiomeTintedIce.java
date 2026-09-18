package com.fruityspikes.biometintedice;

import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(value = BiomeTintedIce.MODID, dist = Dist.CLIENT)
public class BiomeTintedIce {
    public static final String MODID = "biometintedice";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BiomeTintedIce(IEventBus modEventBus, ModContainer modContainer) {
        //modEventBus.addListener(this::commonSetup);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
