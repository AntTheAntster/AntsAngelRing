package uk.co.anttheantster.antsangelring;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import top.theillusivec4.curios.api.CuriosApi;
import uk.co.anttheantster.antsangelring.client.keybinds.KeyBindsHandler;
import uk.co.anttheantster.antsangelring.curios.AngelRingCurio;
import uk.co.anttheantster.antsangelring.item.ModCreativeTab;
import uk.co.anttheantster.antsangelring.item.ModItems;
import uk.co.anttheantster.antsangelring.util.AngelRingSettings;
import uk.co.anttheantster.antsangelring.util.TestClass;
import uk.co.anttheantster.antsangelring.util.VersionChecker;


@Mod(AntsAngelRing.MOD_ID)
public class AntsAngelRing {

    public static final String MOD_ID = "antsangelring";
    public static final String version = "1.2.1.2";

    public static AngelRingSettings angelRingSettings;

    public AntsAngelRing(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ModItems.register(modEventBus);
        ModCreativeTab.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        //NeoForge.EVENT_BUS.register(new TestClass());

        setup();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        NeoForge.EVENT_BUS.register(this);
        CuriosApi.registerCurio(ModItems.ANGEL_RING.get(), new AngelRingCurio());
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            NeoForge.EVENT_BUS.register(new KeyBindsHandler());

            NeoForge.EVENT_BUS.register(VersionChecker.class);
        }
    }

    private void setup() {
        angelRingSettings = new AngelRingSettings();
    }
}
