package ttv.migami.mdf.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import ttv.migami.mdf.init.ModEffects;

public abstract class FruitItem extends Item {

    public FruitItem(Properties properties) {
        super(properties);
    }

    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        if (pEntityLiving instanceof Player) {
            Player player = (Player) pEntityLiving;
            player.removeEffect(ModEffects.FIREWORK_FRUIT.get());
            player.removeEffect(ModEffects.CREEPER_FRUIT.get());
            player.removeEffect(ModEffects.SKELETON_FRUIT.get());
        }
        return super.finishUsingItem(pStack, pLevel, pEntityLiving);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 64;
    }

}
