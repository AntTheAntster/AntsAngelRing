package uk.co.anttheantster.antsangelring.item;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class AngelRingItem extends AccessoryItem {

    public AngelRingItem() {
        super(new Item.Properties()
                .stacksTo(1)
                .setId(ResourceKey.create(Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath("antsangelring", "angel_ring"))));
    }



    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltips, TooltipFlag flags) {

        if (flags.hasShiftDown()){
            tooltips.add(Component.translatable("item.antsangelring.angel_ring.tooltip").withStyle(ChatFormatting.AQUA));
            return;
        }
        tooltips.add(Component.translatable("item.antsangelring.angel_ring.tooltip_no_shift").withStyle(ChatFormatting.GRAY));

        super.appendHoverText(stack, context, tooltips, flags);
    }

    @Override
    public boolean canEquipFromUse(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference reference) {
        return reference.entity() instanceof Player;
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        Player player = (Player) reference.entity();
        if (player.isCreative() || player.isSpectator()) return;

        startFlight(player);

    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        Player player = (Player) reference.entity();
        stopFlight(player);
    }

    private void startFlight(Player player){
        player.getAbilities().mayfly = true;
        player.onUpdateAbilities();
    }
    private void stopFlight(Player player){
        player.getAbilities().mayfly = false;
        player.getAbilities().flying = false;
        player.onUpdateAbilities();
    }
}
