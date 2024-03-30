package ttv.migami.mdf.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ttv.migami.mdf.Reference;
import ttv.migami.mdf.entity.client.ModModelLayers;
import ttv.migami.mdf.entity.client.skeleton.BoneZoneModel;
import ttv.migami.mdf.entity.client.skeleton.GasterBlasterModel;
import ttv.migami.mdf.entity.client.skeleton.SmallBoneModel;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEventsBus {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.GASTER_BLASTER_LAYER, GasterBlasterModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SMALL_BONE_LAYER, SmallBoneModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BONE_ZONE_LAYER, BoneZoneModel::createBodyLayer);
    }
}
