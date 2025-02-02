package uk.co.anttheantster.antsangelring.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import uk.co.anttheantster.antsangelring.AntsAngelRing;
import uk.co.anttheantster.antsangelring.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AntsAngelRing.MOD_ID);
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }

    public static final Supplier<CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TAB.register("creative_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.antsangelring.tabname"))
                    .icon(() -> new ItemStack(ModItems.ANGEL_RING.get()))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.ANGEL_RING.get().getDefaultInstance());
                        output.accept(ModItems.ANGEL_BLOCK_ITEM.get().getDefaultInstance());

                    }).build());
}
