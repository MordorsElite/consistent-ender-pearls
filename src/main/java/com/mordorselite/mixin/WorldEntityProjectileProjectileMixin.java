package com.mordorselite.mixin;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Projectile.class)
public class WorldEntityProjectileProjectileMixin {

    @Redirect(
        method = "spawnProjectileUsingShoot(Lnet/minecraft/world/entity/projectile/ProjectileFactory;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;DDDFF)Lnet/minecraft/world/entity/projectile/Projectile;",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/Projectile;shoot(DDDFF)V")
    )
    private static void redirectShootFactory(Projectile projectile, double x, double y, double z, float power, float uncertainty) {
        float actualUncertainty = projectile instanceof ThrownEnderpearl ? 0 : uncertainty;
        projectile.shoot(x, y, z, power, actualUncertainty);
    }

    @Redirect(
        method = "spawnProjectileUsingShoot(Lnet/minecraft/world/entity/projectile/Projectile;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;DDDFF)Lnet/minecraft/world/entity/projectile/Projectile;",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/Projectile;shoot(DDDFF)V")
    )
    private static void redirectShootDirect(Projectile projectile, double x, double y, double z, float power, float uncertainty) {
        float actualUncertainty = projectile instanceof ThrownEnderpearl ? 0 : uncertainty;
        projectile.shoot(x, y, z, power, actualUncertainty);
    }
}
