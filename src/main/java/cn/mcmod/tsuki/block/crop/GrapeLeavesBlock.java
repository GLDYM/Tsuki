package cn.mcmod.tsuki.block.crop;

import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.init.item.FoodRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiFoodSet;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

public class GrapeLeavesBlock extends Block implements BonemealableBlock {
    public static final MapCodec<GrapeLeavesBlock> CODEC = simpleCodec(p -> new GrapeLeavesBlock());
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.5D, 16.0D));

    public GrapeLeavesBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.PLANT)
                .strength(2.0F)
                .sound(SoundType.WOOD)
                .noOcclusion()
                .randomTicks());
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
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
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos,
            Player player) {
        return new ItemStack(BlockRegistry.GRAPE_SPLINT.get());
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player,
            InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide()) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        int age = state.getValue(AGE);
        if (age >= 6 && stack.is(Items.SHEARS)) {
            if (age == 6) {
                popResource(level, pos.below(),
                        new ItemStack(FoodRegistry.FOODSET.get(TsukiFoodSet.GRAPE_GREEN).get()));
            } else {
                popResource(level, pos.below(), new ItemStack(FoodRegistry.FOODSET.get(TsukiFoodSet.GRAPE).get()));
            }
            level.setBlock(pos, state.setValue(AGE, 0), 2);
            stack.hurtAndBreak(1, player,
                    hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) {
            return;
        }
        if (level.getRawBrightness(pos.above(), 0) >= 9) {
            int age = state.getValue(AGE);
            spreadToNeighbors(level, pos, age);
            if (age < 7 && CommonHooks.canCropGrow(level, pos, state, random.nextInt(26) == 0)) {
                level.setBlock(pos, state.setValue(AGE, age + 1), 2);
                CommonHooks.fireCropGrowPost(level, pos, state);
            }
        }
    }

    private void spreadToNeighbors(Level level, BlockPos pos, int age) {
        if (age >= 2) {
            BlockPos[] horizontalNeighbors = { pos.east(), pos.north(), pos.west(), pos.south() };
            for (BlockPos neighbor : horizontalNeighbors) {
                if (level.getBlockState(neighbor).is(BlockRegistry.GRAPE_SPLINT.get())) {
                    level.setBlock(neighbor, this.defaultBlockState().setValue(AGE, 0), 2);
                }
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState n = level.getBlockState(pos.north());
        BlockState s = level.getBlockState(pos.south());
        BlockState w = level.getBlockState(pos.west());
        BlockState e = level.getBlockState(pos.east());
        return isGrapeSupport(n) || isGrapeSupport(s) || isGrapeSupport(w) || isGrapeSupport(e);
    }

    private boolean isGrapeSupport(BlockState state) {
        Block block = state.getBlock();
        return block instanceof GrapeLeavesBlock
                || block instanceof GrapeSplintBlock
                || block instanceof GrapeVineBlock;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < 7;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int newAge = Math.min(7, state.getValue(AGE) + Mth.nextInt(random, 2, 5));
        spreadToNeighbors(level, pos, newAge);
        level.setBlock(pos, state.setValue(AGE, newAge), 2);
    }
}
