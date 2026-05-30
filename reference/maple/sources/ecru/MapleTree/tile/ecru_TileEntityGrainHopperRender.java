package ecru.MapleTree.tile;

import ecru.MapleTree.common.ecru_numericConstant;
import java.util.Random;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ecru_TileEntityGrainHopperRender extends TileEntitySpecialRenderer {
    private static final ResourceLocation tex = new ResourceLocation("mapletree", "textures/blocks/grainHopper_contents.png");
    private ecru_numericConstant nc = new ecru_numericConstant();
    private final Random random = new Random();

    public void func_147500_a(TileEntity par1TileEntity, double i, double j, double k, float par8) {
        ecru_TileEntityGrainHopper tile = (ecru_TileEntityGrainHopper) par1TileEntity;
        render(par1TileEntity, i, j, k, par8, tile);
    }

    public void render(TileEntity par1TileEntity, double i, double j, double k, float par8, ecru_TileEntityGrainHopper tile) {
        double itemNum = tile.dt_itemNum;
        double itemNumMax = tile.dt_itemNumMax;
        double pe = itemNum / itemNumMax;
        if (itemNum < 1.0d) {
            return;
        }
        Tessellator tess = Tessellator.field_78398_a;
        func_147499_a(tex);
        GL11.glPushMatrix();
        GL11.glTexParameterf(3553, 10242, 10497.0f);
        GL11.glTexParameterf(3553, 10243, 10497.0f);
        GL11.glDisable(2896);
        GL11.glDisable(2884);
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        GL11.glBlendFunc(770, 1);
        tess.func_78382_b();
        tess.func_78370_a(255, 255, 255, 255);
        double OFFSET = this.nc.P01;
        renderBlocked_(i + this.nc.P02, j + this.nc.P08 + OFFSET, k + this.nc.P02, i + this.nc.P30, j + this.nc.P08 + (pe * this.nc.P22) + OFFSET, k + this.nc.P30, tess, 0.0d, 1.0d, 0.0d, pe);
        renderBlocked_top(i + this.nc.P02, j + this.nc.P08 + OFFSET, k + this.nc.P02, i + this.nc.P30, j + this.nc.P08 + (pe * this.nc.P22) + OFFSET, k + this.nc.P30, tess, 0.0d, 1.0d, 0.0d, 1.0d);
        tess.func_78381_a();
        GL11.glEnable(2884);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
    }

    private void renderBlocked_top(double minX, double maxY, double minZ, double maxX, double minY, double maxZ, Tessellator tess, double U1, double U2, double V1, double V2) {
        tess.func_78374_a(minX, minY, minZ, U1, V1);
        tess.func_78374_a(minX, minY, maxZ, U1, V2);
        tess.func_78374_a(maxX, minY, maxZ, U2, V2);
        tess.func_78374_a(maxX, minY, minZ, U2, V1);
        tess.func_78374_a(minX, maxY, maxZ, U1, V1);
        tess.func_78374_a(minX, maxY, minZ, U1, V2);
        tess.func_78374_a(maxX, maxY, minZ, U2, V2);
        tess.func_78374_a(maxX, maxY, maxZ, U2, V1);
    }

    private void renderBlocked_(double minX, double maxY, double minZ, double maxX, double minY, double maxZ, Tessellator tess, double U1, double U2, double V1, double V2) {
        tess.func_78374_a(minX, minY, minZ, U1, V1);
        tess.func_78374_a(minX, maxY, minZ, U1, V2);
        tess.func_78374_a(minX, maxY, maxZ, U2, V2);
        tess.func_78374_a(minX, minY, maxZ, U2, V1);
        tess.func_78374_a(maxX, minY, maxZ, U1, V1);
        tess.func_78374_a(maxX, maxY, maxZ, U1, V2);
        tess.func_78374_a(maxX, maxY, minZ, U2, V2);
        tess.func_78374_a(maxX, minY, minZ, U2, V1);
        tess.func_78374_a(maxX, minY, minZ, U1, V1);
        tess.func_78374_a(maxX, maxY, minZ, U1, V2);
        tess.func_78374_a(minX, maxY, minZ, U2, V2);
        tess.func_78374_a(minX, minY, minZ, U2, V1);
        tess.func_78374_a(minX, minY, maxZ, U1, V1);
        tess.func_78374_a(minX, maxY, maxZ, U1, V2);
        tess.func_78374_a(maxX, maxY, maxZ, U2, V2);
        tess.func_78374_a(maxX, minY, maxZ, U2, V1);
    }
}
