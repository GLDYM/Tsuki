package cn.mcmod.mmlib.block.entity;

import cn.mcmod.tsuki.tag.TsukiBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public interface HeatableBlockEntity {

    default boolean isHeated(Level level, BlockPos pos) {
        BlockState stateBelow = level.getBlockState(pos.below());
        if (stateBelow.is(this.heatSourceTag())) {
            return stateBelow.hasProperty(BlockStateProperties.LIT)
                    ? stateBelow.getValue(BlockStateProperties.LIT)
                    : true;
        }

        if (!this.requiresDirectHeat() && stateBelow.is(this.heatConductorTag())) {
            BlockState stateFurtherBelow = level.getBlockState(pos.below(2));
            if (stateFurtherBelow.is(this.heatSourceTag())) {
                if (stateFurtherBelow.hasProperty(BlockStateProperties.LIT)) {
                    return stateFurtherBelow.getValue(BlockStateProperties.LIT);
                }
                return true;
            }
        }

        return false;
    }

    default TagKey<Block> heatSourceTag() {
        return TsukiBlockTags.HEAT_SOURCES;
    }

    default TagKey<Block> heatConductorTag() {
        return TsukiBlockTags.HEAT_CONDUCTORS;
    }

    default boolean requiresDirectHeat() {
        return false;
    }
}
