package mc.lovexyn0827.mcwmem.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.entity.vehicle.BoatEntity;

@Mixin(BoatEntity.class)
public interface BoatEntityAccessor {
	@Accessor("velocityDecay")
	public float getVelocityDeacyMCWMEM();
}
