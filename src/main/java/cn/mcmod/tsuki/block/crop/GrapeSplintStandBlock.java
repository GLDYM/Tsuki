package cn.mcmod.tsuki.block.crop;

import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.init.item.ItemRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiNormalItemSet;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GrapeSplintStandBlock extends Block {
    public static final MapCodec<GrapeSplintStandBlock> CODEC = simpleCodec(p -> new GrapeSplintStandBlock());
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D),
            Block.box(0.0D, 12.0D, 7.0D, 16.0D, 13.0D, 9.0D),
            Block.box(7.0D, 12.0D, 0.0D, 9.0D, 13.0D, 16.0D));

    public GrapeSplintStandBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .strength(2.0F)
                .sound(SoundType.WOOD)
                .noOcclusion());
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player,
            InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide())
            return ItemInteractionResult.SUCCESS;

        if (stack.is(ItemRegistry.GRAPE_SEEDS.get())) {
            level.setBlock(pos, BlockRegistry.GRAPE_VINE.get().defaultBlockState(), 3);
            if (!player.isCreative())
                stack.shrink(1);
            return ItemInteractionResult.SUCCESS;
        }
        if (stack.is(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.HOP).get())) {
            level.setBlock(pos, BlockRegistry.HOPS_CROP.get().defaultBlockState(), 3);
            if (!player.isCreative())
                stack.shrink(1);
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
