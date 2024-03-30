package ttv.migami.mdf.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ttv.migami.mdf.Reference;
import ttv.migami.mdf.effect.IncurableEffect;
import ttv.migami.mdf.effect.fruit.CreeperFruitEffect;
import ttv.migami.mdf.effect.fruit.FireworkFruitEffect;
import ttv.migami.mdf.effect.fruit.SkeletonFruitEffect;

/**
 * Author: MigaMi
 */
public class ModEffects
{
    public static final DeferredRegister<MobEffect> REGISTER = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Reference.MOD_ID);
    
    public static final RegistryObject<FireworkFruitEffect> FIREWORK_FRUIT = REGISTER.register("firework_fruit", () -> new FireworkFruitEffect(MobEffectCategory.BENEFICIAL, 0));
    public static final RegistryObject<CreeperFruitEffect> CREEPER_FRUIT = REGISTER.register("creeper_fruit", () -> new CreeperFruitEffect(MobEffectCategory.BENEFICIAL, 0));
    public static final RegistryObject<SkeletonFruitEffect> SKELETON_FRUIT = REGISTER.register("skeleton_fruit", () -> new SkeletonFruitEffect(MobEffectCategory.BENEFICIAL, 0));
    public static final RegistryObject<IncurableEffect> POWERED = REGISTER.register("powered", () -> new IncurableEffect(MobEffectCategory.BENEFICIAL, 0));
}
