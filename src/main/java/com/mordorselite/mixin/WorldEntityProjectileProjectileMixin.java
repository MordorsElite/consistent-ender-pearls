package com.mordorselite.mixin;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.phys.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

@Mixin(Projectile.class)
public class WorldEntityProjectileProjectileMixin {

    @WrapOperation(
        method = "shoot",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/projectile/Projectile;getMovementToShoot(DDDFF)Lnet/minecraft/world/phys/Vec3;"
        )
    )
    private Vec3 removeEnderpearlUncertainty(Projectile instance, double xd, double yd, double zd, float pow, float uncertainty, Operation<Vec3> original) {
        if (instance instanceof ThrownEnderpearl) {
            uncertainty = 0.0F;
        }
        return original.call(instance, xd, yd, zd, pow, uncertainty);
    }

}