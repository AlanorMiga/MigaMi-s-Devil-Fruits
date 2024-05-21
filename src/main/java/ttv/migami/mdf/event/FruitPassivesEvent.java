package ttv.migami.mdf.event;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ttv.migami.mdf.Reference;
import ttv.migami.mdf.init.ModEffects;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FruitPassivesEvent {

    @SubscribeEvent
    public static void onClientTick(TickEvent.PlayerTickEvent event) {

        Player player = event.player;
        Level level = player.level();

        if (level instanceof ServerLevel serverLevel)
        {
            if (player.hasEffect(ModEffects.FEATHER_FALLING.get())) {
                player.resetFallDistance();
            }

            if (player.hasEffect(ModEffects.POWER.get()) && !player.hasEffect(MobEffects.INVISIBILITY)) {
                serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                        player.getX(), player.getY() + 1, player.getZ(), 2, 0.3, 0.4, 0.3, 0);
            }

            if (player.hasEffect(ModEffects.SQUID_FRUIT.get())) {
                if (player.isInWater()) {
                    player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 20, 0, false, false));
                }
                if (player.isUnderWater()) {
                    player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 20, 0, false, false));
                }
                if (player.hasEffect(MobEffects.LEVITATION) && player.hasEffect(MobEffects.MOVEMENT_SPEED)) {
                    serverLevel.sendParticles(ParticleTypes.SQUID_INK,
                            player.getX(), player.getY(), player.getZ(), 2, 0.3, 0.4, 0.3, 0);
                }
            }
        }
    }
}
