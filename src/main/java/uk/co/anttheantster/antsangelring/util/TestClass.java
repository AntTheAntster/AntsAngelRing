package uk.co.anttheantster.antsangelring.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import uk.co.anttheantster.antsangelring.item.ModItems;

public class TestClass {

    @SubscribeEvent
    public void playerJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Player player) {
            ItemStack item = new ItemStack(ModItems.ANGEL_RING.get());
            if (!player.getInventory().contains(item)){
                player.getInventory().add(item);
            }
        }
    }

}
