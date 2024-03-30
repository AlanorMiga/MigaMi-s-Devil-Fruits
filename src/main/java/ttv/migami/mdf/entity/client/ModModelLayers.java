package ttv.migami.mdf.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import ttv.migami.mdf.Reference;

public class ModModelLayers {
    public static final ModelLayerLocation GASTER_BLASTER_LAYER = new ModelLayerLocation(
            new ResourceLocation(Reference.MOD_ID, "gaster_blaster_layer"), "main");

    public static final ModelLayerLocation SMALL_BONE_LAYER = new ModelLayerLocation(
            new ResourceLocation(Reference.MOD_ID, "small_bone_layer"), "main");

    public static final ModelLayerLocation BONE_ZONE_LAYER = new ModelLayerLocation(
            new ResourceLocation(Reference.MOD_ID, "bone_zone_layer"), "main");

}
