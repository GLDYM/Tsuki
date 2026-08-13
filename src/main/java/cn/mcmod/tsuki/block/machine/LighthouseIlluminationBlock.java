package cn.mcmod.tsuki.block.machine;

import cn.mcmod.tsuki.block.entity.LighthouseIlluminationBlockEntity;
import cn.mcmod.tsuki.init.block.BlockEntityRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LighthouseIlluminationBlock extends BaseEntityBlock {
    public static final MapCodec<LighthouseIlluminationBlock> CODEC = simpleCodec(LighthouseIlluminationBlock::new);
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    private static final VoxelShape SHAPE = box(2, 0, 2, 14, 16, 14);

    public LighthouseIlluminationBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(LIT, false));
    }

    public LighthouseIlluminationBlock() { this(Properties.of().strength(3.5F).noOcclusion().lightLevel(state -> state.getValue(LIT) ? 15 : 0)); }

    @Override protected MapCodec<? extends BaseEntityBlock> codec() { return CODEC; }
    @Override public RenderShape getRenderShape(BlockState state) { return RenderShape.ENTITYBLOCK_ANIMATED; }
    @Override public VoxelShape getShape(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos, CollisionContext context) { return SHAPE; }
    @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return BlockEntityRegistry.LIGHTHOUSE_ILLUMINATION.get().create(pos, state); }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player,
            InteractionHand hand, BlockHitResult hit) {
        if (player.isShiftKeyDown()) {
            if (!level.isClientSide()) level.setBlock(pos, state.cycle(LIT), 3);
            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        }
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof LighthouseIlluminationBlockEntity lighthouse) {
            ((ServerPlayer) player).openMenu(lighthouse, pos);
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) { builder.add(LIT); }
}
