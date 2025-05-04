package uk.co.anttheantster.antsangelring.event;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import uk.co.anttheantster.antsangelring.item.ModItems;

@EventBusSubscriber
public class AngelRingEvent {

    @SubscribeEvent
    public static void onAngelRingUse(PlayerTickEvent.Post event){
        Player player = event.getEntity();
        if (player.isCreative() || player.isSpectator()) return;

        boolean hasAngelRing = player.getInventory().contains(ModItems.ANGEL_RING.get().getDefaultInstance());

        if (hasAngelRing){
            player.getAbilities().mayfly = true;
        } else {
            player.getAbilities().flying = false;
            player.getAbilities().mayfly = false;
        }
        player.onUpdateAbilities();
    }
}
