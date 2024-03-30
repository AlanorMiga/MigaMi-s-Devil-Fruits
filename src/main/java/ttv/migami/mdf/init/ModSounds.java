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

    private static RegistryObject<SoundEvent> register(String key) {
        return REGISTER.register(key, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MOD_ID, key)));
    }
}
