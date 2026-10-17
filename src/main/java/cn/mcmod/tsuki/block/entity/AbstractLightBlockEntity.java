package cn.mcmod.tsuki.block.entity;

import cn.mcmod.mmlib.block.entity.SyncedBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class AbstractLightBlockEntity extends SyncedBlockEntity implements MenuProvider, GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Settings settings;
    private int color;
    private int length;
    private int width;
    private int transparency;
    private int polygonCount;

    protected AbstractLightBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, Settings settings) {
        super(type, pos, state);
        this.settings = settings;
        color = settings.defaultColor() & 0xFFFFFF;
        length = settings.length().defaultValue();
        width = settings.width().defaultValue();
        transparency = settings.transparency().defaultValue();
        polygonCount = settings.polygonCount().defaultValue();
    }

    public int getColor() {
        return color;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getTransparency() {
        return transparency;
    }

    public int getPolygonCount() {
        return polygonCount;
    }

    public int clampLength(int value) {
        return settings.length().clamp(value);
    }

    public int clampWidth(int value) {
        return settings.width().clamp(value);
    }

    public int clampPolygonCount(int value) {
        return settings.polygonCount().clamp(value);
    }

    public void configure(int color, int length, int width, int transparency, int polygonCount) {
        this.color = color & 0xFFFFFF;
        this.length = settings.length().clamp(length);
        this.width = settings.width().clamp(width);
        this.transparency = settings.transparency().clamp(transparency);
        this.polygonCount = settings.polygonCount().clamp(polygonCount);
        setChanged();
        if (level != null)
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("Color", color);
        tag.putInt("Length", length);
        tag.putInt("Width", width);
        tag.putInt("Transparency", transparency);
        tag.putInt("PolygonCount", polygonCount);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        color = tag.contains("Color") ? tag.getInt("Color") & 0xFFFFFF : settings.defaultColor() & 0xFFFFFF;
        length = readSetting(tag, "Length", settings.length());
        width = readSetting(tag, "Width", settings.width());
        transparency = readSetting(tag, "Transparency", settings.transparency());
        polygonCount = readSetting(tag, "PolygonCount", settings.polygonCount());
    }

    private static int readSetting(CompoundTag tag, String key, ValueRange range) {
        return range.clamp(tag.contains(key) ? tag.getInt(key) : range.defaultValue());
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public record ValueRange(int defaultValue, int minimum, int maximum) {
        public int clamp(int value) {
            return Math.clamp(value, minimum, maximum);
        }
    }

    public record Settings(int defaultColor, ValueRange length, ValueRange width, ValueRange transparency,
            ValueRange polygonCount) {
    }
}
