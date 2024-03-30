package ttv.migami.mdf.common.network;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class ServerPlayHandler
{

    // Placeholder for action!
    public static void shootSnowball(ServerPlayer pShooter) {
        Level pLevel = pShooter.level();

        if (!pLevel.isClientSide()) {
            Snowball snowball = new Snowball(pLevel, pShooter);

            Vec3 look = pShooter.getViewVector(1F);
            double speed = 1.5;
            snowball.lerpMotion(look.x * speed, look.y * speed, look.z * speed);

            pLevel.addFreshEntity(snowball);
        }
    }

    public static void messageToFruit(Player player, int effect, int move) {
        switch (effect) {
            case 1:
                FireworkFruitHandler.moveHandler(player, move);
                break;
            case 2:
                CreeperFruitHandler.moveHandler(player, move);
                break;
            case 3:
                SkeletonFruitHandler.moveHandler(player, move);
                break;
        }
    }

    private static boolean canDamage(Entity entity) {

        return entity instanceof LivingEntity;

    }

    static BlockPos rayTrace(Player pPlayer, double distance) {
        HitResult rayTraceResult = pPlayer.pick(distance, 1.0F, false);
        return BlockPos.containing(rayTraceResult.getLocation());
    }

    static EntityHitResult hitEntity(Level pLevel, Player pPlayer, BlockPos blockPos) {
        return ProjectileUtil.getEntityHitResult(pLevel, pPlayer, pPlayer.getEyePosition(), blockPos.getCenter(), new AABB(pPlayer.getEyePosition(), blockPos.getCenter()), ServerPlayHandler::canDamage);
    }

    static void actionSlowdown(Player pPlayer) {
        pPlayer.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 0, false, false));
        pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 0, false, false));
        pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10, 4, false, false));
    }

    static void actionHeavySlowdown(Player pPlayer) {
        pPlayer.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 0, false, false));
        pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 4, false, false));
        pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10, 4, false, false));
    }

}
