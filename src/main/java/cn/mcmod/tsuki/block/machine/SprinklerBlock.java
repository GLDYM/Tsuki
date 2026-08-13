package cn.mcmod.tsuki.block.machine;

import javax.annotation.Nullable;

import cn.mcmod.tsuki.block.entity.SprinklerBlockEntity;
import cn.mcmod.tsuki.client.particle.ParticleRegistry;
import cn.mcmod.tsuki.init.block.BlockEntityRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SprinklerBlock extends BaseEntityBlock {
    public static final MapCodec<SprinklerBlock> CODEC = simpleCodec(properties -> new SprinklerBlock(properties, Material.IRON));
    public static final BooleanProperty ENABLED = BooleanProperty.create("enabled");
    public static final EnumProperty<Material> MATERIAL = EnumProperty.create("material", Material.class);
    private static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 6, 14);
    private final Material material;

    public enum Material implements StringRepresentable {
        IRON(1, 4200, "iron"),
        GOLD(1, 3600, "gold"),
        DIAMOND(2, 3000, "diamond"),
        SAKURA_DIAMOND(2, 2000, "sakura_diamond");

        private final int growthStages;
        private final int growthRate;
        private final String name;

        Material(int growthStages, int growthRate, String name) {
            this.growthStages = growthStages;
            this.growthRate = growthRate;
            this.name = name;
        }

        public int growthStages() { return growthStages; }
        public int growthRate() { return growthRate; }
        @Override public String getSerializedName() { return name; }
    }

    public SprinklerBlock() {
        this(Material.IRON);
    }

    public SprinklerBlock(Material material) {
        this(Properties.of().mapColor(mapColor(material)).strength(1.5F).sound(SoundType.METAL)
                .noOcclusion().randomTicks(), material);
    }

    private static MapColor mapColor(Material material) {
        return switch (material) {
            case IRON -> MapColor.METAL;
            case GOLD -> MapColor.GOLD;
            case DIAMOND, SAKURA_DIAMOND -> MapColor.DIAMOND;
        };
    }

    private SprinklerBlock(Properties properties, Material material) {
        super(properties);
        this.material = material;
        registerDefaultState(stateDefinition.any().setValue(ENABLED, true).setValue(MATERIAL, material));
    }

    @Override protected MapCodec<? extends BaseEntityBlock> codec() { return CODEC; }
    @Override public RenderShape getRenderShape(BlockState state) { return RenderShape.MODEL; }
    @Override public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos,
            CollisionContext context) { return SHAPE; }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos supportPos = pos.below();
        return level.getBlockState(supportPos).isFaceSturdy(level, supportPos, Direction.UP);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
            LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (direction == Direction.DOWN && !state.canSurvive(level, currentPos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }
    @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityRegistry.SPRINKLER.get().create(pos, state);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hit) {
        if (!(stack.getItem() instanceof HoeItem)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        if (!level.isClientSide) {
            int converted = SprinklerBlockEntity.till(level, pos, player, hand, stack);
            for (int i = 0; i < converted && !player.getAbilities().instabuild; i++) {
                stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            }
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hit) {
        if (!level.isClientSide) {
            level.setBlock(pos, state.cycle(ENABLED), 3);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!state.getValue(ENABLED)) {
            return;
        }

        double angle = Math.toRadians((level.getGameTime() * 12L + random.nextInt(12)) % 360L);
        spawnWaterJet(level, pos, angle);
        spawnWaterJet(level, pos, angle + Math.PI);
    }

    private static void spawnWaterJet(Level level, BlockPos pos, double angle) {
        double velocityX = Math.sin(angle) * 0.18D;
        double velocityZ = Math.cos(angle) * 0.18D;
        level.addParticle(ParticleRegistry.SPRINKLER_WATER.get(),
                pos.getX() + 0.5D, pos.getY() + 0.4D, pos.getZ() + 0.5D,
                velocityX, 0.0D, velocityZ);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ENABLED, MATERIAL);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
            BlockEntityType<T> type) {
        return createTickerHelper(type, BlockEntityRegistry.SPRINKLER.get(), SprinklerBlockEntity::serverTick);
    }
}
