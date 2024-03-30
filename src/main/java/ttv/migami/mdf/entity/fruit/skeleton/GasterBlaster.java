package ttv.migami.mdf.entity.fruit.skeleton;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import ttv.migami.mdf.init.ModEntities;
import ttv.migami.mdf.init.ModSounds;

import javax.annotation.Nullable;
import java.util.UUID;

public class GasterBlaster extends Entity implements TraceableEntity {
    private int warmupDelayTicks;
    private boolean shot = false;
    private int lifeTicks = 100;
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    @Nullable
    private Vec3 target;
    @Nullable
    private Entity targetEntity;
    private float damage = 5.0F;

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(GasterBlaster.class, EntityDataSerializers.BOOLEAN);

    public GasterBlaster(EntityType<? extends GasterBlaster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public GasterBlaster(Level pLevel, Player pPlayer, Vec3 pPos, Vec3 pTarget, float pXRot, float pYRot) {
        super(ModEntities.GASTER_BLASTER.get(), pLevel);
        this.setPos(pPos);
        this.setXRot(pXRot);
        this.setYRot(pYRot);
        this.target = pTarget;
        this.setOwner(pPlayer);
    }

    public GasterBlaster(Level pLevel, Player pPlayer, Vec3 pPos, Entity pEntity, float pXRot, float pYRot) {
        super(ModEntities.GASTER_BLASTER.get(), pLevel);
        this.setPos(pPos);
        this.setXRot(pXRot);
        this.setYRot(pYRot);
        this.targetEntity = pEntity;
        this.setOwner(pPlayer);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();

    public void setTarget(@Nullable Vec3 pTarget) {
        this.target = pTarget;
    }

    @Nullable
    public Vec3 getTarget() {
        if (this.target != null) {
            return target;
        }
        else {
            return null;
        }
    }

    public void setOwner(@Nullable LivingEntity pOwner) {
        this.owner = pOwner;
        this.ownerUUID = pOwner == null ? null : pOwner.getUUID();
    }

    /**
     * Returns null or the entityliving it was ignited by
     */
    @Nullable
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
        this.entityData.define(ATTACKING, false);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (ATTACKING.equals(pKey)) {
            if (isAttacking()) {
                this.attackAnimationState.start(this.tickCount);
            }

        }
        super.onSyncedDataUpdated(pKey);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    public void tick() {
        super.tick();

        Level level = this.level();

        if (level.isClientSide)
        {
            this.attackAnimationState.animateWhen(this.isAttacking(), this.tickCount);
        }
        else
        {
            if (this.targetEntity != null) {
                this.lookAt(EntityAnchorArgument.Anchor.EYES, this.targetEntity.getPosition(1F));
            }
            else if (this.target != null) {
                this.lookAt(EntityAnchorArgument.Anchor.EYES, this.target);
            }
            if (--this.warmupDelayTicks < 0) {
                --this.lifeTicks;
                if (this.warmupDelayTicks == -1) {
                    level.playSound(this, this.blockPosition(), ModSounds.GASTER_BLASTER_PRIME.get(), SoundSource.PLAYERS, 3F, 1F);
                }
                if (this.warmupDelayTicks == -12) {
                    if (!this.shot) {
                        shoot();
                        this.shot = true;
                        this.setAttacking(true);
                    }
                }

                if (--this.lifeTicks < 0) {
                    this.discard();
                }
            }
        }
    }

    public void shoot() {
        Level level = this.level();

        HitResult result = this.pick(48, 0, false);
        Vec3 eyePos = this.getEyePosition().add(0, -0.75, 0);
        Vec3 targetPos = result.getLocation();
        if (this.target != null) {
            targetPos = this.getTarget();
        }
        Vec3 distanceTo = targetPos.subtract(eyePos);
        Vec3 normal = distanceTo.normalize();

        for(int i = 1; i < Mth.floor(distanceTo.length()) + 7; ++i) {
            Vec3 eyeVec3 = eyePos.add(normal.scale((double)i));
            if (this.targetEntity != null) {
                ((ServerLevel) level).sendParticles(ParticleTypes.SONIC_BOOM, eyeVec3.x, eyeVec3.y + 1, eyeVec3.z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
            }
            else {
                ((ServerLevel) level).sendParticles(ParticleTypes.SONIC_BOOM, eyeVec3.x, eyeVec3.y, eyeVec3.z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
            }
        }

        EntityHitResult entityHitResult = ProjectileUtil.getEntityHitResult(level, this, eyePos, targetPos, new AABB(eyePos, targetPos), this::canDamage);

        if(entityHitResult != null && entityHitResult.getEntity() instanceof LivingEntity entity && entity != owner) {

            entity.hurt(this.damageSources().sonicBoom(this), this.damage);
            double d1 = 0.5D * (1.0D - entity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
            double d0 = 2.5D * (1.0D - entity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
            entity.push(normal.x() * d0, normal.y() * d1, normal.z() * d0);
            entity.invulnerableTime = 0;

        }
        level.playSound(this, this.blockPosition(), ModSounds.GASTER_BLASTER_SHOOT.get(), SoundSource.PLAYERS, 3F, 1F);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    private boolean canDamage(Entity entity) {

        return entity instanceof LivingEntity;

    }

}
