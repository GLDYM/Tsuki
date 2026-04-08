package cn.mcmod.tsuki.item;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.client.particle.ParticleRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;

public class SakuraDiamondItem extends Item {
    public SakuraDiamondItem() {
        super(Tsuki.defaultItemProperties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        BlockHitResult hitResult = Item.getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
        BlockPos hitPos = hitResult.getBlockPos();

        if (level.getBlockState(hitPos).is(Blocks.END_PORTAL_FRAME)) {
            return InteractionResultHolder.pass(stack);
        }

        if (level.isClientSide) {
            int j = level.random.nextInt(2) * 2 - 1;
            int k = level.random.nextInt(2) * 2 - 1;

            double x = player.getX() + 0.25D * j;
            double y = player.getY() + 1.0D;
            double z = player.getZ() + 0.25D * k;
            double vx = level.random.nextFloat() * j * 0.1D;
            double vy = level.random.nextFloat() * 0.055D + 0.015D;
            double vz = level.random.nextFloat() * k * 0.1D;

            level.addParticle(ParticleRegistry.SAKURA_LEAF.get(), x, y, z, vx, -vy, vz);
        }

        return InteractionResultHolder.success(stack);
    }
}
