package cn.mcmod.tsuki.tags;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod_mmf.mmlib.utils.TagUtils;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class TsukiBlockTags {
    public static final TagKey<Block> KAWARA_BLOCK = TagUtils.modBlockTag(Tsuki.MODID, "kawara_block");
    public static final TagKey<Block> STRIPPED_LOG = TagUtils.forgeBlockTag("stripped_log");
    public static final TagKey<Block> STRIPPED_WOOD = TagUtils.forgeBlockTag("stripped_wood");
    public static final TagKey<Block> HEAT_SOURCES = TagUtils.modBlockTag(Tsuki.MODID, "heat_sources");
    public static final TagKey<Block> HEAT_CONDUCTORS = TagUtils.modBlockTag(Tsuki.MODID, "heat_conductors");
    public static final TagKey<Block> TRAY_HEAT_SOURCES = TagUtils.modBlockTag(Tsuki.MODID, "tray_heat_sources");
    public static final TagKey<Block> MINEABLE_WITH_KNIFE = TagUtils.modBlockTag(Tsuki.MODID, "mineable_with_knife");
    public static final TagKey<Block> MINEABLE_WITH_HAMMER = TagUtils.modBlockTag(Tsuki.MODID, "mineable_with_hammer");
}
