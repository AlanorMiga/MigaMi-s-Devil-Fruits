package ttv.migami.mdf.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ttv.migami.mdf.Reference;
import ttv.migami.mdf.item.fruit.CreeperFruitItem;
import ttv.migami.mdf.item.fruit.FireworkFruitItem;
import ttv.migami.mdf.item.fruit.SkeletonFruitItem;

public class ModItems {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    /* Items */
    public static final RegistryObject<Item> FIREWORK_FRUIT = REGISTER.register("firework_fruit",
            () -> new FireworkFruitItem(new FireworkFruitItem.Properties()
                    .stacksTo(1)
            ));

    public static final RegistryObject<Item> CREEPER_FRUIT = REGISTER.register("creeper_fruit",
            () -> new CreeperFruitItem(new CreeperFruitItem.Properties()
                    .stacksTo(1)
            ));

    public static final RegistryObject<Item> SKELETON_FRUIT = REGISTER.register("skeleton_fruit",
            () -> new SkeletonFruitItem(new SkeletonFruitItem.Properties()
                    .stacksTo(1)
            ));

}