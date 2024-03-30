package ttv.migami.mdf.entity.fruit.skeleton;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import ttv.migami.mdf.init.ModEntities;
import ttv.migami.mdf.init.ModSounds;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BoneZone extends Entity implements TraceableEntity {
    private int warmupDelayTicks;
    private int lifeTicks = 300;
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    private float damage = 3.0F;
    private Set<Entity> damagedEntities = new HashSet<>();

    public BoneZone(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BoneZone(Level pLevel, Player pPlayer, BlockPos pPos, float pXRot) {
        super(ModEntities.BONE_ZONE.get(), pLevel);
        this.setPos(pPos.getCenter().add(0, -0.5, 0));
        this.setXRot(pXRot);
        this.setOwner(pPlayer);
    }

    public void setOwner(@javax.annotation.Nullable LivingEntity pOwner) {
        this.owner = pOwner;
        this.ownerUUID = pOwner == null ? null : pOwner.getUUID();
    }

    /**
     * Returns null or the entityliving it was ignited by
     */
    @javax.annotation.Nullable
    public LivingEntity getOwner() {
        if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel) {
            Entity entity = ((ServerLevel)this.level()).getEntity(this.ownerUUID);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity)entity;
            }
        }

        return this.owner;
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    public void tick() {
        super.tick();

        Level level = this.level();

        if (!level.isClientSide)
        {
            List<Entity> collidedEntities = level.getEntities(this, this.getBoundingBox());
            for (Entity entity : collidedEntities) {
                if (entity instanceof LivingEntity && entity != owner) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    if (!hasBeenDamaged(entity)) {
                        livingEntity.hurt(this.damageSources().generic(), damage);
                        livingEntity.invulnerableTime = 0;
                    }
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 5, false, false));
                    this.markAsDamaged(livingEntity);
                }
            }

            if (--this.warmupDelayTicks < 0) {
                --this.lifeTicks;
                if (this.warmupDelayTicks == -1) {
                    level.playSound(this, this.blockPosition(), ModSounds.BONE_ZONE.get(), SoundSource.PLAYERS, 3F, 1F);
                }

                if (--this.lifeTicks < 0) {
                    this.discard();
                }
            }
        }
    }

    private boolean hasBeenDamaged(Entity entity) {
        return damagedEntities.contains(entity);
    }

    private void markAsDamaged(Entity entity) {
        damagedEntities.add(entity);
    }

}
