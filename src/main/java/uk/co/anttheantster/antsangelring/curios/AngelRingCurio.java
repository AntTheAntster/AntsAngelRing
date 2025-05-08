package uk.co.anttheantster.antsangelring.curios;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import uk.co.anttheantster.antsangelring.item.ModItems;
import uk.co.anttheantster.antsangelring.util.AngelRingSettings;

public class AngelRingCurio implements ICurioItem {


    @Override
    public void onEquipFromUse(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);

        ICurioItem.super.onEquipFromUse(slotContext, stack);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        if (!(entity instanceof Player player)) {
            return;
        }

        if (player.isCreative() || player.isSpectator()) return;

        if (AngelRingSettings.angelRingToggled){
            startFlight(player);
        } else {
            stopFlight(player);
        }
    }

    private void startFlight(Player player){
        if (AngelRingSettings.angelRingToggled) {
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
    }
    private void stopFlight(Player player){
        player.getAbilities().mayfly = false;
        player.getAbilities().flying = false;
        player.onUpdateAbilities();
    }

}
