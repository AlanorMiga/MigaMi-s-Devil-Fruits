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
import ttv.migami.mdf.common.network.fruit.*;
import ttv.migami.mdf.effect.FruitEffect;

public class ServerPlayHandler
{
    static FruitEffect effect;

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

    public static void messageToFruit(Player player, int effect, int move, int amount) {
        switch (effect) {
            case 1:
                FireworkFruitHandler.moveHandler(player, move);
                break;
            case 2:
                CreeperFruitHandler.moveHandler(player, move);
                break;
            case 3:
                SkeletonFruitHandler.moveHandler(player, move, amount);
                break;
            case 4:
                SquidFruitHandler.moveHandler(player, move, amount);
                break;
            case 5:
                BusterFruitHandler.moveHandler(player, move);
                break;
            case 6:
                FlowerFruitHandler.moveHandler(player, move, amount);
                break;
            default:
                break;
        }
    }

    public static boolean canDamage(Entity entity) {

        return entity instanceof LivingEntity;

    }

    public static BlockPos rayTrace(Player pPlayer, double distance) {
        HitResult rayTraceResult = pPlayer.pick(distance, 1.0F, false);
        return BlockPos.containing(rayTraceResult.getLocation());
    }

    public static EntityHitResult hitEntity(Level pLevel, Player pPlayer, BlockPos blockPos) {
        return ProjectileUtil.getEntityHitResult(pLevel, pPlayer, pPlayer.getEyePosition(), blockPos.getCenter(), new AABB(pPlayer.getEyePosition(), blockPos.getCenter()), ServerPlayHandler::canDamage);
    }

    public static void actionSlowdown(Player pPlayer) {
        pPlayer.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 0, false, false));
        pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 0, false, false));
        pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10, 4, false, false));
    }

    public static void actionHeavySlowdown(Player pPlayer) {
        pPlayer.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 0, false, false));
        pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 4, false, false));
        pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10, 4, false, false));
    }

    // Damage that scales with XP level, caps at 3 times the Base Damage or experience level 30
    public static float calculateCustomDamage(Player player, float baseDamage) {
        float maxScaling = 3.0F;
        float maxDamage = baseDamage * maxScaling;
        int maxLevel = 30;
        // XP divisor to control scaling
        float xpDivisor = 5.0F;

        float damage;
        if (player.experienceLevel < maxLevel && !player.isCreative()) {
            damage = baseDamage + ((float) player.experienceLevel / xpDivisor) * (maxDamage - baseDamage) / (maxLevel / xpDivisor);
        } else {
            damage = maxDamage;
        }

        damage = Math.max(baseDamage, Math.min(damage, maxDamage));

        return damage;
    }

    /*
     * Applies Food Exhaustion to the user
     * 25F drains 3 icons in 1 tick
     */
    public static void applyFoodExhaustion(Player pPlayer, float amount) {
        pPlayer.causeFoodExhaustion(amount);
    }

    public static void smallFoodExhaustion(Player pPlayer) {
        if (pPlayer.getFoodData().getFoodLevel() > 6) {
            pPlayer.causeFoodExhaustion(1F);
        }
    }

    public static void mediumFoodExhaustion(Player pPlayer) {
        if (pPlayer.getFoodData().getFoodLevel() > 6) {
            pPlayer.causeFoodExhaustion(3F);
        }
    }

    public static void bigFoodExhaustion(Player pPlayer) {
        if (pPlayer.getFoodData().getFoodLevel() > 6) {
            pPlayer.causeFoodExhaustion(5F);
        }
    }

    public static void largeFoodExhaustion(Player pPlayer) {
        if (pPlayer.getFoodData().getFoodLevel() > 6) {
            pPlayer.causeFoodExhaustion(10F);
        }
    }

    public static void applyHunger(Player pPlayer) {
        if (pPlayer.getFoodData().getFoodLevel() > 6) {
            pPlayer.addEffect(new MobEffectInstance(MobEffects.HUNGER, 20, 0, false, false));
        }
    }
}

