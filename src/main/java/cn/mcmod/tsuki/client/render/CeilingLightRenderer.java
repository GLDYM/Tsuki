package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.block.entity.CeilingLightBlockEntity;
import cn.mcmod.tsuki.block.machine.CeilingLightBlock;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.phys.Vec3;

public class CeilingLightRenderer extends AbstractLightRenderer<CeilingLightBlockEntity> {
    public CeilingLightRenderer(BlockEntityRendererProvider.Context context) {
        super(new CeilingLightGeoModel());
    }

    @Override
    protected boolean isLit(CeilingLightBlockEntity entity) {
        return entity.getBlockState().getValue(CeilingLightBlock.LIT);
    }

    @Override
    protected Vec3 getBeamDirection(CeilingLightBlockEntity entity) {
        return new Vec3(0, -1, 0);
    }
}
