package ttv.migami.mdf.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ttv.migami.mdf.Reference;
import ttv.migami.mdf.effect.IncurableEffect;
import ttv.migami.mdf.effect.fruit.*;

/**
 * Author: MigaMi
 */
public class ModEffects
{
    public static final DeferredRegister<MobEffect> REGISTER = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Reference.MOD_ID);
    
    public static final RegistryObject<FireworkFruitEffect> FIREWORK_FRUIT = REGISTER.register("firework_fruit", () -> new FireworkFruitEffect(MobEffectCategory.BENEFICIAL, 0));
    public static final RegistryObject<CreeperFruitEffect> CREEPER_FRUIT = REGISTER.register("creeper_fruit", () -> new CreeperFruitEffect(MobEffectCategory.BENEFICIAL, 0));
    public static final RegistryObject<SkeletonFruitEffect> SKELETON_FRUIT = REGISTER.register("skeleton_fruit", () -> new SkeletonFruitEffect(MobEffectCategory.BENEFICIAL, 0));
    public static final RegistryObject<SquidFruitEffect> SQUID_FRUIT = REGISTER.register("squid_fruit", () -> new SquidFruitEffect(MobEffectCategory.BENEFICIAL, 0));
    public static final RegistryObject<BusterFruitEffect> BUSTER_FRUIT = REGISTER.register("buster_fruit", () -> new BusterFruitEffect(MobEffectCategory.BENEFICIAL, 0));
    public static final RegistryObject<FlowerFruitEffect> FLOWER_FRUIT = REGISTER.register("flower_fruit", () -> new FlowerFruitEffect(MobEffectCategory.BENEFICIAL, 0));

    public static final RegistryObject<IncurableEffect> POWER = REGISTER.register("power", () -> new IncurableEffect(MobEffectCategory.BENEFICIAL, 0));
    public static final RegistryObject<IncurableEffect> FEATHER_FALLING = REGISTER.register("feather_falling", () -> new IncurableEffect(MobEffectCategory.BENEFICIAL, 0));

}
