package ttv.migami.mdf.event;

import net.minecraftforge.fml.common.Mod;
import ttv.migami.mdf.Reference;


@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCommonEventBus {

    /*@SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GASTER_BLASTER.get(), GasterBlaster.createAttributes().build());
    }*/

}
