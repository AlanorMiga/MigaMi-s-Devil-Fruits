package ttv.migami.mdf.item.fruit;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import ttv.migami.mdf.init.ModEffects;
import ttv.migami.mdf.item.FruitItem;

public class FireworkFruitItem extends FruitItem {

    public FireworkFruitItem(Properties properties) {
        super(properties
                .food(new FoodProperties.Builder()
                        .nutrition(4)
                        .saturationMod(0.3F)
                        .alwaysEat()
                        .effect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0), 1.0F)
                        .effect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 0), 1.0F)
                        .effect(new MobEffectInstance(ModEffects.FIREWORK_FRUIT.get(), 360000, 0, false, false), 1.0F)
                        .build()
        ));
    }

}
