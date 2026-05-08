package cn.mcmod.tsuki.tag;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.mmlib.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class TsukiBlockTags {
    public static final TagKey<Block> KAWARA_BLOCK = TagUtil.modBlockTag(Tsuki.MODID, "kawara_block");
    public static final TagKey<Block> STRIPPED_LOG = TagUtil.forgeBlockTag("stripped_logs");
    public static final TagKey<Block> STRIPPED_WOOD = TagUtil.forgeBlockTag("stripped_woods");
    public static final TagKey<Block> HEAT_SOURCES = TagUtil.modBlockTag(Tsuki.MODID, "heat_sources");
    public static final TagKey<Block> HEAT_CONDUCTORS = TagUtil.modBlockTag(Tsuki.MODID, "heat_conductors");
    public static final TagKey<Block> TRAY_HEAT_SOURCES = TagUtil.modBlockTag(Tsuki.MODID, "tray_heat_sources");
    public static final TagKey<Block> MINEABLE_WITH_KNIFE = TagUtil.forgeBlockTag("mineable/knife");
    public static final TagKey<Block> MINEABLE_WITH_HAMMER = TagUtil.forgeBlockTag("mineable/hammer");
}
