package uk.co.anttheantster.antsangelring.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import uk.co.anttheantster.antsangelring.AntsAngelRing;
import uk.co.anttheantster.antsangelring.block.ModBlocks;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(AntsAngelRing.MOD_ID);
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

    public static final Supplier<Item> ANGEL_RING = ITEMS.register("angel_ring", AngelRingItem::new);

    public static final Supplier<Item> ANGEL_BLOCK_ITEM = ITEMS.register("angel_block_item",
            () -> new ItemNameBlockItem(ModBlocks.ANGEL_BLOCK.get(), new Item.Properties()));
}
