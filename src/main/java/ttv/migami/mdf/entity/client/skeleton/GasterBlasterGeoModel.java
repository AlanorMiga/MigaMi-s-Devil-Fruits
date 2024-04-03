package ttv.migami.mdf.entity.client.skeleton;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import ttv.migami.mdf.Reference;
import ttv.migami.mdf.entity.fruit.skeleton.GasterBlaster;

public class GasterBlasterGeoModel extends GeoModel<GasterBlaster> {

	@Override
	public ResourceLocation getModelResource(GasterBlaster animatable) {
		return new ResourceLocation(Reference.MOD_ID, "geo/entity/gaster_blaster.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(GasterBlaster animatable) {
		return new ResourceLocation(Reference.MOD_ID, "textures/fruit/skeleton/gaster_blaster.png");
	}

	@Override
	public ResourceLocation getAnimationResource(GasterBlaster animatable) {
		return new ResourceLocation(Reference.MOD_ID, "animations/entity/gaster_blaster.animation.json");
	}

	@Override
	public void setCustomAnimations(GasterBlaster animatable, long instanceId, AnimationState<GasterBlaster> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			// TODO: Gaster does not face the entity please help!!!
			//head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			//head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}
}