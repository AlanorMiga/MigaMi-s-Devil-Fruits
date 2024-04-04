package ttv.migami.mdf.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ttv.migami.mdf.Reference;
import ttv.migami.mdf.entity.fruit.skeleton.GasterBlaster;
import ttv.migami.mdf.init.ModEntities;


@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCommonEventBus {

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GASTER_BLASTER.get(), GasterBlaster.createAttributes().build());
    }

}
