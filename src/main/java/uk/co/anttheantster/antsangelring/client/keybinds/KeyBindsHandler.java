package uk.co.anttheantster.antsangelring.client.keybinds;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import uk.co.anttheantster.antsangelring.util.AngelRingSettings;

import static uk.co.anttheantster.antsangelring.client.keybinds.KeyBinds.toggleRingKey;

public class KeyBindsHandler {

    @SubscribeEvent
    public void clientTick(PlayerTickEvent.Post event) {
        if (toggleRingKey.consumeClick()) {
            AngelRingSettings.angelRingToggled = !AngelRingSettings.angelRingToggled;

            String status = AngelRingSettings.angelRingToggled ? "§aEnabled" : "§cDisabled";
            Player player = Minecraft.getInstance().player;
            player.displayClientMessage(Component.literal("§eRing " + status), true);
        }
    }

}
