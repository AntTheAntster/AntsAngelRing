package uk.co.anttheantster.antsangelring.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.List;
import java.util.function.Consumer;

public class AngelRingItem extends Item {

    public AngelRingItem() {
        super(new Item.Properties()
                .stacksTo(1)
                .setId(ResourceKey.create(Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath("antsangelring", "angel_ring"))));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltips, TooltipFlag flags) {
        if (flags.hasShiftDown()) {
            tooltips.accept(Component.translatable("item.antsangelring.angel_ring.tooltip").withStyle(ChatFormatting.AQUA));
            return;
        }
        tooltips.accept(Component.translatable("item.antsangelring.angel_ring.tooltip_no_shift").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, context, tooltipDisplay, tooltips, flags);
    }


}
