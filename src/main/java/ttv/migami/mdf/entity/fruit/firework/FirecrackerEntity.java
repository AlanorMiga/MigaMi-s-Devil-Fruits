package ttv.migami.mdf.entity.fruit.firework;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Iterator;

public class FirecrackerEntity extends CustomFireworkRocketEntity {

    public FirecrackerEntity(Level pLevel, Entity pShooter, double pX, double pY, double pZ, ItemStack pStack) {
        super(pLevel, pX, pY, pZ, pStack);
        this.setOwner(pShooter);
    }

    @Override
    public void tick() {
        this.isSilent();
        this.explode();
    }

    @Override
    public void explode() {
        this.level.broadcastEntityEvent(this, (byte)17);
        this.gameEvent(GameEvent.EXPLODE, this.getOwner());
        this.dealExplosionDamage();
        this.discard();
    }

    @Override
    public void dealExplosionDamage() {
        float f = 0.0F;
        ItemStack itemstack = (ItemStack)this.entityData.get(DATA_ID_FIREWORKS_ITEM);
        CompoundTag compoundtag = itemstack.isEmpty() ? null : itemstack.getTagElement("Fireworks");
        ListTag listtag = compoundtag != null ? compoundtag.getList("Explosions", 10) : null;
        if (listtag != null && !listtag.isEmpty()) {
            f = 5.0F + (float)(listtag.size() * 2);
        }

        if (f > 0.0F) {
            if (this.attachedToEntity != null) {
                this.attachedToEntity.hurt(this.damageSources().fireworks(this, this.getOwner()), 5.0F + (float)(listtag.size() * 2));
            }

            double d0 = 5.0;
            Vec3 vec3 = this.position();
            Iterator var8 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(5.0)).iterator();

            while(true) {
                LivingEntity livingentity;
                do {
                    do {
                        if (!var8.hasNext()) {
                            return;
                        }

                        livingentity = (LivingEntity)var8.next();
                    } while(livingentity == this.attachedToEntity);
                } while(this.distanceToSqr(livingentity) > 25.0);

                boolean flag = false;

                for(int i = 0; i < 2; ++i) {
                    Vec3 vec31 = new Vec3(livingentity.getX(), livingentity.getY(0.5 * (double)i), livingentity.getZ());
                    HitResult hitresult = this.level.clip(new ClipContext(vec3, vec31, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
                    if (hitresult.getType() == HitResult.Type.MISS) {
                        flag = true;
                        break;
                    }
                }

                if (flag && livingentity != this.getOwner()) {
                    float f1 = (f * (float)Math.sqrt((5.0 - (double)this.distanceTo(livingentity)) / 5.0)) / 3;
                    livingentity.hurt(this.damageSources().fireworks(this, this.getOwner()), f1);
                    livingentity.invulnerableTime = 0;
                }
            }
        }
    }

}
