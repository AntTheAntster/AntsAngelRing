package uk.co.anttheantster.antsangelring.block;

import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import uk.co.anttheantster.antsangelring.AntsAngelRing;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(AntsAngelRing.MOD_ID);
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

    public static final Supplier<Block> ANGEL_BLOCK = BLOCKS.register("angel_block",
            () -> new Block(Block.Properties.of().strength(5.0f)));
}
