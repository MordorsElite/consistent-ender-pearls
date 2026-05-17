package com.mordorselite.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Projectile.class)
public class WorldEntityProjectileProjectileMixin {

    @Inject(
        method = "shoot",
        at = @At(value = "INVOKE")
    )
    private static void removeEnderpearlUncertainty(final double xd, final double yd, final double zd, final float pow, final float uncertainty, CallbackInfo ci) {
        double neverUsed = xd + yd;
    }
}