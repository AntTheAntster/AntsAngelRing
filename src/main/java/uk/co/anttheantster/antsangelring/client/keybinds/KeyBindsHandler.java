package uk.co.anttheantster.antsangelring.client.keybinds;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import uk.co.anttheantster.antsangelring.util.AngelRingSettings;

public class KeyBindsHandler {

    @SubscribeEvent
    public void clientTick(PlayerTickEvent.Post event) {
        if (KeyBinds.toggleRingKey.consumeClick()){
            AngelRingSettings.isAngelRingEnabled = !AngelRingSettings.isAngelRingEnabled;

            String status = AngelRingSettings.isAngelRingEnabled ? "§aEnabled" : "§cDisabled";
            Player player = Minecraft.getInstance().player;
            player.displayClientMessage(Component.literal("Ring " + status), true);
        }
    }
}
