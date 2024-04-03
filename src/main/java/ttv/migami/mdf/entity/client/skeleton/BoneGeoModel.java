package ttv.migami.mdf.entity.client.skeleton;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import ttv.migami.mdf.Reference;
import ttv.migami.mdf.entity.fruit.skeleton.Bone;

public class BoneGeoModel extends GeoModel<Bone> {
	@Override
	public ResourceLocation getModelResource(Bone animatable) {
		return new ResourceLocation(Reference.MOD_ID, "geo/entity/bone.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Bone animatable) {
		return new ResourceLocation(Reference.MOD_ID, "textures/fruit/skeleton/bone.png");
	}

	@Override
	public ResourceLocation getAnimationResource(Bone animatable) {
		return null;
	}

	@Override
	public void setCustomAnimations(Bone animatable, long instanceId, AnimationState<Bone> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("bone");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}
}