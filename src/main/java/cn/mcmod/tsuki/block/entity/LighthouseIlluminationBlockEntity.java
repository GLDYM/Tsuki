package cn.mcmod.tsuki.block.entity;

import cn.mcmod.mmlib.block.entity.SyncedBlockEntity;
import cn.mcmod.tsuki.container.LighthouseIlluminationContainer;
import cn.mcmod.tsuki.init.block.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class LighthouseIlluminationBlockEntity extends SyncedBlockEntity implements MenuProvider, GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int color = 0xFFF0A0;
    private int length = 16;
    private int width = 4;
    private int transparency = 160;

    public LighthouseIlluminationBlockEntity(BlockPos pos, BlockState state) { super(BlockEntityRegistry.LIGHTHOUSE_ILLUMINATION.get(), pos, state); }
    public int getColor() { return color; }
    public int getLength() { return length; }
    public int getWidth() { return width; }
    public int getTransparency() { return transparency; }
    public void configure(int color, int length, int width, int transparency) {
        this.color = color & 0xFFFFFF; this.length = Math.clamp(length, 1, 31); this.width = Math.clamp(width, 1, 15); this.transparency = Math.clamp(transparency, 0, 255);
        setChanged();
        if (level != null) level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }
    @Override protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) { super.saveAdditional(tag, registries); tag.putInt("Color", color); tag.putInt("Length", length); tag.putInt("Width", width); tag.putInt("Transparency", transparency); }
    @Override protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) { super.loadAdditional(tag, registries); color = tag.getInt("Color"); length = Math.clamp(tag.getInt("Length"), 1, 31); width = Math.clamp(tag.getInt("Width"), 1, 15); transparency = Math.clamp(tag.getInt("Transparency"), 0, 255); }
    @Override public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) { return new LighthouseIlluminationContainer(id, inventory, this); }
    @Override public Component getDisplayName() { return Component.translatable("container.tsuki.lighthouse_illumination"); }
    @Override public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}
    @Override public AnimatableInstanceCache getAnimatableInstanceCache() { return cache; }
}
