package uk.co.anttheantster.antsangelring.event;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.EventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import uk.co.anttheantster.antsangelring.item.ModItems;

public class AngelRingEvent {

    @SubscribeEvent
    public void onAngelRingUse(PlayerTickEvent.Pre event){
        Player player = event.getEntity();
        boolean hasAngelRing = player.getInventory().contains(ModItems.ANGEL_RING.get().getDefaultInstance());

        if (hasAngelRing){
            player.getAbilities().mayfly = true;
        } else {
            if (player.getAbilities().mayfly) {
                player.getAbilities().mayfly = false;
            }
            player.getAbilities().flying = false;
        }
        player.onUpdateAbilities();
    }
}
