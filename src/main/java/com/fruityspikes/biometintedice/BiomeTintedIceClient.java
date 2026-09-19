package com.fruityspikes.biometintedice;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.awt.*;

import static com.fruityspikes.biometintedice.Config.COLOR_INTENSITY;

@Mod(value = BiomeTintedIce.MODID)
@Mod.EventBusSubscriber(modid = BiomeTintedIce.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BiomeTintedIceClient {
    public static SimplexNoise noise = new SimplexNoise(new XoroshiroRandomSource(67));
    private static float lastIntensity = -2;

    @SubscribeEvent
    public static void onConfigReload(ModConfigEvent.Reloading event) {
        if (event.getConfig().getSpec() != Config.SPEC) return;
        if (FMLEnvironment.dist != Dist.CLIENT) return;

        float current = COLOR_INTENSITY.get().floatValue();
        if (current == lastIntensity) return;
        lastIntensity = current;

        Minecraft.getInstance().execute(() ->
                Minecraft.getInstance().reloadResourcePacks()
        );
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, world, pos, tintIndex) -> {
                    if (world == null || pos == null) return -1;
                    int color = BiomeColors.getAverageWaterColor(world, pos);
                    double intensity = (1 - COLOR_INTENSITY.get());

                    double iceNoise = noise.getValue(pos.getX()*0.045f , pos.getY()*0.045f, pos.getZ()*0.045f);

                    int r = (color & 16711680) >> 16;
                    int g = (color & '\uff00') >> 8;
                    int b = (color & 255);

                    float[] hsb = Color.RGBtoHSB(r, g, b, null);
                    hsb[1]= (float) Mth.clamp(hsb[1]*(0.75*iceNoise+1.25f), 0, 1);
                    Color rgb = Color.getHSBColor(hsb[0], hsb[1],hsb[2]);

                    int newR = (int) Mth.lerp(intensity, rgb.getRed(), 255);
                    int newG = (int) Mth.lerp(intensity, rgb.getGreen(), 255);
                    int newB = (int) Mth.lerp(intensity, rgb.getBlue(), 255);


                    return FastColor.ARGB32.color(64, newR, newG, newB);
                }, Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE
        );
    }
}
