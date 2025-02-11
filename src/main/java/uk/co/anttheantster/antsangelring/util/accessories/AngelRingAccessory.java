package uk.co.anttheantster.antsangelring.util.accessories;

import io.wispforest.accessories.api.Accessory;
import io.wispforest.accessories.api.slot.SlotBasedPredicate;
import io.wispforest.accessories.api.slot.SlotPredicateRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import uk.co.anttheantster.antsangelring.AntsAngelRing;
import uk.co.anttheantster.antsangelring.item.AngelRingItem;

import java.util.function.Predicate;

public class AngelRingAccessory implements Accessory {

    public static void init() {
        Predicate<Item> predicate = item -> item instanceof AngelRingItem;
        SlotPredicateRegistry.register(ResourceLocation.fromNamespaceAndPath(AntsAngelRing.MOD_ID, "angel_ring"),
                SlotBasedPredicate.ofItem(predicate));
    }
}
