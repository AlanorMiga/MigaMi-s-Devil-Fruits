package ttv.migami.mdf.entity.fruit.skeleton;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import ttv.migami.mdf.init.ModEntities;
import ttv.migami.mdf.init.ModSounds;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SmallBone extends Entity implements TraceableEntity {
    private int warmupDelayTicks;
    private int lifeTicks = 100;
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    @Nullable
    private Vec3 target;
    private float damage = 3;
    private List<Entity> hurtEntities = new ArrayList<>();

    public SmallBone(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public SmallBone(Level pLevel, Player pPlayer, Vec3 pPos) {
        super(ModEntities.SMALL_BONE.get(), pLevel);
        this.setPos(pPos.add(0, 1, 0));
        this.setOwner(pPlayer);

        Vec3 dir = this.getDirection(pPlayer);
        double speed = 3.5F;
        this.setDeltaMovement(dir.x * speed, dir.y * speed, dir.z * speed);
        this.updateHeading();
    }

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
        this.updateHeading();

        Level level = this.level();

        double nextPosX = this.getX() + this.getDeltaMovement().x();
        double nextPosY = this.getY() + this.getDeltaMovement().y();
        double nextPosZ = this.getZ() + this.getDeltaMovement().z();
        this.setPos(nextPosX, nextPosY, nextPosZ);

        if (!this.isRemoved()) {
            List<Entity> nearbyEntities = this.level().getEntities(this, new AABB(
                            this.getX() - 0.5, this.getY() - 0.5, this.getZ() - 0.5,
                            this.getX() + 0.5, this.getY() + 0.5, this.getZ() + 0.5),
                    EntitySelector.NO_SPECTATORS);


            hurtEntities.addAll(nearbyEntities);

            for (Entity entity : hurtEntities) {
                if (entity instanceof LivingEntity && entity != owner) {
                    entity.hurt(this.damageSources().generic(), damage);
                    entity.invulnerableTime = 0;
                    this.remove(RemovalReason.KILLED);
                }
            }
        }

        if (!level.isClientSide)
        {
            if (--this.warmupDelayTicks < 0) {
                --this.lifeTicks;
                if (this.warmupDelayTicks == -1) {
                    level.playSound(this, this.blockPosition(), ModSounds.BONE_THROW.get(), SoundSource.PLAYERS, 3F, 1F);
                }

                if (--this.lifeTicks < 0) {
                    this.discard();
                }
            }
        }
    }

    public void updateHeading()
    {
        double horizontalDistance = this.getDeltaMovement().horizontalDistance();
        this.setYRot((float) (Mth.atan2(this.getDeltaMovement().x(), this.getDeltaMovement().z()) * (180D / Math.PI)));
        this.setXRot((float) (Mth.atan2(this.getDeltaMovement().y(), horizontalDistance) * (180D / Math.PI)));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    public void setOwner(@Nullable LivingEntity pOwner) {
        this.owner = pOwner;
        this.ownerUUID = pOwner == null ? null : pOwner.getUUID();
    }

    @Nullable
    @Override
    public Entity getOwner() {
        return null;
    }

    private Vec3 getDirection(LivingEntity pShooter)
    {
        return this.getVectorFromRotation(pShooter.getXRot(), pShooter.getYHeadRot());
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
