package uk.co.anttheantster.antsangelring;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import uk.co.anttheantster.antsangelring.client.KeyBindsHandler;
import uk.co.anttheantster.antsangelring.item.ModCreativeTab;
import uk.co.anttheantster.antsangelring.item.ModItems;
import uk.co.anttheantster.antsangelring.util.AngelRingSettings;
import uk.co.anttheantster.antsangelring.util.VersionChecker;

@Mod(AntsAngelRing.MOD_ID)
public class AntsAngelRing {
    public static final String MOD_ID = "antsangelring";
    public static final String version = "1.2.1";

    private static final Logger LOGGER = LogUtils.getLogger();

    private AngelRingSettings angelRingSettings;

    public AntsAngelRing(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        ModItems.register(modEventBus);
        ModCreativeTab.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        //accessoriesLoaded = ModList.get().isLoaded("accessories");

        setup();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            NeoForge.EVENT_BUS.register(new KeyBindsHandler());
        }
    }

    private void setup(){
        angelRingSettings = new AngelRingSettings();

        NeoForge.EVENT_BUS.register(VersionChecker.class);

    }
}
