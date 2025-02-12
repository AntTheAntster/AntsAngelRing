package uk.co.anttheantster.antsangelring.event;

import net.minecraft.world.InteractionHand;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import uk.co.anttheantster.antsangelring.AntsAngelRing;
import uk.co.anttheantster.antsangelring.item.ModItems;
import uk.co.anttheantster.antsangelring.util.AngelRingSettings;

@EventBusSubscriber(modid = AntsAngelRing.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ToggleRing extends AngelRingSettings {

    @SubscribeEvent
    public static void onUseEvent(PlayerInteractEvent event){
        if (!event.getEntity().isShiftKeyDown()){
            return;
        }
        if (event.getEntity().getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.ANGEL_RING.get())){
            if (AngelRingSettings.isAngelRingEnabled){
                AngelRingSettings.isAngelRingEnabled = false;
            } else {
                AngelRingSettings.isAngelRingEnabled = true;
            }
            toggleRing();
        }
    }

    public static void toggleRing(){
        if (isAngelRingEnabled){

        }
    }

}
