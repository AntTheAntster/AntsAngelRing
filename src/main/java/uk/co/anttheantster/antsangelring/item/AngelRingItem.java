package uk.co.anttheantster.antsangelring.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
public class AngelRingItem extends Item {
    public AngelRingItem() {
        super(new Item.Properties()
                .stacksTo(1)
                .fireResistant()
                .rarity(Rarity.EPIC));
    }



    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltips, TooltipFlag flags) {

        if (flags.hasShiftDown()){
            tooltips.add(Component.translatable("item.antsangelring.angel_ring.tooltip").withStyle(ChatFormatting.AQUA));
            tooltips.add(Component.translatable("item.antsangelring.angel_ring.tooltip2").withStyle(ChatFormatting.GOLD));
        }
        tooltips.add(Component.translatable("item.antsangelring.angel_ring.tooltip_noshift").withStyle(ChatFormatting.GRAY));

        super.appendHoverText(stack, context, tooltips, flags);
    }
}
