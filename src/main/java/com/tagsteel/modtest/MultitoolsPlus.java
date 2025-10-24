package com.tagsteel.modtest;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MultitoolsPlus.MODID)
public class MultitoolsPlus {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "multitoolsplus";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // Registres
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Enregistrement des MultiTools
    public static final RegistryObject<Item> BASIC_MULTITOOL = ITEMS.register("basic_multitool",
        () -> new BasicMultitoolItem(new Item.Properties().durability(MultitoolsConfig.BASIC_MULTITOOL_DURABILITY.get())));

    public static final RegistryObject<Item> ADVANCED_MULTITOOL = ITEMS.register("advanced_multitool",
        () -> new AdvancedMultitoolItem(new Item.Properties().durability(MultitoolsConfig.ADVANCED_MULTITOOL_DURABILITY.get())));

    public static final RegistryObject<Item> ULTIMATE_MULTITOOL = ITEMS.register("ultimate_multitool",
        () -> new UltimateMultitoolItem(new Item.Properties().durability(MultitoolsConfig.ULTIMATE_MULTITOOL_DURABILITY.get()).fireResistant()));

    // Tab créatif
    public static final RegistryObject<CreativeModeTab> MULTITOOLS_TAB = CREATIVE_MODE_TABS.register("multitools_tab",
        () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.TOOLS_AND_UTILITIES)
            .icon(() -> ADVANCED_MULTITOOL.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(BASIC_MULTITOOL.get());
                output.accept(ADVANCED_MULTITOOL.get());
                output.accept(ULTIMATE_MULTITOOL.get());
            }).build());

    public MultitoolsPlus(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new MultiToolEventHandler());

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, MultitoolsConfig.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("MultiTools Plus: Initialisation...");
    }

    // Add the multitools to the tools tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(BASIC_MULTITOOL);
            event.accept(ADVANCED_MULTITOOL);
            event.accept(ULTIMATE_MULTITOOL);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("MultiTools Plus: Serveur démarré");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("MultiTools Plus: Client setup complete");
        }
    }
}
