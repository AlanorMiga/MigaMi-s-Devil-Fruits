package ttv.migami.mdf.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ttv.migami.mdf.Reference;
import ttv.migami.mdf.entity.fruit.skeleton.BoneZone;
import ttv.migami.mdf.entity.fruit.skeleton.GasterBlaster;
import ttv.migami.mdf.entity.fruit.skeleton.Bone;

/**
 * Author: MigaMi
 */
public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Reference.MOD_ID);

    public static final RegistryObject<EntityType<GasterBlaster>> GASTER_BLASTER = REGISTER.register("gaster_blaster", () -> EntityType.Builder.<GasterBlaster>of(GasterBlaster::new, MobCategory.MISC).sized(1.0F, 1.0F).noSave().fireImmune().build("gaster_blaster"));
    public static final RegistryObject<EntityType<Bone>> SMALL_BONE = REGISTER.register("small_bone", () -> EntityType.Builder.<Bone>of(Bone::new, MobCategory.MISC).sized(0.8F, 0.8F).noSave().noSummon().fireImmune().build("small_bone"));
    public static final RegistryObject<EntityType<BoneZone>> BONE_ZONE = REGISTER.register("bone_zone", () -> EntityType.Builder.<BoneZone>of(BoneZone::new, MobCategory.MISC).sized(3.0F, 2.0F).noSave().noSummon().fireImmune().build("bone_zone"));
}
