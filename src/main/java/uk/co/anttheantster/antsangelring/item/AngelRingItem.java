package uk.co.anttheantster.antsangelring.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.List;
import java.util.Optional;

public class AngelRingItem extends Item implements ICurioItem {
    public AngelRingItem() {
        super(new Item.Properties().stacksTo(1));
    }



    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltips, TooltipFlag flags) {

        tooltips.add(Component.translatable("item.antsangelring.angel_ring.tooltip"));
        tooltips.add(Component.translatable("item.antsangelring.angel_ring.tooltip2")
                .withStyle(style -> style.applyFormat(ChatFormatting.AQUA)));

        super.appendHoverText(stack, context, tooltips, flags);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        if (entity instanceof Player player){
            if (player.isCreative() || player.isSpectator()) return;

            ICuriosItemHandler handler = CuriosApi.getCuriosInventory(entity).orElse(null);
            if (handler == null) return;
            SlotResult equipped = handler.findFirstCurio(ModItems.ANGEL_RING.get()).orElse(null);
            if (equipped != null) {
                if (!player.getAbilities().mayfly) {
                    startFlight(player);
                } else if (player.getAbilities().mayfly) {
                    stopFlight(player);
                }
            }
        }
        ICurioItem.super.onEquip(slotContext, prevStack, stack);
    }

    @Override
    public void onEquipFromUse(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);

        ICurioItem.super.onEquipFromUse(slotContext, stack);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {

        if (slotContext.entity() instanceof Player player) {
            stopFlight(player);
        }

        ICurioItem.super.onUnequip(slotContext, newStack, stack);
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
