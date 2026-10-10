package cn.mcmod.tsuki.block.entity;

import cn.mcmod.mmlib.block.entity.SyncedBlockEntity;
import cn.mcmod.tsuki.container.CeilingLightContainer;
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

public class CeilingLightBlockEntity extends SyncedBlockEntity implements MenuProvider, GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int color = 0xFFFF00, length = 12, width = 3, transparency = 255, polygonCount = 3;

    public CeilingLightBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.CEILING_LIGHT.get(), pos, state);
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

    public void configure(int color, int length, int width, int transparency, int polygonCount) {
        this.color = color & 0xFFFFFF;
        this.length = Math.clamp(length, 5, 30);
        this.width = Math.clamp(width, 1, 10);
        this.transparency = Math.clamp(transparency, 30, 255);
        this.polygonCount = Math.clamp(polygonCount, 2, 32);
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
        color = tag.contains("Color") ? tag.getInt("Color") & 0xFFFFFF : 0xFFFF00;
        length = Math.clamp(tag.contains("Length") ? tag.getInt("Length") : 12, 5, 30);
        width = Math.clamp(tag.contains("Width") ? tag.getInt("Width") : 3, 1, 10);
        transparency = Math.clamp(tag.contains("Transparency") ? tag.getInt("Transparency") : 255, 30, 255);
        polygonCount = Math.clamp(tag.contains("PolygonCount") ? tag.getInt("PolygonCount") : 3, 2, 10);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new CeilingLightContainer(id, inventory, this);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.tsuki.ceiling_light");
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
