package ttv.migami.mdf.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class BlessedFruitItem extends FruitItem {
    public BlessedFruitItem(Properties properties, List<MobEffectInstance> effects) {
        super(properties, effects);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        return super.finishUsingItem(stack, level, user);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("info.mdf.fruit_info").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("info.mdf.blessed_fruit_info").withStyle(ChatFormatting.YELLOW));
    }

}
