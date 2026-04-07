package cn.mcmod.sakura.tags;

import cn.mcmod.sakura.SakuraMod;
import cn.mcmod_mmf.mmlib.utils.TagUtils;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class SakuraBlockTags {
    public static final TagKey<Block> TRAY_HEAT_SOURCES = TagUtils.modBlockTag("mysterious_mountain_lib", "tray_heat_sources");
    public static final TagKey<Block> MINEABLE_WITH_KNIFE = TagUtils.modBlockTag(SakuraMod.MODID, "mineable_with_knife");
}
