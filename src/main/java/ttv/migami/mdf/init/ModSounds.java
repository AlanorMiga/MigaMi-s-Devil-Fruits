package ttv.migami.mdf.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ttv.migami.mdf.Reference;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Reference.MOD_ID);

    public static final RegistryObject<SoundEvent> GASTER_BLASTER_PRIME = register("fruit.skeleton.gaster_blaster_prime");
    public static final RegistryObject<SoundEvent> GASTER_BLASTER_SHOOT = register("fruit.skeleton.gaster_blaster_shoot");
    public static final RegistryObject<SoundEvent> BLINK = register("fruit.skeleton.blink");
    public static final RegistryObject<SoundEvent> BONE_THROW = register("fruit.skeleton.bone_throw");
    public static final RegistryObject<SoundEvent> BONE_ZONE = register("fruit.skeleton.bone_zone");
    public static final RegistryObject<SoundEvent> SQUID_SPLAT = register("fruit.squid.splat");
    public static final RegistryObject<SoundEvent> SQUID_EXPLOSION = register("fruit.squid.explosion");
    public static final RegistryObject<SoundEvent> BUSTER_PIANO = register("fruit.buster.piano");
    public static final RegistryObject<SoundEvent> BUSTER_FIRE = register("fruit.buster.fire");
    public static final RegistryObject<SoundEvent> BUSTER_FIRE_LASSO = register("fruit.buster.lasso");
    public static final RegistryObject<SoundEvent> BUSTER_FIRE_CACTUS_GROW = register("fruit.buster.cactus_grow");
    public static final RegistryObject<SoundEvent> FLOWER_WHIP = register("fruit.flower.whip");
    public static final RegistryObject<SoundEvent> FLOWER_RUSTLE = register("fruit.flower.rustle");
    public static final RegistryObject<SoundEvent> PIRANHA_PLANT_BITE = register("fruit.flower.bite");

    private static RegistryObject<SoundEvent> register(String key) {
        return REGISTER.register(key, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MOD_ID, key)));
    }
}
