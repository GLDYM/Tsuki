package cn.mcmod.tsuki.mixin;

import cn.mcmod.tsuki.item.YataNoKagamiHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public abstract class YataNoKagamiPlayerMixin extends LivingEntity {
    protected YataNoKagamiPlayerMixin(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isSpectator()Z"))
    private boolean canClipThroughWorld(Player player) {
        return player.isSpectator() || YataNoKagamiHelper.isActive(player);
    }

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isSpectator()Z"))
    private boolean collidesWithEntities(Player player) {
        return player.isSpectator() || YataNoKagamiHelper.isActive(player);
    }

    @Redirect(method = "updatePlayerPose", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isSpectator()Z"))
    private boolean spectatorPose(Player player) {
        return player.isSpectator() || YataNoKagamiHelper.isActive(player);
    }
}
