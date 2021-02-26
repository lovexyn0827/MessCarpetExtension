package mc.lovexyn0827.mcwmem.mixins;

import java.util.List;
import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import mc.lovexyn0827.mcwmem.MCWMEMod;
import mc.lovexyn0827.mcwmem.command.SetExplosionBlockCommand;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.explosion.Explosion;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {
	@ModifyArg(method = "affectWorld",
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"),
			index = 1)
	public BlockState replaceToCustomBlockState(BlockState blockState) {
		BlockState customBlockState = SetExplosionBlockCommand.getBlockState();
		return customBlockState == null?blockState:customBlockState;
	}
	
	@ModifyArg(method = "affectWorld",
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z"),
			index = 1)
	public BlockState replaceToCustomFireState(BlockState fireState) {
		BlockState customFireState = SetExplosionBlockCommand.getFireState();
		return customFireState == null?fireState:customFireState;
	}
	
	@Inject(method = "getExposure",
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/world/World;raycast(Lnet/minecraft/world/RaycastContext;)Lnet/minecraft/util/hit/BlockHitResult;"
					),
			locals = LocalCapture.CAPTURE_FAILHARD
			)
	private static void renderLines(Vec3d source, 
			Entity entity, 
			CallbackInfoReturnable<Float> cir, 
			Box box, 
			double d, double e, double f, double g, double h, int i, int j, float k, float l, float m, double n, double o, double p, 
			Vec3d vec3d
			) {
		if(entity.world instanceof ServerWorld) {
			ServerWorld world = (ServerWorld)(entity.world);
			if(MCWMEMod.INSTANCE.getBooleanOption("entityExplosionRaysVisiblity")) {
				MCWMEMod.INSTANCE.getExplosionRenderer().renderRay(source, vec3d, 
						world.raycast(new RaycastContext(vec3d, source, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, entity)).getType() == HitResult.Type.MISS);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Inject(method = "collectBlocksAndDamageEntities", 
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"
			),
			locals = LocalCapture.CAPTURE_FAILHARD
	)
	public void onExplosionInfluencedEntity(CallbackInfo ci, 
			Set set, 
			float q, int r, int s, int t, int u, int v, int w, 
			List list, 
			Vec3d vec3d, 
			int x, 
			Entity entity, 
			double y, double z, double aa, double ab, double ac, double ad, double ae) {
		if(MCWMEMod.INSTANCE.getBooleanOption("entityExplosionInfluence")) {
			StringBuilder entityInfoBuilder = new StringBuilder(entity.getType().getTranslationKey().replaceFirst("^.+\\u002e", "")).
					append("[").append(entity.getEntityId()).append(",").append(entity.getPos()).append("]");
			MCWMEMod.INSTANCE.sendMessageToEveryone("Affected Entity:", entityInfoBuilder.toString(), "\n", 
					"Exposure:", ad, "\n", 
					"Infulence:", ae);
		}
	}
}
