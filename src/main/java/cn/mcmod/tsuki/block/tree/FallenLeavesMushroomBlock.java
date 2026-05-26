package cn.mcmod.tsuki.block.tree;

import cn.mcmod.tsuki.init.item.FoodRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiFoodSet;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class FallenLeavesMushroomBlock extends FallenLeavesBlock {
    private static final MapCodec<FallenLeavesMushroomBlock> MUSHROOM_CODEC = simpleCodec(
            properties -> new FallenLeavesMushroomBlock(Type.MUSHROOM, properties));
    private static final MapCodec<FallenLeavesMushroomBlock> MATSUTAKE_CODEC = simpleCodec(
            properties -> new FallenLeavesMushroomBlock(Type.MATSUTAKE, properties));
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    public enum Type {
        MUSHROOM,
        MATSUTAKE
    }

    private final Type type;

    public FallenLeavesMushroomBlock(Type type) {
        this(type, BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .strength(0.2F)
                .randomTicks()
                .sound(SoundType.GRASS)
                .noOcclusion());
    }

    private FallenLeavesMushroomBlock(Type type, BlockBehaviour.Properties properties) {
        super(properties);
        this.type = type;
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0));
    }

    public Type getMushroomType() {
        return type;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AGE);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 3;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(AGE) >= 3) {
            return;
        }
        if (level.getRawBrightness(pos, 0) < 9) {
            return;
        }
        if (random.nextInt(8) != 0) {
            return;
        }
        level.setBlock(pos, state.setValue(AGE, state.getValue(AGE) + 1), 2);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hitResult) {
        if (state.getValue(AGE) < 3) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide) {
            ItemStack normal;

            switch (level.random.nextInt(4)) {
                case 0 -> normal = new ItemStack(FoodRegistry.FOODSET.get(TsukiFoodSet.EDODES).get(),
                        5 + level.random.nextInt(3));
                case 1 -> normal = new ItemStack(FoodRegistry.FOODSET.get(TsukiFoodSet.SHIMEJI).get(),
                        5 + level.random.nextInt(3));
                case 2 -> normal = new ItemStack(Items.BROWN_MUSHROOM, 5 + level.random.nextInt(3));
                case 3 -> normal = new ItemStack(Items.RED_MUSHROOM, 5 + level.random.nextInt(3));
                default -> normal = new ItemStack(FoodRegistry.FOODSET.get(TsukiFoodSet.EDODES).get(),
                        5 + level.random.nextInt(3));
            }
            ItemStack drop = type == Type.MATSUTAKE
                    ? new ItemStack(FoodRegistry.FOODSET.get(TsukiFoodSet.MATSUTAKE).get(), 5 + level.random.nextInt(3))
                    : normal;
            popResource(level, pos, drop);
            level.setBlock(pos, state.setValue(AGE, 1), 2);
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.9F);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < 3;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int current = state.getValue(AGE);
        int next = Mth.clamp(current + Mth.nextInt(random, 1, 2), 0, 3);
        level.setBlock(pos, state.setValue(AGE, next), 2);
    }

    @Override
    protected MapCodec<? extends FallenLeavesBlock> codec() {
        return type == Type.MATSUTAKE ? MATSUTAKE_CODEC : MUSHROOM_CODEC;
    }
}
