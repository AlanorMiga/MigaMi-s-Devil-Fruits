package ttv.migami.mdf.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ttv.migami.mdf.Reference;
import ttv.migami.mdf.item.BlessedFruitItem;
import ttv.migami.mdf.item.FruitItem;

import java.util.List;

public class ModItems {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    public static final RegistryObject<Item> FIREWORK_FRUIT = REGISTER.register("firework_fruit",
            () -> new FruitItem(new Item.Properties()
                    .rarity(Rarity.UNCOMMON)
                    , List.of(
                    new MobEffectInstance(MobEffects.CONFUSION, 200, 0),
                    new MobEffectInstance(MobEffects.WEAKNESS, 200, 0),
                    new MobEffectInstance(ModEffects.FIREWORK_FRUIT.get(), (72000 * 5), 0, false, false)
            ))
    );

    public static final RegistryObject<Item> CREEPER_FRUIT = REGISTER.register("creeper_fruit",
            () -> new FruitItem(new Item.Properties()
                    .rarity(Rarity.COMMON)
                    , List.of(
                    new MobEffectInstance(MobEffects.CONFUSION, 200, 0),
                    new MobEffectInstance(MobEffects.WEAKNESS, 200, 0),
                    new MobEffectInstance(ModEffects.CREEPER_FRUIT.get(), (72000 * 5), 0, false, false)
            ))
    );

    public static final RegistryObject<Item> SKELETON_FRUIT = REGISTER.register("skeleton_fruit",
            () -> new FruitItem(new Item.Properties()
                    .rarity(Rarity.RARE)
                    , List.of(
                    new MobEffectInstance(MobEffects.CONFUSION, 200, 0),
                    new MobEffectInstance(MobEffects.WEAKNESS, 200, 0),
                    new MobEffectInstance(ModEffects.SKELETON_FRUIT.get(), (72000 * 5), 0, false, false)
            ))
    );

    public static final RegistryObject<Item> SQUID_FRUIT = REGISTER.register("squid_fruit",
            () -> new FruitItem(new Item.Properties()
                    .rarity(Rarity.UNCOMMON)
                    , List.of(
                    new MobEffectInstance(MobEffects.CONFUSION, 200, 0),
                    new MobEffectInstance(MobEffects.WEAKNESS, 200, 0),
                    new MobEffectInstance(ModEffects.SQUID_FRUIT.get(), (72000 * 5), 0, false, false)
            ))
    );

    public static final RegistryObject<Item> BUSTER_FRUIT = REGISTER.register("buster_fruit",
            () -> new FruitItem(new Item.Properties()
                    .rarity(Rarity.RARE)
                    , List.of(
                    new MobEffectInstance(MobEffects.CONFUSION, 200, 0),
                    new MobEffectInstance(MobEffects.WEAKNESS, 200, 0),
                    new MobEffectInstance(ModEffects.BUSTER_FRUIT.get(), (72000 * 5), 0, false, false)
            ))
    );

    public static final RegistryObject<Item> FALLEN_FLOWER = REGISTER.register("fallen_flower",
            () -> new FruitItem(new Item.Properties()
                    .rarity(Rarity.RARE)
                    , List.of(
                    new MobEffectInstance(MobEffects.CONFUSION, 200, 0),
                    new MobEffectInstance(MobEffects.WEAKNESS, 200, 0),
                    new MobEffectInstance(ModEffects.FLOWER_FRUIT.get(), (72000 * 5), 0, false, false)
            ))
    );

    public static final RegistryObject<Item> BLESSED_FIREWORK_FRUIT = REGISTER.register("blessed_firework_fruit",
            () -> new BlessedFruitItem(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    , List.of(
                    new MobEffectInstance(MobEffects.CONFUSION, 200, 0),
                    new MobEffectInstance(MobEffects.WEAKNESS, 200, 0),
                    new MobEffectInstance(ModEffects.FIREWORK_FRUIT.get(), (-1), 0, false, false)
            ))
    );

    public static final RegistryObject<Item> BLESSED_CREEPER_FRUIT = REGISTER.register("blessed_creeper_fruit",
            () -> new BlessedFruitItem(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    , List.of(
                    new MobEffectInstance(MobEffects.CONFUSION, 200, 0),
                    new MobEffectInstance(MobEffects.WEAKNESS, 200, 0),
                    new MobEffectInstance(ModEffects.CREEPER_FRUIT.get(), -1, 0, false, false)
            ))
    );

    public static final RegistryObject<Item> BLESSED_SKELETON_FRUIT = REGISTER.register("blessed_skeleton_fruit",
            () -> new BlessedFruitItem(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    , List.of(
                    new MobEffectInstance(MobEffects.CONFUSION, 200, 0),
                    new MobEffectInstance(MobEffects.WEAKNESS, 200, 0),
                    new MobEffectInstance(ModEffects.SKELETON_FRUIT.get(), -1, 0, false, false)
            ))
    );

    public static final RegistryObject<Item> BLESSED_SQUID_FRUIT = REGISTER.register("blessed_squid_fruit",
            () -> new BlessedFruitItem(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    , List.of(
                    new MobEffectInstance(MobEffects.CONFUSION, 200, 0),
                    new MobEffectInstance(MobEffects.WEAKNESS, 200, 0),
                    new MobEffectInstance(ModEffects.SQUID_FRUIT.get(), -1, 0, false, false)
            ))
    );

    public static final RegistryObject<Item> BLESSED_BUSTER_FRUIT = REGISTER.register("blessed_buster_fruit",
            () -> new BlessedFruitItem(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    , List.of(
                    new MobEffectInstance(MobEffects.CONFUSION, 200, 0),
                    new MobEffectInstance(MobEffects.WEAKNESS, 200, 0),
                    new MobEffectInstance(ModEffects.BUSTER_FRUIT.get(), -1, 0, false, false)
            ))
    );

    public static final RegistryObject<Item> BLESSED_FALLEN_FLOWER = REGISTER.register("blessed_fallen_flower",
            () -> new BlessedFruitItem(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    , List.of(
                    new MobEffectInstance(MobEffects.CONFUSION, 200, 0),
                    new MobEffectInstance(MobEffects.WEAKNESS, 200, 0),
                    new MobEffectInstance(ModEffects.FLOWER_FRUIT.get(), (72000 * 5), 0, false, false)
            ))
    );

}