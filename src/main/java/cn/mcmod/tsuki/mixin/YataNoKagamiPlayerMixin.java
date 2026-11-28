package cn.mcmod.tsuki.mixin;

import cn.mcmod.tsuki.item.YataNoKagamiHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Gives the Yata no Kagami its noclip behaviour.
 * <p>
 * Older versions redirected every {@code Player#isSpectator()} call inside {@code tick},
 * {@code aiStep} and {@code updatePlayerPose}. That conflicted with other mods that also
 * redirected the same invocation, so this mixin re-implements the three spectator side
 * effects directly instead of touching {@code isSpectator()} at all.
 */
@Mixin(Player.class)
public abstract class YataNoKagamiPlayerMixin extends LivingEntity {
    protected YataNoKagamiPlayerMixin(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }

    /**
     * Replaces {@code this.noPhysics = this.isSpectator()} / {@code setOnGround(false)} from
     * {@code Player#tick}. {@code aiStep} runs after that assignment and before the entity moves,
     * so setting the flags here keeps the flags for the whole movement step without matching the
     * {@code isSpectator()} instruction.
     */
    @Inject(method = "aiStep", at = @At("HEAD"))
    private void tsuki$applyNoPhysics(CallbackInfo callback) {
        if (YataNoKagamiHelper.isActive((Player) (Object) this)) {
            this.noPhysics = true;
            this.setOnGround(false);
        }
    }

    /**
     * Replaces the {@code isSpectator()} check that stops the player from touching nearby
     * entities in {@code Player#aiStep}. That call site is a very common redirect target for
     * other mods, so hooking {@code touch} avoids the collision entirely.
     */
    @Inject(method = "touch", at = @At("HEAD"), cancellable = true)
    private void tsuki$skipEntityTouch(Entity entity, CallbackInfo callback) {
        if (YataNoKagamiHelper.isActive((Player) (Object) this)) {
            callback.cancel();
        }
    }

    /**
     * Replaces the {@code isSpectator()} check in {@code Player#updatePlayerPose} that keeps the
     * desired pose without testing whether the player fits. With noclip enabled the player never
     * collides, so the desired pose can be applied directly.
     */
    @Inject(method = "updatePlayerPose", at = @At("TAIL"))
    private void tsuki$forceDesiredPose(CallbackInfo callback) {
        Player player = (Player) (Object) this;
        if (!YataNoKagamiHelper.isActive(player) || player.getForcedPose() != null) {
            return;
        }

        Pose pose;
        if (player.isFallFlying()) {
            pose = Pose.FALL_FLYING;
        } else if (player.isSleeping()) {
            pose = Pose.SLEEPING;
        } else if (player.isSwimming()) {
            pose = Pose.SWIMMING;
        } else if (player.isAutoSpinAttack()) {
            pose = Pose.SPIN_ATTACK;
        } else if (player.isShiftKeyDown() && !player.getAbilities().flying) {
            pose = Pose.CROUCHING;
        } else {
            pose = Pose.STANDING;
        }
        player.setPose(pose);
    }
}
