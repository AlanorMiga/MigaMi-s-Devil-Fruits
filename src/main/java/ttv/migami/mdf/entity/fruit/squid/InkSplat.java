package ttv.migami.mdf.entity.fruit.squid;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import ttv.migami.mdf.common.network.ServerPlayHandler;
import ttv.migami.mdf.entity.CustomProjectileEntity;
import ttv.migami.mdf.init.ModEntities;
import ttv.migami.mdf.init.ModSounds;

import java.util.List;
import java.util.UUID;

public class InkSplat extends CustomProjectileEntity {
    private int warmupDelayTicks;
    public int life = 100;
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    public float damage = 3;
    public float customDamage = damage;
    public boolean affectedByGravity;
    public boolean explosive;

    public InkSplat(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public InkSplat(Level pLevel, Player pPlayer, Vec3 pPos, Vec3 targetPos) {
        super(ModEntities.INK_SPLAT.get(), pLevel);
        this.setPos(pPos.add(0, 1, 0));
        this.setOwner(pPlayer);
        this.owner = pPlayer;

        Vec3 dir = this.getDirection(pPlayer);
        this.lookAt(EntityAnchorArgument.Anchor.EYES, targetPos);
        this.getLookAngle();
        this.affectedByGravity = true;
        double speed = 1.5F;
        this.setDeltaMovement(dir.x * speed, dir.y * speed, dir.z * speed);
        this.updateHeading();
    }

    public InkSplat(Level pLevel, Player pPlayer, Vec3 pPos, Vec3 targetPos, boolean explosive) {
        super(ModEntities.INK_SPLAT.get(), pLevel);
        this.setPos(pPos.add(0, 1, 0));
        this.setOwner(pPlayer);
        this.owner = pPlayer;

        Vec3 dir = this.getDirection(pPlayer);
        this.lookAt(EntityAnchorArgument.Anchor.EYES, targetPos);
        this.getLookAngle();
        this.affectedByGravity = false;
        this.explosive = explosive;
        double speed = 3.5F;
        this.setDeltaMovement(this.getLookAngle().x * speed, this.getLookAngle().y * speed, this.getLookAngle().z * speed);
        this.updateHeading();
    }

    @Override
    public void tick()
    {
        super.tick();
        if(this.affectedByGravity)
        {
            this.setDeltaMovement(this.getDeltaMovement().add(0, this.modifiedGravity, 0));
        }
    }

    @Override
    protected void onProjectileTick()
    {
        if(this.level().isClientSide && this.tickCount < this.life) {
            if (this.tickCount > 2)
            {
                this.level().addParticle(ParticleTypes.SQUID_INK, true, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
            }
        }
    }

    @Override
    public float calculateDamage() {
        this.customDamage = this.damage;
        if (this.getOwner() instanceof Player) {
            Player owner = (Player) this.getOwner();
            this.customDamage = ServerPlayHandler.calculateCustomDamage(owner, this.damage);
        }
        return this.customDamage;
    }

    @Override
    protected void onHitEntity(Entity entity, Vec3 hitVec, Vec3 startVec, Vec3 endVec)
    {
        int explosionRadius = 1;
        if (this.explosive) {
            Level pLevel = this.level();

            if (this.owner instanceof Player) {
                Player playerOwner = (Player) this.owner;
                if (playerOwner.experienceLevel > 20 || playerOwner.isCreative()) {
                    explosionRadius = 2;
                }
            }
            CustomProjectileEntity.createExplosion(this, explosionRadius, true);

            double radius = 4;
            AABB areaOfEffect = new AABB(this.getX() - radius, this.getY() - radius /2 , this.getZ() - radius, this.getX() + radius, this.getY() + radius / 2, this.getZ() + radius);
            List<Entity> entitiesArea = pLevel.getEntities(this, areaOfEffect);
            for (Entity entityAttack : entitiesArea) {
                if (entityAttack instanceof LivingEntity livingEntity) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 3, false, false));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 60, 0, false, false));
                }
            }
        }

        if (entity != this.owner && this.tickCount > 2) {
            entity.hurt(this.damageSources().playerAttack((Player) owner), this.customDamage);
            entity.invulnerableTime = 0;

            if (entity instanceof LivingEntity) {
                LivingEntity pTarget = (LivingEntity) entity;

                pTarget.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 30, 0, false, true));
                pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 3, false, false));
                pTarget.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 60, 6, false, false));
            }

            if (!this.level().isClientSide) {
                ServerLevel serverLevel = (ServerLevel) this.level();
                if (this.explosive) {
                    serverLevel.sendParticles(ParticleTypes.SQUID_INK, this.getX(), this.getY(), this.getZ(), 128, explosionRadius, explosionRadius, explosionRadius, 0.2);
                    serverLevel.playSound(null, this.getOnPos(), ModSounds.SQUID_EXPLOSION.get(), SoundSource.PLAYERS, 8F, 1F);
                }
                serverLevel.sendParticles(ParticleTypes.SQUID_INK, this.getX(), this.getY() + 1, this.getZ(), 12, 0.2, 0.0, 0.3, 0.2);
            }
        }
    }

    @Override
    protected void onHitBlock(BlockState state, BlockPos pos, Direction face, double x, double y, double z)
    {
        int explosionRadius = 1;
        if (this.explosive) {
            Level pLevel = this.level();

            if (this.owner instanceof Player) {
                Player playerOwner = (Player) this.owner;
                if (playerOwner.experienceLevel > 20 || playerOwner.isCreative()) {
                    explosionRadius = 2;
                }
            }
            CustomProjectileEntity.createExplosion(this, explosionRadius, true);

            double radius = 4;
            AABB areaOfEffect = new AABB(this.getX() - radius, this.getY() - radius /2 , this.getZ() - radius, this.getX() + radius, this.getY() + radius / 2, this.getZ() + radius);
            List<Entity> entitiesArea = pLevel.getEntities(this, areaOfEffect);
            for (Entity entity : entitiesArea) {
                if (entity instanceof LivingEntity livingEntity) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 3, false, false));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 60, 0, false, false));
                }
            }
        }

        if (!this.level().isClientSide) {
            BlockPos entityPos = this.blockPosition();
            BlockPos belowPos = entityPos.below();

            ServerLevel serverLevel = (ServerLevel) this.level();

            if (this.explosive) {
                serverLevel.sendParticles(ParticleTypes.SQUID_INK, this.getX(), this.getY(), this.getZ(), 128, explosionRadius, explosionRadius, explosionRadius, 0.2);
                serverLevel.playSound(null, this.getOnPos(), ModSounds.SQUID_EXPLOSION.get(), SoundSource.PLAYERS, 2F, 1F);
            }

            serverLevel.sendParticles(ParticleTypes.SQUID_INK, this.getX(), belowPos.getY() + 1, this.getZ(), 12, 0.2, 0.0, 0.3, 0.2);
            serverLevel.playSound(null, this.getOnPos(), ModSounds.SQUID_SPLAT.get(), SoundSource.PLAYERS, 2F, 1F);

            this.remove(RemovalReason.KILLED);
        }
    }

    @Nullable
    @Override
    public Entity getOwner() {
        return this.owner;
    }

    public void setOwner(@Nullable LivingEntity pOwner) {
        this.owner = pOwner;
        this.ownerUUID = pOwner == null ? null : pOwner.getUUID();
    }

    private Vec3 getDirection(LivingEntity pShooter)
    {
        return this.getVectorFromRotation(pShooter.getXRot() - (5 / 2.0F) + random.nextFloat() * 2, pShooter.getYHeadRot() - (5 / 2.0F) + random.nextFloat() * 2);
    }

    private Vec3 getVectorFromRotation(float pitch, float yaw)
    {
        float f = Mth.cos(-yaw * 0.017453292F - (float) Math.PI);
        float f1 = Mth.sin(-yaw * 0.017453292F - (float) Math.PI);
        float f2 = -Mth.cos(-pitch * 0.017453292F);
        float f3 = Mth.sin(-pitch * 0.017453292F);
        return new Vec3(f1 * f2, f3, f * f2);
    }

}
