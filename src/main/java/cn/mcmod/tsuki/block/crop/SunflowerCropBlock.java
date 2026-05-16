package cn.mcmod.tsuki.block.crop;

import cn.mcmod.tsuki.init.block.BlockEntityRegistry;
import cn.mcmod.tsuki.init.item.ItemRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

public class SunflowerCropBlock extends CropBlock implements EntityBlock {
    public static final MapCodec<SunflowerCropBlock> CODEC = simpleCodec(SunflowerCropBlock::new);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    public static final EnumProperty<Part> PART = EnumProperty.create("part", Part.class);

    private static final VoxelShape LOWER_STAGE0_SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 4.0D, 12.0D);
    private static final VoxelShape LOWER_STAGE1_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 12.0D, 13.0D);
    private static final VoxelShape STEM_SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    private static final VoxelShape FLOWER_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public SunflowerCropBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(PART, Part.LOWER));
    }

    @Override
    public MapCodec<? extends CropBlock> codec() {
        return CODEC;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ItemRegistry.SUNFLOWER_SEEDS.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 7;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, PART);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return super.mayPlaceOn(state, level, pos) || state.is(BlockTags.DIRT);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityRegistry.SUNFLOWER_CROP.get().create(pos, state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(PART)) {
            case LOWER -> switch (state.getValue(AGE)) {
                case 0 -> LOWER_STAGE0_SHAPE;
                case 1 -> LOWER_STAGE1_SHAPE;
                default -> STEM_SHAPE;
            };
            case MIDDLE -> STEM_SHAPE;
            case UPPER -> FLOWER_SHAPE;
        };
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return switch (state.getValue(PART)) {
            case LOWER -> this.mayPlaceOn(level.getBlockState(pos.below()), level, pos.below());
            case MIDDLE -> isStemBlock(level.getBlockState(pos.below()));
            case UPPER -> level.getBlockState(pos.below()).is(this)
                    && level.getBlockState(pos.below()).getValue(PART) == Part.MIDDLE;
        };
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level,
            BlockPos pos, BlockPos neighborPos) {
        return state.canSurvive(level, pos) ? super.updateShape(state, direction, neighborState, level, pos, neighborPos)
                : Blocks.AIR.defaultBlockState();
    }

    // TODO: DUPILICATE?
    @Override
    public boolean isRandomlyTicking(BlockState state) {
        Part part = state.getValue(PART);
        int age = state.getValue(AGE);
        return part != Part.UPPER || age < this.getMaxAge();
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1) || level.getRawBrightness(pos.above(), 0) < 9) {
            return;
        }

        Part part = state.getValue(PART);
        int age = state.getValue(AGE);
        BlockPos basePos = getBasePos(state, pos);
        float growthSpeed = getGrowthSpeed(level.getBlockState(basePos), level, basePos);
        boolean canGrow = CommonHooks.canCropGrow(level, pos, state,
                random.nextInt((int) (25.0F / growthSpeed) + 1) == 0);

        if (!canGrow) {
            return;
        }

        if (part == Part.LOWER && age < 2) {
            if (age == 1) {
                if (canFormTall(level, pos)) {
                    BlockState original = state;
                    placeTall(level, pos, 3);
                    CommonHooks.fireCropGrowPost(level, pos, original);
                }
            } else {
                BlockState grown = state.setValue(AGE, age + 1);
                level.setBlock(pos, grown, 2);
                CommonHooks.fireCropGrowPost(level, pos, state);
            }

            return;
        }

        if (age == 2) {
            tryGrowUpper(level, basePos);
            CommonHooks.fireCropGrowPost(level, pos, state);
            return;
        }

        if (part == Part.UPPER && age < this.getMaxAge()) {
            level.setBlock(pos, state.setValue(AGE, age + 1), 2);
            CommonHooks.fireCropGrowPost(level, pos, state);
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        Part part = state.getValue(PART);
        int age = state.getValue(AGE);

        if (part == Part.UPPER) {
            return age >= 3 && age < this.getMaxAge();
        }

        if (age == 2) {
            return getGrowableUpper(level, getBasePos(state, pos)) != null;
        }

        if (part == Part.LOWER) {
            if (age == 1) {
                return canFormTall(level, pos);
            }

            return age < 1;
        }

        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        Part part = state.getValue(PART);
        int age = state.getValue(AGE);

        if (part == Part.UPPER) {
            level.setBlock(pos, state.setValue(AGE, Math.min(this.getMaxAge(), age + getBonemealAgeIncrease(level))), 2);
            return;
        }

        if (age == 2) {
            BlockPos upperPos = getGrowableUpper(level, getBasePos(state, pos));
            if (upperPos != null) {
                BlockState upperState = level.getBlockState(upperPos);
                int grownAge = Math.min(this.getMaxAge(), upperState.getValue(AGE) + getBonemealAgeIncrease(level));
                level.setBlock(upperPos, upperState.setValue(AGE, grownAge), 2);
            }
            return;
        }

        if (part != Part.LOWER) {
            return;
        }

        int targetAge = Math.min(this.getMaxAge(), age + getBonemealAgeIncrease(level));

        if (targetAge <= 1) {
            level.setBlock(pos, state.setValue(AGE, targetAge), 2);
            return;
        }

        if (!canFormTall(level, pos)) {
            return;
        }

        int upperAge = Math.min(this.getMaxAge(), 3 + Math.max(0, targetAge - 2));
        placeTall(level, pos, upperAge);
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide()) {
            Part part = state.getValue(PART);
            BlockPos basePos = getBasePos(state, pos);
            BlockPos middlePos = basePos.above();
            BlockPos upperPos = basePos.above(2);
            BlockState upperState = level.getBlockState(upperPos);

            if (player.isCreative()) {
                removeOtherParts(level, pos, state, basePos, middlePos, upperPos);
            } else if (part != Part.UPPER && isMatureUpper(upperState)) {
                dropResources(upperState, level, upperPos, null, player, player.getMainHandItem());
                removeOtherParts(level, pos, state, basePos, middlePos, upperPos);
            } else if (part == Part.UPPER) {
                removeLowerParts(level, basePos, middlePos);
            }
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
    }

    private static void removeOtherParts(Level level, BlockPos currentPos, BlockState currentState, BlockPos basePos,
            BlockPos middlePos, BlockPos upperPos) {
        if (!currentPos.equals(basePos)) {
            BlockState baseState = level.getBlockState(basePos);
            if (baseState.is(currentState.getBlock())) {
                level.setBlock(basePos, Blocks.AIR.defaultBlockState(), 35);
            }
        }

        if (!currentPos.equals(middlePos)) {
            BlockState middleState = level.getBlockState(middlePos);
            if (middleState.is(currentState.getBlock())) {
                level.setBlock(middlePos, Blocks.AIR.defaultBlockState(), 35);
            }
        }

        if (!currentPos.equals(upperPos)) {
            BlockState upperState = level.getBlockState(upperPos);
            if (upperState.is(currentState.getBlock())) {
                level.setBlock(upperPos, Blocks.AIR.defaultBlockState(), 35);
            }
        }
    }

    private static void removeLowerParts(Level level, BlockPos basePos, BlockPos middlePos) {
        BlockState baseState = level.getBlockState(basePos);
        if (isStemBlock(baseState)) {
            level.setBlock(basePos, Blocks.AIR.defaultBlockState(), 35);
        }

        BlockState middleState = level.getBlockState(middlePos);
        if (isStemBlock(middleState)) {
            level.setBlock(middlePos, Blocks.AIR.defaultBlockState(), 35);
        }
    }

    private static boolean isStemBlock(BlockState state) {
        return state.getBlock() instanceof SunflowerCropBlock
                && state.getValue(PART) != Part.UPPER;
    }

    private static boolean isMatureUpper(BlockState state) {
        return state.getBlock() instanceof SunflowerCropBlock
                && state.getValue(PART) == Part.UPPER
                && state.getValue(AGE) >= 3;
    }

    private BlockPos getGrowableUpper(LevelReader level, BlockPos basePos) {
        BlockPos upperPos = basePos.above(2);
        BlockState upperState = level.getBlockState(upperPos);
        if (upperState.is(this) && upperState.getValue(PART) == Part.UPPER) {
            int upperAge = upperState.getValue(AGE);
            if (upperAge >= 3 && upperAge < 7) {
                return upperPos;
            }
        }

        return null;
    }

    private void tryGrowUpper(ServerLevel level, BlockPos basePos) {
        BlockPos upperPos = getGrowableUpper(level, basePos);
        if (upperPos == null) {
            return;
        }

        BlockState upperState = level.getBlockState(upperPos);
        level.setBlock(upperPos, upperState.setValue(AGE, upperState.getValue(AGE) + 1), 2);
    }

    private boolean canFormTall(LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.above()).isAir() && level.getBlockState(pos.above(2)).isAir();
    }

    private void placeTall(LevelAccessor level, BlockPos basePos, int upperAge) {
        BlockState lower = this.defaultBlockState().setValue(PART, Part.LOWER).setValue(AGE, 2);
        BlockState middle = this.defaultBlockState().setValue(PART, Part.MIDDLE).setValue(AGE, 2);
        BlockState upper = this.defaultBlockState().setValue(PART, Part.UPPER).setValue(AGE, upperAge);
        level.setBlock(basePos, lower, 2);
        level.setBlock(basePos.above(), middle, 2);
        level.setBlock(basePos.above(2), upper, 2);
    }

    private static BlockPos getBasePos(BlockState state, BlockPos pos) {
        return switch (state.getValue(PART)) {
            case LOWER -> pos;
            case MIDDLE -> pos.below();
            case UPPER -> pos.below(2);
        };
    }

    protected int getBonemealAgeIncrease(Level level) {
        return Mth.nextInt(level.random, 1, 5);
    }

    public enum Part implements StringRepresentable {
        LOWER("lower"),
        MIDDLE("middle"),
        UPPER("upper");

        private final String name;

        Part(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
