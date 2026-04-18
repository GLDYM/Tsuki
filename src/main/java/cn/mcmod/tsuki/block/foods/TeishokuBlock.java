
package cn.mcmod.tsuki.block.foods;

import net.minecraft.world.level.block.state.BlockBehaviour;

import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod_mmf.mmlib.item.info.FoodInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TeishokuBlock extends Block {
    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 3);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D);
    private final FoodInfo info;
    public TeishokuBlock(FoodInfo info) {
        super(BlockBehaviour.Properties.of());
        this.info = info;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, BITES);
    }
    public FoodInfo getFoodInfo() {
        return this.info;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemstack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide) {
            if (eat(level, pos, state, player).consumesAction()) {
                return ItemInteractionResult.SUCCESS;
            }
            if (itemstack.isEmpty()) {
                return ItemInteractionResult.CONSUME;
            }
        }
        return eat(level, pos, state, player);
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    protected ItemInteractionResult eat(LevelAccessor level, BlockPos pos, BlockState state, Player player) {
        if (!player.canEat(false)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        } else {
            player.getFoodData().eat(this.getFoodInfo().getAmount(), this.getFoodInfo().getCalories());
            int i = state.getValue(BITES);
            level.gameEvent(player, GameEvent.EAT, pos);
            if (i < 3) {
                level.setBlock(pos, state.setValue(BITES, Integer.valueOf(i + 1)), 3);
            } else {
                level.setBlock(pos, BlockRegistry.TEISHOUKU_FINISHED.get().defaultBlockState().setValue(FACING, state.getValue(FACING)), 3);
            }

            return level.isClientSide() ? ItemInteractionResult.SUCCESS : ItemInteractionResult.CONSUME;
        }
    }
}

