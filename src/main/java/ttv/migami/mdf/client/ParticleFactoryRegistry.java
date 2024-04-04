package ttv.migami.mdf.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ttv.migami.mdf.Reference;
import ttv.migami.mdf.client.particle.GasterBlasterBeamParticle;
import ttv.migami.mdf.client.particle.SkeletonControlParticle;
import ttv.migami.mdf.init.ModParticleTypes;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleFactoryRegistry
{
    @SubscribeEvent
    public static void onRegisterParticleFactory(RegisterParticleProvidersEvent event)
    {
        event.registerSpriteSet(ModParticleTypes.GASTER_BLASTER_BEAM.get(), GasterBlasterBeamParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.SKELETON_CONTROL_PARTICLE.get(), SkeletonControlParticle.Provider::new);
    }
}
