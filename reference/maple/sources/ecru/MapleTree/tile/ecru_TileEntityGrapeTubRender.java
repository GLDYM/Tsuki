package ecru.MapleTree.tile;

import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ecru_TileEntityGrapeTubRender extends TileEntitySpecialRenderer {
    private static final ResourceLocation tex = new ResourceLocation("mapletree", "textures/blocks/grapeJuice_tub.png");
    private static final ResourceLocation tex2 = new ResourceLocation("mapletree", "textures/blocks/grapeStompTub_side1.png");
    private static final ResourceLocation tex_top = new ResourceLocation("mapletree", "textures/blocks/grapeStompTub_top.png");
    private ecru_numericConstant nc = new ecru_numericConstant();
    private float[] rgb_f = {0.78f, 0.74f, 0.7f, 0.66f, 0.62f, 0.58f, 0.54f, 0.46f, 0.4f, 0.32f, 0.15f};
    private float[] grape_alpha = {0.6f, 0.64f, 0.68f, 0.72f, 0.76f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f};

    public void func_147500_a(TileEntity par1TileEntity, double i, double j, double k, float par8) {
        ecru_TileEntityGrapeTub tile = (ecru_TileEntityGrapeTub) par1TileEntity;
        if ((tile.func_145832_p() & 8) == 8) {
            render_partition(tile, i, j, k, par8);
            render(tile, i, j, k, par8);
        }
    }

    private int getTargetData(World world, int i, int j, int k, int mode) {
        ecru_TileEntityGrapeTub tile = (ecru_TileEntityGrapeTub) world.func_147438_o(i, j, k);
        if (tile != null && world.func_147439_a(tile.getPosX(), tile.getPosY(), tile.getPosZ()) == mod_ecru_MapleTree.blockGrapeStompTub) {
            ecru_TileEntityGrapeTub tile2 = (ecru_TileEntityGrapeTub) world.func_147438_o(tile.getPosX(), tile.getPosY(), tile.getPosZ());
            switch (mode) {
                case 0:
                    return tile2.getTubNum() + 1;
                case 1:
                    return tile2.getGrapeNum();
                case 2:
                    return tile2.getGrapeNumMax() * (tile2.getTubNum() + 1);
                default:
                    return -1;
            }
        }
        return -1;
    }

    private int getStompTime(ecru_TileEntityGrapeTub tile, int mode) {
        if (tile != null && tile.func_145831_w().func_147439_a(tile.getPosX(), tile.getPosY(), tile.getPosZ()) == mod_ecru_MapleTree.blockGrapeStompTub) {
            ecru_TileEntityGrapeTub tile2 = (ecru_TileEntityGrapeTub) tile.func_145831_w().func_147438_o(tile.getPosX(), tile.getPosY(), tile.getPosZ());
            switch (mode) {
                case 0:
                    return tile2.getStompTime();
                case 1:
                    return tile2.getStompTimeMax();
                default:
                    return 0;
            }
        }
        return 0;
    }

    public void render(ecru_TileEntityGrapeTub tile, double i, double j, double k, float par8) {
        double itemNumMax = getTargetData(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 2);
        double itemNum = getTargetData(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 1);
        int tubNum = getTargetData(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 0);
        double pe = itemNum / itemNumMax;
        if (itemNum < 1.0d) {
            return;
        }
        Tessellator tess = Tessellator.field_78398_a;
        func_147499_a(tex);
        GL11.glPushMatrix();
        GL11.glEnable(32826);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        int time = getStompTime(tile, 0);
        int timeMax = getStompTime(tile, 1);
        float rgb_pe = time / timeMax;
        if (rgb_pe > 1.0f) {
            rgb_pe = 1.0f;
        }
        float rgb_pe2 = (int) (rgb_pe * 10.0f);
        GL11.glColor4f(1.0f, this.rgb_f[(int) rgb_pe2], 1.0f, this.grape_alpha[(int) rgb_pe2]);
        tess.func_78382_b();
        tess.func_78375_b(1.0f, 0.0f, 0.0f);
        double V1 = tile.getAnimeCounter() / 32.0d;
        double V2 = (tile.getAnimeCounter() / 32.0d) + 0.03125d;
        double V2a = (tile.getAnimeCounter() / 32.0d) + (0.03125d * (pe / tubNum));
        renderBlocked_side(tile, i, j + this.nc.P08 + 0.0d, k, i + this.nc.P32, j + this.nc.P08 + (pe * this.nc.P16) + 0.01d, k + this.nc.P32, tess, 0.0d, 1.0d, V1, V2a);
        renderBlocked_top(tile, i, j + this.nc.P08 + 0.0d, k, i + this.nc.P32, j + this.nc.P08 + (pe * this.nc.P16) + 0.01d, k + this.nc.P32, tess, 0.0d, 1.0d, V1, V2);
        tess.func_78381_a();
        GL11.glDisable(32826);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    private void renderBlocked_top(ecru_TileEntityGrapeTub tile, double minX, double maxY, double minZ, double maxX, double minY, double maxZ, Tessellator tess, double U1, double U2, double V1, double V2) {
        tess.func_78374_a(minX, minY, minZ, U1, V1);
        tess.func_78374_a(minX, minY, maxZ, U1, V2);
        tess.func_78374_a(maxX, minY, maxZ, U2, V2);
        tess.func_78374_a(maxX, minY, minZ, U2, V1);
    }

    private void renderBlocked_side(ecru_TileEntityGrapeTub tile, double minX, double maxY, double minZ, double maxX, double minY, double maxZ, Tessellator tess, double U1, double U2, double V1, double V2) {
        if (tile.func_145831_w().func_147439_a(tile.field_145851_c - 1, tile.field_145848_d, tile.field_145849_e) == mod_ecru_MapleTree.blockHumanPowerDrive) {
            tess.func_78374_a(minX, minY, minZ, U1, V1);
            tess.func_78374_a(minX, maxY, minZ, U1, V2);
            tess.func_78374_a(minX, maxY, maxZ, U2, V2);
            tess.func_78374_a(minX, minY, maxZ, U2, V1);
        }
        if (tile.func_145831_w().func_147439_a(tile.field_145851_c + 1, tile.field_145848_d, tile.field_145849_e) == mod_ecru_MapleTree.blockHumanPowerDrive) {
            tess.func_78374_a(maxX, minY, maxZ, U1, V1);
            tess.func_78374_a(maxX, maxY, maxZ, U1, V2);
            tess.func_78374_a(maxX, maxY, minZ, U2, V2);
            tess.func_78374_a(maxX, minY, minZ, U2, V1);
        }
        if (tile.func_145831_w().func_147439_a(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e - 1) == mod_ecru_MapleTree.blockHumanPowerDrive) {
            tess.func_78374_a(maxX, minY, minZ, U1, V1);
            tess.func_78374_a(maxX, maxY, minZ, U1, V2);
            tess.func_78374_a(minX, maxY, minZ, U2, V2);
            tess.func_78374_a(minX, minY, minZ, U2, V1);
        }
        if (tile.func_145831_w().func_147439_a(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e + 1) == mod_ecru_MapleTree.blockHumanPowerDrive) {
            tess.func_78374_a(minX, minY, maxZ, U1, V1);
            tess.func_78374_a(minX, maxY, maxZ, U1, V2);
            tess.func_78374_a(maxX, maxY, maxZ, U2, V2);
            tess.func_78374_a(maxX, minY, maxZ, U2, V1);
        }
    }

    public void render_partition(ecru_TileEntityGrapeTub tile, double i, double j, double k, float par8) {
        Tessellator tess = Tessellator.field_78398_a;
        renderBlocked_partition(tile, i, j, k, i + this.nc.P32, (j + this.nc.P32) - 0.004999999888241291d, k + this.nc.P32, tess, 0.0d, 1.0d, 0.0d, 1.0d);
    }

    private void renderBlocked_partition(ecru_TileEntityGrapeTub tile, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, Tessellator tess, double U1, double U2, double V1, double V2) {
        ecru_TileEntityGrapeTub tile2 = null;
        TileEntity _tile = tile.func_145831_w().func_147438_o(tile.field_145851_c - 1, tile.field_145848_d, tile.field_145849_e);
        if (_tile instanceof ecru_TileEntityGrapeTub) {
            tile2 = (ecru_TileEntityGrapeTub) _tile;
        }
        if (tile2 != null && (tile.getPosX() != tile2.getPosX() || tile.getPosY() != tile2.getPosY() || tile.getPosZ() != tile2.getPosZ())) {
            sideInit(tess);
            tess.func_78374_a(minX, maxY, minZ, U1, V1);
            tess.func_78374_a(minX, minY, minZ, U1, V2);
            tess.func_78374_a(minX, minY, maxZ, U2, V2);
            tess.func_78374_a(minX, maxY, maxZ, U2, V1);
            tess.func_78374_a(minX + this.nc.P02, maxY, maxZ, U1, V1);
            tess.func_78374_a(minX + this.nc.P02, minY, maxZ, U1, V2);
            tess.func_78374_a(minX + this.nc.P02, minY, minZ, U2, V2);
            tess.func_78374_a(minX + this.nc.P02, maxY, minZ, U2, V1);
            double UU1 = this.nc.P30;
            tess.func_78374_a(maxX - this.nc.P30, maxY, minZ, UU1, V1);
            tess.func_78374_a(maxX - this.nc.P30, minY, minZ, UU1, V2);
            tess.func_78374_a(minX, minY, minZ, U2, V2);
            tess.func_78374_a(minX, maxY, minZ, U2, V1);
            tess.func_78374_a(minX, maxY, maxZ, UU1, V1);
            tess.func_78374_a(minX, minY, maxZ, UU1, V2);
            tess.func_78374_a(maxX - this.nc.P30, minY, maxZ, U2, V2);
            tess.func_78374_a(maxX - this.nc.P30, maxY, maxZ, U2, V1);
            sideDraw(tess);
            topInit(tess);
            double UU2 = this.nc.P02;
            tess.func_78374_a(minX, maxY, minZ, U1, V1);
            tess.func_78374_a(minX, maxY, maxZ, U1, V2);
            tess.func_78374_a(maxX - this.nc.P30, maxY, maxZ, UU2, V2);
            tess.func_78374_a(maxX - this.nc.P30, maxY, minZ, UU2, V1);
            topDraw(tess);
        }
        ecru_TileEntityGrapeTub tile22 = null;
        TileEntity _tile2 = tile.func_145831_w().func_147438_o(tile.field_145851_c + 1, tile.field_145848_d, tile.field_145849_e);
        if (_tile2 instanceof ecru_TileEntityGrapeTub) {
            tile22 = (ecru_TileEntityGrapeTub) _tile2;
        }
        if (tile22 != null && (tile.getPosX() != tile22.getPosX() || tile.getPosY() != tile22.getPosY() || tile.getPosZ() != tile22.getPosZ())) {
            sideInit(tess);
            tess.func_78374_a(maxX, maxY, maxZ, U1, V1);
            tess.func_78374_a(maxX, minY, maxZ, U1, V2);
            tess.func_78374_a(maxX, minY, minZ, U2, V2);
            tess.func_78374_a(maxX, maxY, minZ, U2, V1);
            tess.func_78374_a(maxX - this.nc.P02, maxY, minZ, U1, V1);
            tess.func_78374_a(maxX - this.nc.P02, minY, minZ, U1, V2);
            tess.func_78374_a(maxX - this.nc.P02, minY, maxZ, U2, V2);
            tess.func_78374_a(maxX - this.nc.P02, maxY, maxZ, U2, V1);
            double UU12 = this.nc.P30;
            tess.func_78374_a(maxX, maxY, minZ, UU12, V1);
            tess.func_78374_a(maxX, minY, minZ, UU12, V2);
            tess.func_78374_a(minX + this.nc.P30, minY, minZ, U2, V2);
            tess.func_78374_a(minX + this.nc.P30, maxY, minZ, U2, V1);
            tess.func_78374_a(minX + this.nc.P30, maxY, maxZ, UU12, V1);
            tess.func_78374_a(minX + this.nc.P30, minY, maxZ, UU12, V2);
            tess.func_78374_a(maxX, minY, maxZ, U2, V2);
            tess.func_78374_a(maxX, maxY, maxZ, U2, V1);
            sideDraw(tess);
            topInit(tess);
            double UU13 = this.nc.P30;
            tess.func_78374_a(minX + this.nc.P30, maxY, minZ, UU13, V1);
            tess.func_78374_a(minX + this.nc.P30, maxY, maxZ, UU13, V2);
            tess.func_78374_a(maxX, maxY, maxZ, U2, V2);
            tess.func_78374_a(maxX, maxY, minZ, U2, V1);
            topDraw(tess);
        }
        ecru_TileEntityGrapeTub tile23 = null;
        TileEntity _tile3 = tile.func_145831_w().func_147438_o(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e - 1);
        if (_tile3 instanceof ecru_TileEntityGrapeTub) {
            tile23 = (ecru_TileEntityGrapeTub) _tile3;
        }
        if (tile23 != null && (tile.getPosX() != tile23.getPosX() || tile.getPosY() != tile23.getPosY() || tile.getPosZ() != tile23.getPosZ())) {
            sideInit(tess);
            tess.func_78374_a(maxX, maxY, minZ, U1, V1);
            tess.func_78374_a(maxX, minY, minZ, U1, V2);
            tess.func_78374_a(minX, minY, minZ, U2, V2);
            tess.func_78374_a(minX, maxY, minZ, U2, V1);
            tess.func_78374_a(minX, maxY, minZ + this.nc.P02, U1, V1);
            tess.func_78374_a(minX, minY, minZ + this.nc.P02, U1, V2);
            tess.func_78374_a(maxX, minY, minZ + this.nc.P02, U2, V2);
            tess.func_78374_a(maxX, maxY, minZ + this.nc.P02, U2, V1);
            double UU14 = this.nc.P30;
            tess.func_78374_a(minX, maxY, minZ, UU14, V1);
            tess.func_78374_a(minX, minY, minZ, UU14, V2);
            tess.func_78374_a(minX, minY, maxZ - this.nc.P30, U2, V2);
            tess.func_78374_a(minX, maxY, maxZ - this.nc.P30, U2, V1);
            tess.func_78374_a(maxX, maxY, maxZ - this.nc.P30, UU14, V1);
            tess.func_78374_a(maxX, minY, maxZ - this.nc.P30, UU14, V2);
            tess.func_78374_a(maxX, minY, minZ, U2, V2);
            tess.func_78374_a(maxX, maxY, minZ, U2, V1);
            sideDraw(tess);
            topInit(tess);
            double VV2 = this.nc.P02;
            tess.func_78374_a(minX, maxY, minZ, U1, V1);
            tess.func_78374_a(minX, maxY, maxZ - this.nc.P30, U1, VV2);
            tess.func_78374_a(maxX, maxY, maxZ - this.nc.P30, U2, VV2);
            tess.func_78374_a(maxX, maxY, minZ, U2, V1);
            topDraw(tess);
        }
        ecru_TileEntityGrapeTub tile24 = null;
        TileEntity _tile4 = tile.func_145831_w().func_147438_o(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e + 1);
        if (_tile4 instanceof ecru_TileEntityGrapeTub) {
            tile24 = (ecru_TileEntityGrapeTub) _tile4;
        }
        if (tile24 != null) {
            if (tile.getPosX() != tile24.getPosX() || tile.getPosY() != tile24.getPosY() || tile.getPosZ() != tile24.getPosZ()) {
                sideInit(tess);
                tess.func_78374_a(minX, maxY, maxZ, U1, V1);
                tess.func_78374_a(minX, minY, maxZ, U1, V2);
                tess.func_78374_a(maxX, minY, maxZ, U2, V2);
                tess.func_78374_a(maxX, maxY, maxZ, U2, V1);
                tess.func_78374_a(maxX, maxY, maxZ - this.nc.P02, U1, V1);
                tess.func_78374_a(maxX, minY, maxZ - this.nc.P02, U1, V2);
                tess.func_78374_a(minX, minY, maxZ - this.nc.P02, U2, V2);
                tess.func_78374_a(minX, maxY, maxZ - this.nc.P02, U2, V1);
                double UU15 = this.nc.P30;
                tess.func_78374_a(minX, maxY, minZ + this.nc.P30, UU15, V1);
                tess.func_78374_a(minX, minY, minZ + this.nc.P30, UU15, V2);
                tess.func_78374_a(minX, minY, maxZ, U2, V2);
                tess.func_78374_a(minX, maxY, maxZ, U2, V1);
                tess.func_78374_a(maxX, maxY, maxZ, UU15, V1);
                tess.func_78374_a(maxX, minY, maxZ, UU15, V2);
                tess.func_78374_a(maxX, minY, minZ + this.nc.P30, U2, V2);
                tess.func_78374_a(maxX, maxY, minZ + this.nc.P30, U2, V1);
                sideDraw(tess);
                topInit(tess);
                double VV1 = this.nc.P30;
                tess.func_78374_a(minX, maxY, minZ + this.nc.P30, U1, VV1);
                tess.func_78374_a(minX, maxY, maxZ, U1, V2);
                tess.func_78374_a(maxX, maxY, maxZ, U2, V2);
                tess.func_78374_a(maxX, maxY, minZ + this.nc.P30, U2, VV1);
                topDraw(tess);
            }
        }
    }

    void sideInit(Tessellator tess) {
        GL11.glPushMatrix();
        tess.func_78386_a(1.0f, 1.0f, 1.0f);
        GL11.glEnable(32826);
        func_147499_a(tex2);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        tess.func_78382_b();
        tess.func_78375_b(0.0f, 0.0f, 1.0f);
    }

    void sideDraw(Tessellator tess) {
        tess.func_78381_a();
        GL11.glDisable(32826);
        GL11.glPopMatrix();
    }

    void topInit(Tessellator tess) {
        GL11.glPushMatrix();
        tess.func_78386_a(1.0f, 1.0f, 1.0f);
        GL11.glEnable(32826);
        func_147499_a(tex_top);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        tess.func_78382_b();
        tess.func_78375_b(0.0f, 0.0f, 1.0f);
    }

    void topDraw(Tessellator tess) {
        tess.func_78381_a();
        GL11.glPopMatrix();
        GL11.glDisable(32826);
    }
}
