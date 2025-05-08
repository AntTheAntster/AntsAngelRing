package uk.co.anttheantster.antsangelring.event;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import uk.co.anttheantster.antsangelring.item.ModItems;
import uk.co.anttheantster.antsangelring.util.AngelRingSettings;

public class AngelRingEvent {

    @SubscribeEvent
    public void onAngelRingUse(PlayerTickEvent.Post event){

        Player player = event.getEntity();
        if (player.isCreative() || player.isSpectator()) return;

        boolean hasAngelRing = player.getInventory().contains(ModItems.ANGEL_RING.get().getDefaultInstance());


        if (hasAngelRing && AngelRingSettings.isAngelRingToggled) {
            startFlight(player);
            player.onUpdateAbilities();
        } else {
            stopFlight(player);
            player.onUpdateAbilities();
        }
    }

    private static void startFlight(Player player){
        player.getAbilities().mayfly = true;
        player.onUpdateAbilities();
    }
    private static void stopFlight(Player player){
        player.getAbilities().mayfly = false;
        player.getAbilities().flying = false;
        player.onUpdateAbilities();
    }
}
