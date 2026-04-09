package cn.mcmod.tsuki.block.foods;

import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod.tsuki.block.machines.CookingPotBlock;
import cn.mcmod.tsuki.tags.TsukiBlockTags;
import cn.mcmod.mmlib.block.entity.HeatableBlockEntity;
import cn.mcmod_mmf.mmlib.item.info.FoodInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NabeBlock extends Block implements HeatableBlockEntity{
    public static final BooleanProperty TRAY_SUPPORT = BooleanProperty.create("tray_support");
    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 3);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D);
    protected static final VoxelShape SHAPE_WITH_TRAY = Shapes.or(SHAPE, Block.box(0.0, -1.0, 0.0, 16.0, 0.0, 16.0));
    private final FoodInfo info;
    public NabeBlock(FoodInfo info) {
        super(BlockBehaviour.Properties.of());
        this.info = info;
        this.registerDefaultState(this.stateDefinition.any().setValue(TRAY_SUPPORT, false).setValue(FACING, Direction.NORTH).setValue(BITES, 0));
    }


    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        if(this.isHeated(level, pos) && rand.nextInt(8) == 0) {
            double x = (double) pos.getX() + 0.5D + (rand.nextDouble() * 0.6D - 0.3D);
            double y = (double) pos.getY() + 0.75D;
            double z = (double) pos.getZ() + 0.5D + (rand.nextDouble() * 0.6D - 0.3D);
            level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
    
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
       return state.getValue(TRAY_SUPPORT) ? SHAPE_WITH_TRAY : SHAPE;
    }
   
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TRAY_SUPPORT, FACING, BITES);
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
       BlockPos pos = context.getClickedPos();
       Level world = context.getLevel();
       BlockState belowBlock = world.getBlockState(pos.below());
       BlockState state = defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
       return state.setValue(TRAY_SUPPORT, belowBlock.is(TsukiBlockTags.TRAY_HEAT_SOURCES));
   }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
       BlockState belowBlock = world.getBlockState(currentPos.below());
       return (BlockState)state.setValue(TRAY_SUPPORT, belowBlock.is(TsukiBlockTags.TRAY_HEAT_SOURCES));
    }
    
    protected ItemInteractionResult eat(LevelAccessor level, BlockPos pos, BlockState state, Player player) {
        if (!isHeated(level, pos)) {
            player.displayClientMessage(Component.translatable("tsuki.block.nabe.not_cooked"), true);
            return ItemInteractionResult.FAIL;
        }else if (!player.canEat(false)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        } else {
            player.getFoodData().eat(this.getFoodInfo().getAmount(), this.getFoodInfo().getCalories());
            int i = state.getValue(BITES);
            level.gameEvent(player, GameEvent.EAT, pos);
            if (i < 3) {
                level.setBlock(pos, state.setValue(BITES, Integer.valueOf(i + 1)), 3);
            } else {
                level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS, 0.8F, 0.8F);
                level.setBlock(
                   pos,
                   BlockRegistry.COOKING_POT.get()
                      .defaultBlockState()
                             .setValue(CookingPotBlock.TRAY_SUPPORT, state.getValue(TRAY_SUPPORT))
                             .setValue(CookingPotBlock.FACING, state.getValue(FACING))
                             .setValue(CookingPotBlock.OPEN, true),
                   3
                );
            }

            return level.isClientSide() ? ItemInteractionResult.SUCCESS : ItemInteractionResult.CONSUME;
        }
    }
    private boolean isHeated(LevelAccessor level, BlockPos pos) {
       BlockState stateBelow = level.getBlockState(pos.below());
       if (stateBelow.is(this.heatSourceTag())) {
          return stateBelow.hasProperty(BlockStateProperties.LIT) ? (Boolean)stateBelow.getValue(BlockStateProperties.LIT) : true;
       } else {
          if (!this.requiresDirectHeat() && stateBelow.is(this.heatConductorTag())) {
             BlockState stateFurtherBelow = level.getBlockState(pos.below(2));
                 if (stateFurtherBelow.is(this.heatSourceTag())) {
                if (stateFurtherBelow.hasProperty(BlockStateProperties.LIT)) {
                   return (Boolean)stateFurtherBelow.getValue(BlockStateProperties.LIT);
                }

                return true;
             }
          }

          return false;
       }
    }
}

