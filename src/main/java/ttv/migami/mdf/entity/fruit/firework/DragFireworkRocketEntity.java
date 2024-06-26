package ttv.migami.mdf.entity.fruit.firework;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.*;

public class DragFireworkRocketEntity extends CustomFireworkRocketEntity {

    private List<Entity> draggedEntities = new ArrayList<>();

    public boolean dragsUser = true;

    public DragFireworkRocketEntity(Level pLevel, ItemStack pStack, Entity pShooter, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ, pStack);
        this.setOwner(pShooter);
    }

    public DragFireworkRocketEntity(Level pLevel, Boolean pDragsUser, ItemStack pStack, Entity pShooter, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ, pStack);
        this.setDragsUser(pDragsUser);
        this.setOwner(pShooter);
    }

    public DragFireworkRocketEntity(Level pLevel, ItemStack pStack, Entity pShooter, double pX, double pY, double pZ, Boolean pShotAtAngle) {
        super(pLevel, pStack, pX, pY, pZ, pShotAtAngle);
        this.setOwner(pShooter);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.isRemoved()) {
            List<Entity> nearbyEntities = this.level().getEntities(this, new AABB(
                    this.getX() - 2, this.getY() - 2, this.getZ() - 2,
                    this.getX() + 2, this.getY() + 2, this.getZ() + 2),
                    EntitySelector.NO_SPECTATORS);


            draggedEntities.addAll(nearbyEntities);

            for (Entity entity : draggedEntities) {
                if (entity instanceof Player) {
                    if (entity == this.getOwner() && this.dragsUser) {
                        entity.startRiding(this);
                    }
                    else if (entity != this.getOwner()) {
                        entity.startRiding(this);
                    }
                    ((Player) entity).addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 100, 0, false, false));
                }
                else {
                    if (entity instanceof LivingEntity) {
                        ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 100, 0, false, false));
                    }
                    entity.setDeltaMovement(this.getDeltaMovement());
                }
            }
        }
    }

    private void setDragsUser(Boolean pDragsUser) {
        this.dragsUser = pDragsUser;
    }
}