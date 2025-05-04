package uk.co.anttheantster.antsangelring;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
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
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import uk.co.anttheantster.antsangelring.curios.AngelRingCurio;
import uk.co.anttheantster.antsangelring.item.AngelRingItem;
import uk.co.anttheantster.antsangelring.item.ModCreativeTab;
import uk.co.anttheantster.antsangelring.item.ModItems;


@Mod(AntsAngelRing.MOD_ID)
public class AntsAngelRing
{

    public static final String MOD_ID = "antsangelring";

    private static final Logger LOGGER = LogUtils.getLogger();

    public AntsAngelRing(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);
        //modEventBus.addListener(this::registerCapabilities);

        ModItems.register(modEventBus);
        ModCreativeTab.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        NeoForge.EVENT_BUS.register(this);
        CuriosApi.registerCurio(ModItems.ANGEL_RING.get(), new AngelRingCurio());
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("Hello {}!", Minecraft.getInstance().getUser().getName());
        }
    }

    /*
    public void registerCapabilities(final RegisterCapabilitiesEvent event){
        event.registerItem(
                CuriosCapability.ITEM,
                (stack, context) -> new ICurio() {
                    @Override
                    public ItemStack getStack() {
                        return stack;
                    }
                },
                ModItems.ANGEL_RING.get());
    }

     */
}
