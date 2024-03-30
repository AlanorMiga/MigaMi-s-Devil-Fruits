package ttv.migami.mdf.world.loot;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ttv.migami.mdf.Reference;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Reference.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> FIREWORK_FRUIT_SHIPWRECK_TREASURE =
            LOOT_MODIFIER_SERIALIZERS.register("firework_fruit_on_shipwreck_treasure", FireworkFruitOnShipwreckTreasureModifier.CODEC);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> CREEPER_FRUIT_SIMPLE_DUNGEON =
            LOOT_MODIFIER_SERIALIZERS.register("creeper_fruit_on_simple_dungeon", CreeperFruitOnSimpleDungeonModifier.CODEC);


    public static void register(IEventBus bus) {
        LOOT_MODIFIER_SERIALIZERS.register(bus);
    }
}