package uk.co.anttheantster.antsangelring.client;


import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import uk.co.anttheantster.antsangelring.AntsAngelRing;

@EventBusSubscriber(modid = AntsAngelRing.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyBinds {
    public static final KeyBinds INSTANCE = new KeyBinds();

    private KeyBinds() {}

    private static final String CATEGORY = "Ant's Angel Ring";

    public static final KeyMapping toggleRingKey = new KeyMapping(
            "key.antsangelring.toggle", KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_V, -1), CATEGORY
    );

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event){
        event.register(toggleRingKey);
    }


}
