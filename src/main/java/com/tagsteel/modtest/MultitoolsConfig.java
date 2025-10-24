package com.tagsteel.modtest;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = MultitoolsPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MultitoolsConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    // Durabilité des outils
    public static final ForgeConfigSpec.IntValue BASIC_MULTITOOL_DURABILITY = BUILDER
            .comment("Durabilité du Basic MultiTool")
            .defineInRange("basic_multitool_durability", 750, 100, 5000);

    public static final ForgeConfigSpec.IntValue ADVANCED_MULTITOOL_DURABILITY = BUILDER
            .comment("Durabilité de l'Advanced MultiTool")
            .defineInRange("advanced_multitool_durability", 1500, 100, 10000);

    public static final ForgeConfigSpec.IntValue ULTIMATE_MULTITOOL_DURABILITY = BUILDER
            .comment("Durabilité de l'Ultimate MultiTool")
            .defineInRange("ultimate_multitool_durability", 3000, 100, 20000);

    // Options de gameplay
    public static final ForgeConfigSpec.BooleanValue ENABLE_AUTO_SMELT = BUILDER
            .comment("Activer la fonction auto-smelt de l'Ultimate MultiTool")
            .define("enable_auto_smelt", true);

    public static final ForgeConfigSpec.DoubleValue AUTO_SMELT_CHANCE = BUILDER
            .comment("Chance d'activation de l'auto-smelt (0.0 - 1.0)")
            .defineInRange("auto_smelt_chance", 0.75, 0.0, 1.0);

    public static final ForgeConfigSpec.IntValue ORE_DETECTOR_RADIUS = BUILDER
            .comment("Rayon de détection des minerais de l'Ultimate MultiTool")
            .defineInRange("ore_detector_radius", 5, 1, 15);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int basicMultitoolDurability;
    public static int advancedMultitoolDurability;
    public static int ultimateMultitoolDurability;
    public static boolean enableAutoSmelt;
    public static double autoSmeltChance;
    public static int oreDetectorRadius;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        basicMultitoolDurability = BASIC_MULTITOOL_DURABILITY.get();
        advancedMultitoolDurability = ADVANCED_MULTITOOL_DURABILITY.get();
        ultimateMultitoolDurability = ULTIMATE_MULTITOOL_DURABILITY.get();
        enableAutoSmelt = ENABLE_AUTO_SMELT.get();
        autoSmeltChance = AUTO_SMELT_CHANCE.get();
        oreDetectorRadius = ORE_DETECTOR_RADIUS.get();
    }
}
