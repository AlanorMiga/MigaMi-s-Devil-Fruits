package ttv.migami.mdf.common.network;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import ttv.migami.mdf.entity.fruit.skeleton.BoneZone;
import ttv.migami.mdf.entity.fruit.skeleton.GasterBlaster;
import ttv.migami.mdf.entity.fruit.skeleton.Bone;
import ttv.migami.mdf.init.ModSounds;

import static ttv.migami.mdf.common.network.ServerPlayHandler.actionSlowdown;
import static ttv.migami.mdf.common.network.ServerPlayHandler.rayTrace;

/**
 * Author: MigaMi
 */
public class SkeletonFruitHandler
{

    public static void moveHandler(Player pPlayer, int move) {
        Level pLevel = pPlayer.level();

        if (!pLevel.isClientSide()) {

            Vec3 look = pPlayer.getViewVector(1F);
            GasterBlaster gasterBlaster;
            BlockPos blockPos;
            Vec3 playerPos;
            RandomSource rand = RandomSource.create();
            EntityHitResult entityHitResult;
            LivingEntity pTarget;

            double xOffset;
            double zOffset;
            double speed;

            switch (move) {
                case 1:
                    playerPos = pPlayer.getPosition(1F);

                    actionSlowdown(pPlayer);
                    Bone bone = new Bone(pLevel, pPlayer, playerPos);
                    pLevel.addFreshEntity(bone);
                    break;
                case 2:
                    blockPos = rayTrace(pPlayer, 48.0D);
                    entityHitResult = ServerPlayHandler.hitEntity(pLevel, pPlayer, blockPos);

                    // Just Enough Guns code! :D
                    Vec3 lookVec = pPlayer.getLookAngle(); //Get the player's look vector
                    Vec3 rightVec = new Vec3(-lookVec.z, 0, lookVec.x).normalize();
                    Vec3 forwardVec = new Vec3(lookVec.x, 0, lookVec.z).normalize();

                    double sideOffset = 2.5;
                    double randomChance = Math.random();

                    if (randomChance < 0.5) {
                        sideOffset *= -1;
                    }

                    double offsetX = rightVec.x * sideOffset + forwardVec.x * 2; //Move the blaster 2.5 blocks to the side and 2 blocks forward
                    double offsetY = pPlayer.getEyeHeight() + 0.4; //Move the blaster slightly above the player's head
                    double offsetZ = rightVec.z * sideOffset + forwardVec.z * 2; //Move the blaster 2.5 blocks to the side and 2 blocks forward

                    playerPos = pPlayer.getPosition(1F).add(offsetX, offsetY, offsetZ);

                    actionSlowdown(pPlayer);
                    if (entityHitResult != null && !pPlayer.isCrouching()) {
                        gasterBlaster = new GasterBlaster(pLevel, pPlayer, playerPos, entityHitResult.getEntity());
                    }
                    else {
                        gasterBlaster = new GasterBlaster(pLevel, pPlayer, playerPos, blockPos.getCenter());
                    }
                    pLevel.addFreshEntity(gasterBlaster);
                    break;
                case 3:
                    blockPos = rayTrace(pPlayer, 24.0D);
                    entityHitResult = ServerPlayHandler.hitEntity(pLevel, pPlayer, blockPos);

                    Vec3 targetPos = entityHitResult.getLocation();
                    Vec3 distanceTo = targetPos.subtract(pPlayer.getEyePosition());
                    Vec3 normal = distanceTo.normalize();

                    pTarget = (LivingEntity) entityHitResult.getEntity();

                    pLevel.playSound(pTarget, pTarget.blockPosition(), ModSounds.GASTER_BLASTER_PRIME.get(), SoundSource.PLAYERS, 3F, 1F);
                    //if (pTarget instanceof Player)
                    //{
                        pTarget.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 10, 24, false, false));
                    /*}
                    else
                    {
                        pTarget.push(normal.x() * 2, normal.y() * -2, normal.z() * 2);
                    }*/
                    pTarget.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 0, false, false));
                    pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 6, false, false));
                    pTarget.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 60, 6, false, false));
                    break;
                case 4:
                    blockPos = rayTrace(pPlayer, 32.0D);
                    BlockState blockState = pLevel.getBlockState(blockPos);
                    entityHitResult = ServerPlayHandler.hitEntity(pLevel, pPlayer, blockPos);

                    if (entityHitResult != null) {
                        pTarget = (LivingEntity) entityHitResult.getEntity();
                        Vec3 pGasterPos = pTarget.getPosition(1F);
                        if (pTarget.fallDistance == 0) {
                            pLevel.addFreshEntity(new BoneZone(pLevel, pPlayer, BlockPos.containing(entityHitResult.getLocation()), (float) look.x));
                        }
                        else {
                            // Spawn Gaster Blasters in a triangle formation
                            double angleIncrement = Math.PI * 2 / 3;
                            double radius = 4.0;
                            for (int i = 0; i < 3; i++) {
                                double angle = angleIncrement * i;
                                offsetX = Math.cos(angle) * radius;
                                offsetZ = Math.sin(angle) * radius;
                                Vec3 blasterPos = pGasterPos.add(offsetX, 3, offsetZ);

                                gasterBlaster = new GasterBlaster(pLevel, pPlayer, blasterPos, entityHitResult.getEntity());
                                pLevel.addFreshEntity(gasterBlaster);
                            }
                        }

                        //pLevel.playSound(pTarget, pTarget.blockPosition(), ModSounds.GASTER_BLASTER_PRIME.get(), SoundSource.PLAYERS, 3F, 1F);
                        //entityHitResult.getEntity().push(0, -5, 0);
                        //pTarget.removeEffect(MobEffects.LEVITATION);
                        //pTarget.removeEffect(MobEffects.SLOW_FALLING);
                        //pTarget.fallDistance = 4;
                    }
                    else {
                        pLevel.addFreshEntity(new BoneZone(pLevel, pPlayer, blockPos, (float) look.x));
                    }
                    break;
                case 5:
                    blockPos = rayTrace(pPlayer, 14.0D);
                    pLevel.playSound(pPlayer, blockPos, ModSounds.BLINK.get(), SoundSource.PLAYERS, 2F, 1F);
                    pPlayer.teleportTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 0, false, false));
                    break;
            }
        }

    }

    private static boolean canDamage(Entity entity) {

        return entity instanceof LivingEntity;

    }

}
