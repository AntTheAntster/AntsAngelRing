package uk.co.anttheantster.antsangelring.client;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import uk.co.anttheantster.antsangelring.util.AngelRingSettings;

import static uk.co.anttheantster.antsangelring.client.KeyBinds.toggleRingKey;

public class KeyBindsHandler {

    @SubscribeEvent
    public void clientTick(PlayerTickEvent.Post event){
        if (toggleRingKey.consumeClick()) {
            AngelRingSettings.isAngelRingToggled = !AngelRingSettings.isAngelRingToggled;

            String status = AngelRingSettings.isAngelRingToggled ? "§aEnabled" : "§cDisabled";
            Player player = Minecraft.getInstance().player;
            player.displayClientMessage(Component.literal("§eRing " + status), true);
        }
    }

}
