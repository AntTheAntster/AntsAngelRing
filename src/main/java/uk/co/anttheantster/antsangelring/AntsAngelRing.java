package uk.co.anttheantster.antsangelring;

import io.wispforest.accessories.api.Accessory;
import io.wispforest.accessories.api.AccessoryRegistry;
import io.wispforest.accessories.api.slot.SlotBasedPredicate;
import io.wispforest.accessories.api.slot.SlotPredicateRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.fml.ModList;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
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
import uk.co.anttheantster.antsangelring.item.AngelRingItem;
import uk.co.anttheantster.antsangelring.item.ModCreativeTab;
import uk.co.anttheantster.antsangelring.item.ModItems;
import uk.co.anttheantster.antsangelring.util.accessories.AngelRingAccessory;

import java.util.function.Predicate;

@Mod(AntsAngelRing.MOD_ID)
public class AntsAngelRing
{
    public boolean accessoriesLoaded;

    public static final String MOD_ID = "antsangelring";

    private static final Logger LOGGER = LogUtils.getLogger();

    public AntsAngelRing(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        ModItems.register(modEventBus);
        ModCreativeTab.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        accessoriesLoaded = ModList.get().isLoaded("accessories");

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        if (accessoriesLoaded) {
            AngelRingAccessory.init();
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("Hello {}!", Minecraft.getInstance().getUser().getName());

        }
    }
}
