package ttv.migami.mdf.event;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ttv.migami.mdf.Reference;
import ttv.migami.mdf.effect.FruitEffect;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class AfterDeathHandler {

    private static final Map<UUID, Map<MobEffectInstance, Integer>> playerEffects = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerDeath(PlayerEvent.Clone event) {
        Player player = event.getOriginal();
            UUID playerId = player.getUUID();
            Map<MobEffectInstance, Integer> effects = new HashMap<>();
            for (MobEffectInstance effect : player.getActiveEffects()) {
                if (effect.getEffect() instanceof FruitEffect) {
                    effects.put(effect, effect.getDuration());
                }
            }
            playerEffects.put(playerId, effects);
            //System.out.println("Effects saved");

    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        UUID playerId = player.getUUID();
        if (playerEffects.containsKey(playerId)) {
            Map<MobEffectInstance, Integer> effects = playerEffects.get(playerId);
            for (Map.Entry<MobEffectInstance, Integer> entry : effects.entrySet()) {
                MobEffectInstance effect = new MobEffectInstance(entry.getKey().getEffect(), entry.getValue(), entry.getKey().getAmplifier(), entry.getKey().isAmbient(), entry.getKey().isVisible());
                player.addEffect(effect);
            }
            //System.out.println("Restored");
            playerEffects.remove(playerId);
        }
        //System.out.println("No UUID Found");
    }
}