package ttv.migami.mdf.entity.client.skeleton;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class BoneZoneModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart bone_zone;

	public BoneZoneModel(ModelPart root) {
		this.bone_zone = root;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone_zone = partdefinition.addOrReplaceChild("bone_zone", CubeListBuilder.create(), PartPose.offset(10.2126F, 19.544F, 15.7802F));

		PartDefinition bone_r1 = bone_zone.addOrReplaceChild("bone_r1", CubeListBuilder.create().texOffs(36, 15).addBox(-1.0F, -12.0F, -0.5F, 2.0F, 24.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, 12.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, -14.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -2.995F, 0.6831F, -2.7472F));

		PartDefinition bone_r2 = bone_zone.addOrReplaceChild("bone_r2", CubeListBuilder.create().texOffs(36, 15).addBox(-14.6F, -1.0F, 1.5F, 2.0F, 24.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-15.6F, 23.0F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-15.6F, -3.0F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.4996F, -5.6146F, -19.6783F, 0.0715F, 0.2388F, -0.5867F));

		PartDefinition bone_r3 = bone_zone.addOrReplaceChild("bone_r3", CubeListBuilder.create().texOffs(36, 15).addBox(-1.0F, -12.0F, -0.5F, 2.0F, 24.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, 12.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, -14.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.629F, 0.6851F, -19.5989F, 2.2475F, 0.6141F, -2.9384F));

		PartDefinition bone_r4 = bone_zone.addOrReplaceChild("bone_r4", CubeListBuilder.create().texOffs(36, 15).addBox(3.4F, -1.0F, 1.5F, 2.0F, 24.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(2.4F, 23.0F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(2.4F, -3.0F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.1286F, 3.7003F, -36.0794F, 1.1138F, -0.5753F, -0.1693F));

		PartDefinition bone_r5 = bone_zone.addOrReplaceChild("bone_r5", CubeListBuilder.create().texOffs(36, 15).addBox(-1.0F, -12.0F, -0.5F, 2.0F, 24.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, 12.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, -14.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6693F, 1.1063F, -9.4662F, 2.818F, 0.0602F, -3.126F));

		PartDefinition bone_r6 = bone_zone.addOrReplaceChild("bone_r6", CubeListBuilder.create().texOffs(36, 15).addBox(-1.0F, -12.0F, -0.5F, 2.0F, 24.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, 12.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, -14.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-25.8303F, 3.2791F, -18.212F, 1.481F, 1.0526F, 0.6632F));

		PartDefinition bone_r7 = bone_zone.addOrReplaceChild("bone_r7", CubeListBuilder.create().texOffs(36, 15).addBox(-1.0F, -12.0F, -0.5F, 2.0F, 24.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, 12.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, -14.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.8074F, 1.7622F, -6.3981F, 0.554F, 1.0181F, 0.2874F));

		PartDefinition bone_r8 = bone_zone.addOrReplaceChild("bone_r8", CubeListBuilder.create().texOffs(36, 15).addBox(-1.0F, -20.0F, -10.5F, 2.0F, 24.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, 4.0F, -11.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, -22.0F, -11.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2922F, 2.6231F, -21.2802F, 1.1889F, -0.7904F, -0.4918F));

		PartDefinition bone_r9 = bone_zone.addOrReplaceChild("bone_r9", CubeListBuilder.create().texOffs(36, 0).addBox(-1.0F, -4.0F, -7.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, 4.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, -6.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.1688F, -0.8859F, 10.3274F, 0.5811F, 0.7062F, -0.585F));

		PartDefinition bone_r10 = bone_zone.addOrReplaceChild("bone_r10", CubeListBuilder.create().texOffs(36, 0).addBox(-1.0F, -4.0F, -7.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, 4.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, -6.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.2545F, 0.897F, -1.1143F, 0.4652F, -0.875F, 0.0808F));

		PartDefinition bone_r11 = bone_zone.addOrReplaceChild("bone_r11", CubeListBuilder.create().texOffs(36, 0).addBox(-1.0F, -4.0F, -7.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, 4.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, -6.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.2793F, 1.5264F, 0.952F, 0.4555F, 0.2592F, 0.1078F));

		PartDefinition bone_r12 = bone_zone.addOrReplaceChild("bone_r12", CubeListBuilder.create().texOffs(36, 0).addBox(-1.0F, -4.0F, -7.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, 4.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, -6.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.2554F, -0.8929F, -29.0009F, 0.8676F, 0.4252F, 0.0169F));

		PartDefinition bone_r13 = bone_zone.addOrReplaceChild("bone_r13", CubeListBuilder.create().texOffs(36, 0).addBox(-1.0F, -4.0F, -7.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, 4.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, -6.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.013F, 1.2284F, -24.1176F, 0.5481F, -0.8357F, -0.1002F));

		PartDefinition bone_r14 = bone_zone.addOrReplaceChild("bone_r14", CubeListBuilder.create().texOffs(36, 0).addBox(-1.0F, -4.0F, -7.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, 4.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, -6.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.355F, 2.3535F, 10.1544F, -0.224F, -0.8932F, 0.6656F));

		PartDefinition bone_r15 = bone_zone.addOrReplaceChild("bone_r15", CubeListBuilder.create().texOffs(36, 0).addBox(-1.0F, -4.0F, -7.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, 4.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, -6.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.2126F, -1.544F, 7.2198F, 0.4363F, 0.0F, 0.0F));

		PartDefinition bone_r16 = bone_zone.addOrReplaceChild("bone_r16", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -43.0F, 6.5F, 4.0F, 45.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(-3.0F, -47.0F, 6.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(-3.0F, 2.0F, 6.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.1804F, 15.265F, -25.2489F, 0.2649F, 0.6916F, -0.2839F));

		PartDefinition bone_r17 = bone_zone.addOrReplaceChild("bone_r17", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -43.0F, 6.5F, 4.0F, 45.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(-3.0F, -47.0F, 6.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(-3.0F, 2.0F, 6.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.6759F, 29.4898F, -19.2272F, 0.8042F, 0.6726F, -0.1782F));

		PartDefinition bone_r18 = bone_zone.addOrReplaceChild("bone_r18", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -43.0F, 6.5F, 4.0F, 45.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(-3.0F, -47.0F, 6.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(-3.0F, 2.0F, 6.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4675F, 22.5482F, -11.7486F, 0.3556F, -0.536F, 0.1727F));

		PartDefinition bone_r19 = bone_zone.addOrReplaceChild("bone_r19", CubeListBuilder.create().texOffs(36, 15).addBox(-1.0F, -20.0F, 7.5F, 2.0F, 24.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, 4.0F, 7.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 9).addBox(-2.0F, -22.0F, 7.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7874F, 7.456F, -5.7802F, 0.1241F, -0.5543F, 0.3348F));

		PartDefinition bone_r20 = bone_zone.addOrReplaceChild("bone_r20", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -43.0F, 6.5F, 4.0F, 45.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(-3.0F, -47.0F, 6.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(-3.0F, 2.0F, 6.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.5358F, 30.1728F, -3.9485F, 0.7844F, -0.4774F, 0.1658F));

		PartDefinition bone_r21 = bone_zone.addOrReplaceChild("bone_r21", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -43.0F, 6.5F, 4.0F, 45.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(-3.0F, -47.0F, 6.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(-3.0F, 2.0F, 6.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8971F, 25.0125F, -15.9024F, 0.8899F, 0.0513F, -0.3073F));

		PartDefinition bone_r22 = bone_zone.addOrReplaceChild("bone_r22", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -43.0F, 6.5F, 4.0F, 45.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(-3.0F, -47.0F, 6.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(-3.0F, 2.0F, 6.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2962F, 20.7376F, -9.3125F, 0.1636F, 0.096F, -0.4265F));

		PartDefinition bone_r23 = bone_zone.addOrReplaceChild("bone_r23", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -43.0F, 6.5F, 4.0F, 45.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(-3.0F, -47.0F, 6.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(-3.0F, 2.0F, 6.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.0532F, 8.8804F, -15.3504F, 0.3916F, -0.0262F, 0.0832F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone_zone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.bone_zone;
	}
}