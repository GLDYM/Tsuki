package cn.mcmod.tsuki.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class KitunebiBlock extends LightBlock {
    public static final MapCodec<LightBlock> CODEC = simpleCodec(KitunebiBlock::new);

    public KitunebiBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(LEVEL, 15).setValue(WATERLOGGED, false));
    }

    public KitunebiBlock() {
        this(BlockBehaviour.Properties.of()
                .mapColor(MapColor.NONE)
                .noCollission()
                .noOcclusion()
                .instabreak()
                .lightLevel(state -> 15));
    }

    @Override
    public MapCodec<LightBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        return state == null ? null : state.setValue(LEVEL, 15);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hitResult) {
        // Keep foxfire brightness fixed; unlike vanilla light block, right-click does not cycle level.
        return InteractionResult.CONSUME;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return context.isHoldingItem(BlockItemRegistry.KITUNEBI.get()) ? Shapes.block() : Shapes.empty();
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        Player player = level.getNearestPlayer(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 32.0D, false);
        if (player == null) {
            return;
        }

        if (!player.getMainHandItem().is(BlockItemRegistry.KITUNEBI.get())
                && !player.getOffhandItem().is(BlockItemRegistry.KITUNEBI.get())) {
            return;
        }

        int distance = Math.max(
                Math.abs(pos.getX() - player.blockPosition().getX()),
                Math.max(
                        Math.abs(pos.getY() - player.blockPosition().getY()),
                        Math.abs(pos.getZ() - player.blockPosition().getZ())));
        if (distance > 32) {
            return;
        }

        // Closer blocks reveal more often; at distance 32 this reaches zero.
        float chance = (32.0F - distance) / 32.0F;
        if (chance <= 0.0F || random.nextFloat() >= chance) {
            return;
        }

        double x = pos.getX() + 0.5D;
        double y = pos.getY() + 0.5D;
        double z = pos.getZ() + 0.5D;
        level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK_MARKER, state), x, y, z, 0.0D, 0.0D, 0.0D);
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }
}
