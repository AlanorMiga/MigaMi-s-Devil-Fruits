package ttv.migami.mdf.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ttv.migami.mdf.Reference;
import ttv.migami.mdf.entity.fruit.StunEntity;
import ttv.migami.mdf.entity.fruit.buster.*;
import ttv.migami.mdf.entity.fruit.flower.*;
import ttv.migami.mdf.entity.fruit.skeleton.Bone;
import ttv.migami.mdf.entity.fruit.skeleton.BoneZone;
import ttv.migami.mdf.entity.fruit.skeleton.GasterBlaster;
import ttv.migami.mdf.entity.fruit.squid.InkSplat;

/**
 * Author: MigaMi
 */
public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Reference.MOD_ID);

    public static final RegistryObject<EntityType<GasterBlaster>> GASTER_BLASTER = REGISTER.register("gaster_blaster", () -> EntityType.Builder.<GasterBlaster>of(GasterBlaster::new, MobCategory.MISC).sized(1.0F, 1.0F).noSave().noSummon().fireImmune().build("gaster_blaster"));
    public static final RegistryObject<EntityType<Bone>> SMALL_BONE = REGISTER.register("small_bone", () -> EntityType.Builder.<Bone>of(Bone::new, MobCategory.MISC).sized(0.8F, 0.3F).noSave().noSummon().fireImmune().build("small_bone"));
    public static final RegistryObject<EntityType<BoneZone>> BONE_ZONE = REGISTER.register("bone_zone", () -> EntityType.Builder.<BoneZone>of(BoneZone::new, MobCategory.MISC).sized(3.0F, 2.0F).noSave().noSummon().fireImmune().build("bone_zone"));
    public static final RegistryObject<EntityType<InkSplat>> INK_SPLAT = REGISTER.register("ink_splat", () -> EntityType.Builder.<InkSplat>of(InkSplat::new, MobCategory.MISC).sized(1.0F, 1.0F).noSave().noSummon().fireImmune().build("ink_splat"));
    public static final RegistryObject<EntityType<Piano>> PIANO = REGISTER.register("piano", () -> EntityType.Builder.<Piano>of(Piano::new, MobCategory.MISC).sized(3.2F, 0.8F).noSave().noSummon().fireImmune().build("piano"));
    public static final RegistryObject<EntityType<Dynamite>> DYNAMITE = REGISTER.register("dynamite", () -> EntityType.Builder.<Dynamite>of(Dynamite::new, MobCategory.MISC).sized(1.0F, 1.0F).noSave().noSummon().fireImmune().build("dynamite"));
    public static final RegistryObject<EntityType<Buster>> BUSTER = REGISTER.register("buster", () -> EntityType.Builder.<Buster>of(Buster::new, MobCategory.MISC).sized(1.3965F, 1.6F).noSave().build("buster"));
    public static final RegistryObject<EntityType<Lasso>> LASSO = REGISTER.register("lasso", () -> EntityType.Builder.<Lasso>of(Lasso::new, MobCategory.MISC).sized(2.0F, 0.5F).noSave().noSummon().build("lasso"));
    public static final RegistryObject<EntityType<Cactus>> CACTUS = REGISTER.register("cactus", () -> EntityType.Builder.<Cactus>of(Cactus::new, MobCategory.MISC).sized(6.0F, 10.0F).noSave().noSummon().build("cactus"));
    public static final RegistryObject<EntityType<FlowerSpear>> FLOWER_SPEAR = REGISTER.register("flower_spear", () -> EntityType.Builder.<FlowerSpear>of(FlowerSpear::new, MobCategory.MISC).sized(1.0F, 2.0F).noSave().noSummon().build("flower_spear"));
    public static final RegistryObject<EntityType<VineTrap>> VINE_TRAP = REGISTER.register("vine_trap", () -> EntityType.Builder.<VineTrap>of(VineTrap::new, MobCategory.MISC).sized(2.0F, 6.0F).noSave().noSummon().build("vine_trap"));
    public static final RegistryObject<EntityType<PiranhaPlant>> PIRANHA_PLANT = REGISTER.register("piranha_plant", () -> EntityType.Builder.<PiranhaPlant>of(PiranhaPlant::new, MobCategory.MISC).sized(2.0F, 6.0F).noSave().noSummon().build("piranha_plant"));
    public static final RegistryObject<EntityType<Vine>> VINE = REGISTER.register("vine", () -> EntityType.Builder.<Vine>of(Vine::new, MobCategory.MISC).sized(5.0F, 4.0F).noSave().noSummon().build("vine"));
    public static final RegistryObject<EntityType<Petal>> PETAL = REGISTER.register("petal", () -> EntityType.Builder.<Petal>of(Petal::new, MobCategory.MISC).sized(1.0F, 0.3F).noSave().noSummon().build("petal"));

    public static final RegistryObject<EntityType<StunEntity>> STUN_ENTITY = REGISTER.register("stun_entity", () -> EntityType.Builder.<StunEntity>of(StunEntity::new, MobCategory.MISC).sized(4.0F, 4.0F).noSave().noSummon().build("stun_entity"));

}
