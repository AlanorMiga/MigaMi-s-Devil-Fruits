package ttv.migami.mdf.common.network;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import ttv.migami.mdf.entity.CustomLightningBolt;
import ttv.migami.mdf.init.ModEffects;

import static ttv.migami.mdf.common.network.ServerPlayHandler.*;

/**
 * Author: MigaMi
 */
public class CreeperFruitHandler
{

    public static void moveHandler(Player pPlayer, int move) {
        Level pLevel = pPlayer.level;

        if (!pLevel.isClientSide()) {

            BlockPos blockPos;

            switch (move) {
                case 1:
                    actionHeavySlowdown(pPlayer);
                    removeCamouflage(pPlayer);
                    explodeSelf(pPlayer);
                    weakness(pPlayer);
                    break;
                case 2:
                    blockPos = rayTrace(pPlayer, 16.0D);
                    actionSlowdown(pPlayer);
                    explode(pPlayer, blockPos);
                    break;
                case 3:
                    camouflage(pPlayer);
                    break;
                case 4:
                    pLevel.addFreshEntity(new CustomLightningBolt(pLevel, (int) pPlayer.getX(), (int) pPlayer.getY(), (int) pPlayer.getZ()));
                    powerUser(pPlayer);
                    break;
            }
        }

    }

    private static void explodeSelf(Player pPlayer) {
        if (!pPlayer.level.isClientSide) {
            float explosionPower = calculateExplosionPower(pPlayer);
            pPlayer.level.explode(pPlayer, pPlayer.getX(), pPlayer.getY() + 0.5, pPlayer.getZ(), explosionPower, Level.ExplosionInteraction.NONE);
        }
    }

    private static void explode(Player pPlayer, BlockPos blockPos) {
        if (!pPlayer.level.isClientSide) {
            float explosionPower = calculateExplosionPower(pPlayer) / 2;

            EntityHitResult entityHitResult = ServerPlayHandler.hitEntity(pPlayer.level, pPlayer, blockPos);
            if (entityHitResult != null) {
                pPlayer.level.explode(pPlayer, entityHitResult.getLocation().x, entityHitResult.getLocation().y + 0.5, entityHitResult.getLocation().z, explosionPower, Level.ExplosionInteraction.NONE);
            }
            else {
                pPlayer.level.explode(pPlayer, blockPos.getX(), blockPos.getY() + 0.5, blockPos.getZ(), explosionPower, Level.ExplosionInteraction.NONE);
            }
        }
    }

    private static void weakness(Player pPlayer) {
        pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2, false, true));
        pPlayer.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0, false, true));
    }

    private static void camouflage(Player pPlayer) {
        pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 1, false, true));
        pPlayer.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 60, 0, false, true));
    }

    private static void removeCamouflage(Player pPlayer) {
        pPlayer.removeEffect(MobEffects.MOVEMENT_SPEED);
        pPlayer.removeEffect(MobEffects.INVISIBILITY);
    }

    private static void powerUser(Player pPlayer) {
        pPlayer.addEffect(new MobEffectInstance(ModEffects.POWER.get(), 100, 0, false, false));
    }

    private static boolean isPoweredUser(Player pPlayer) {
        return pPlayer.hasEffect(ModEffects.POWER.get());
    }

    private static float calculateExplosionPower(Player pPlayer) {
        if (isPoweredUser(pPlayer)) {
            return 1.5F;
        }
        else return 1F;
    }

}
