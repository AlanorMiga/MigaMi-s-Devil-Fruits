package ttv.migami.mdf.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import ttv.migami.mdf.init.ModEffects;

import javax.annotation.Nullable;
import java.util.List;

public class FruitItem extends Item {
    private final List<MobEffectInstance> effects;

    public FruitItem(Properties properties, List<MobEffectInstance> effects) {
        super(properties.food(createFoodProperties(effects)));
        this.effects = effects;
    }

    private static FoodProperties createFoodProperties(List<MobEffectInstance> effects) {
        FoodProperties.Builder builder = new FoodProperties.Builder()
                .nutrition(4)
                .saturationMod(0.3F)
                .alwaysEat();

        for (MobEffectInstance effect : effects) {
            builder.effect(() -> effect, 1.0F);
        }

        return builder.build();
    }

    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        if (!level.isClientSide) {
            removeOtherFruitEffects((ServerPlayer) user);
            for (MobEffectInstance effect : effects) {
                user.addEffect(new MobEffectInstance(effect));
            }
        }
        return super.finishUsingItem(stack, level, user);
    }

    private void removeOtherFruitEffects(ServerPlayer player) {
        List<MobEffectInstance> allFruitEffects = List.of(
                new MobEffectInstance(ModEffects.SKELETON_FRUIT.get(), 0),
                new MobEffectInstance(ModEffects.FIREWORK_FRUIT.get(), 0),
                new MobEffectInstance(ModEffects.CREEPER_FRUIT.get(), 0),
                new MobEffectInstance(ModEffects.SQUID_FRUIT.get(), 0),
                new MobEffectInstance(ModEffects.BUSTER_FRUIT.get(), 0),
                new MobEffectInstance(ModEffects.FLOWER_FRUIT.get(), 0)
        );

        for (MobEffectInstance fruitEffect : allFruitEffects) {
            if (player.hasEffect(fruitEffect.getEffect())) {
                player.removeEffect(fruitEffect.getEffect());
            }
        }
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 64;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("info.mdf.fruit_info").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("info.mdf.tooltip_item" + "." + this.asItem()).withStyle(ChatFormatting.GRAY));
    }

}
