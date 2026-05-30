package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockDriedPersimmon;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderDriedPersimmon implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        iblockaccess.func_72805_g(blockX, blockY, blockZ);
        renderPole(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        render(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        renderblocks.func_147771_a();
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147771_a();
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderDriedPersimmonID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    private void render(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78380_c(block.func_149677_c(iblockaccess, blockX, blockY, blockZ));
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        int l = block.func_149720_d(iblockaccess, blockX, blockY, blockZ);
        float f1 = ((l >> 16) & 255) / 255.0f;
        float f2 = ((l >> 8) & 255) / 255.0f;
        float f3 = (l & 255) / 255.0f;
        if (EntityRenderer.field_78517_a) {
            float f4 = (((f1 * 30.0f) + (f2 * 59.0f)) + (f3 * 11.0f)) / 100.0f;
            float f5 = ((f1 * 30.0f) + (f2 * 70.0f)) / 100.0f;
            float f6 = ((f1 * 30.0f) + (f3 * 70.0f)) / 100.0f;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }
        tessellator.func_78386_a(1.0f * f1, 1.0f * f2, 1.0f * f3);
        double d0 = blockX;
        double d1 = blockY;
        double d2 = blockZ;
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        drawDriedPersimmons((ecru_BlockDriedPersimmon) block, meta, d0, d1, d2, 1.0f, iblockaccess, renderblocks);
    }

    public void drawDriedPersimmons(ecru_BlockDriedPersimmon par1Block, int meta, double i, double j, double k, float par9, IBlockAccess iblockaccess, RenderBlocks renderblocks) {
        int num;
        int tmpxz = Math.abs((((((int) i) % 10) + (((int) j) % 10)) + (((int) k) % 10)) % 10);
        double[] dp1_xmin = {this.nc.P03, this.nc.P04, this.nc.P05};
        double[] dp1_xmax = {this.nc.P13, this.nc.P12, this.nc.P11};
        double[] dp1_ymin = {this.nc.P16, this.nc.P17, this.nc.P17};
        double[] dp1_ymax = {this.nc.P27, this.nc.P27, this.nc.P27};
        double[] dp1_zmin = {this.nc.P09, this.nc.P10, this.nc.P11};
        double[] dp1_zmax = {this.nc.P19, this.nc.P18, this.nc.P17};
        double[] dp2_xmin = {this.nc.P20, this.nc.P21, this.nc.P22};
        double[] dp2_xmax = {this.nc.P30, this.nc.P29, this.nc.P28};
        double[] dp2_ymin = {this.nc.P15, this.nc.P16, this.nc.P16};
        double[] dp2_ymax = {this.nc.P26, this.nc.P26, this.nc.P26};
        double[] dp2_zmin = {this.nc.P06, this.nc.P07, this.nc.P08};
        double[] dp2_zmax = {this.nc.P16, this.nc.P15, this.nc.P14};
        double[] dp3_xmin = {this.nc.P04, this.nc.P05, this.nc.P06};
        double[] dp3_xmax = {this.nc.P14, this.nc.P13, this.nc.P12};
        double[] dp3_ymin = {this.nc.P02, this.nc.P03, this.nc.P03};
        double[] dp3_ymax = {this.nc.P13, this.nc.P13, this.nc.P13};
        double[] dp3_zmin = {this.nc.P06, this.nc.P07, this.nc.P08};
        double[] dp3_zmax = {this.nc.P16, this.nc.P15, this.nc.P14};
        double[] dp4_xmin = {this.nc.P19, this.nc.P20, this.nc.P21};
        double[] dp4_xmax = {this.nc.P29, this.nc.P28, this.nc.P27};
        double[] dp4_ymin = {this.nc.P00, this.nc.P01, this.nc.P01};
        double[] dp4_ymax = {this.nc.P11, this.nc.P11, this.nc.P11};
        double[] dp4_zmin = {this.nc.P08, this.nc.P09, this.nc.P10};
        double[] dp4_zmax = {this.nc.P18, this.nc.P17, this.nc.P16};
        double rope1_xmin = this.nc.P07;
        double rope1_xmax = this.nc.P09;
        double rope1_ymin = this.nc.P00;
        double rope1_ymax = this.nc.P32;
        double rope1_zmin = this.nc.P15;
        double rope1_zmax = this.nc.P17;
        double rope2_xmin = this.nc.P24;
        double rope2_xmax = this.nc.P26;
        double rope2_ymin = this.nc.P00;
        double rope2_ymax = this.nc.P32;
        double rope2_zmin = this.nc.P14;
        double rope2_zmax = this.nc.P16;
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78380_c(par1Block.func_149677_c(iblockaccess, (int) i, (int) j, (int) k));
        IIcon icon = par1Block.func_149691_a(0, meta & 7);
        switch (meta & 7) {
            case 0:
            case 1:
            default:
                num = 0;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                num = 1;
                break;
            case 7:
                num = 2;
                break;
        }
        double uMin = icon.func_94214_a(10);
        double uMax = icon.func_94214_a(16);
        double vMin = icon.func_94207_b(0);
        double vMax = icon.func_94207_b(6);
        double uMinT = icon.func_94214_a(0);
        double uMaxT = icon.func_94214_a(6);
        double vMinT = icon.func_94207_b(0);
        double vMaxT = icon.func_94207_b(6);
        double uMinB = icon.func_94214_a(0);
        double uMaxB = icon.func_94214_a(6);
        double vMinB = icon.func_94207_b(7);
        double vMaxB = icon.func_94207_b(13);
        double uMinC = icon.func_94214_a(10);
        double uMaxC = icon.func_94214_a(16);
        double vMinC = icon.func_94207_b(7);
        double vMaxC = icon.func_94207_b(9);
        double uMinR = icon.func_94214_a(7);
        double uMaxR = icon.func_94214_a(9);
        double vMinR = icon.func_94207_b(0);
        double vMaxR = icon.func_94207_b(16);
        if ((meta & 8) == 0) {
            double xMin = i + rope1_xmin;
            double xMax = i + rope1_xmax;
            double yMin = j + rope1_ymin;
            double yMax = j + rope1_ymax;
            double zMin = k + rope1_zmin;
            double zMax = k + rope1_zmax;
            drawRope(tess, xMin, xMax, yMin, yMax, zMin, zMax, uMinR, uMaxR, vMinR, vMaxR);
            double xMin2 = i + rope2_xmin;
            double xMax2 = i + rope2_xmax;
            double yMin2 = j + rope2_ymin;
            double yMax2 = j + rope2_ymax;
            double zMin2 = k + rope2_zmin;
            double zMax2 = k + rope2_zmax;
            drawRope(tess, xMin2, xMax2, yMin2, yMax2, zMin2, zMax2, uMinR, uMaxR, vMinR, vMaxR);
            double xMin3 = i + dp1_xmin[num];
            double xMax3 = i + dp1_xmax[num];
            double yMin3 = j + dp1_ymin[num];
            double yMax3 = j + dp1_ymax[num];
            double zMin3 = k + dp1_zmin[num];
            double zMax3 = k + dp1_zmax[num];
            drawTop(tess, xMin3, xMax3, yMin3, yMax3, zMin3, zMax3, uMinT, uMaxT, vMinT, vMaxT);
            drawBottom(tess, xMin3, xMax3, yMin3, yMax3, zMin3, zMax3, uMinB, uMaxB, vMinB, vMaxB);
            drawSide(tess, xMin3, xMax3, yMin3, yMax3, zMin3, zMax3, uMin, uMax, vMin, vMax);
            drawCalyx(tess, xMin3, xMax3, yMin3, yMax3, zMin3, zMax3, uMinC, uMaxC, vMinC, vMaxC, tmpxz);
            double xMin4 = i + dp2_xmin[num];
            double xMax4 = i + dp2_xmax[num];
            double yMin4 = j + dp2_ymin[num];
            double yMax4 = j + dp2_ymax[num];
            double zMin4 = k + dp2_zmin[num];
            double zMax4 = k + dp2_zmax[num];
            drawTop(tess, xMin4, xMax4, yMin4, yMax4, zMin4, zMax4, uMinT, uMaxT, vMinT, vMaxT);
            drawBottom(tess, xMin4, xMax4, yMin4, yMax4, zMin4, zMax4, uMinB, uMaxB, vMinB, vMaxB);
            drawSide(tess, xMin4, xMax4, yMin4, yMax4, zMin4, zMax4, uMin, uMax, vMin, vMax);
            drawCalyx(tess, xMin4, xMax4, yMin4, yMax4, zMin4, zMax4, uMinC, uMaxC, vMinC, vMaxC, tmpxz);
            double xMin5 = i + dp3_xmin[num];
            double xMax5 = i + dp3_xmax[num];
            double yMin5 = j + dp3_ymin[num];
            double yMax5 = j + dp3_ymax[num];
            double zMin5 = k + dp3_zmin[num];
            double zMax5 = k + dp3_zmax[num];
            drawTop(tess, xMin5, xMax5, yMin5, yMax5, zMin5, zMax5, uMinT, uMaxT, vMinT, vMaxT);
            drawBottom(tess, xMin5, xMax5, yMin5, yMax5, zMin5, zMax5, uMinB, uMaxB, vMinB, vMaxB);
            drawSide(tess, xMin5, xMax5, yMin5, yMax5, zMin5, zMax5, uMin, uMax, vMin, vMax);
            drawCalyx(tess, xMin5, xMax5, yMin5, yMax5, zMin5, zMax5, uMinC, uMaxC, vMinC, vMaxC, tmpxz);
            double xMin6 = i + dp4_xmin[num];
            double xMax6 = i + dp4_xmax[num];
            double yMin6 = j + dp4_ymin[num];
            double yMax6 = j + dp4_ymax[num];
            double zMin6 = k + dp4_zmin[num];
            double zMax6 = k + dp4_zmax[num];
            drawTop(tess, xMin6, xMax6, yMin6, yMax6, zMin6, zMax6, uMinT, uMaxT, vMinT, vMaxT);
            drawBottom(tess, xMin6, xMax6, yMin6, yMax6, zMin6, zMax6, uMinB, uMaxB, vMinB, vMaxB);
            drawSide(tess, xMin6, xMax6, yMin6, yMax6, zMin6, zMax6, uMin, uMax, vMin, vMax);
            drawCalyx(tess, xMin6, xMax6, yMin6, yMax6, zMin6, zMax6, uMinC, uMaxC, vMinC, vMaxC, tmpxz);
            return;
        }
        double xMin7 = i + rope1_zmin;
        double xMax7 = i + rope1_zmax;
        double yMin7 = j + rope1_ymin;
        double yMax7 = j + rope1_ymax;
        double zMin7 = k + rope1_xmin;
        double zMax7 = k + rope1_xmax;
        drawRope(tess, xMin7, xMax7, yMin7, yMax7, zMin7, zMax7, uMinR, uMaxR, vMinR, vMaxR);
        double xMin8 = i + rope2_zmin;
        double xMax8 = i + rope2_zmax;
        double yMin8 = j + rope2_ymin;
        double yMax8 = j + rope2_ymax;
        double zMin8 = k + rope2_xmin;
        double zMax8 = k + rope2_xmax;
        drawRope(tess, xMin8, xMax8, yMin8, yMax8, zMin8, zMax8, uMinR, uMaxR, vMinR, vMaxR);
        double xMin9 = i + dp1_zmin[num];
        double xMax9 = i + dp1_zmax[num];
        double yMin9 = j + dp1_ymin[num];
        double yMax9 = j + dp1_ymax[num];
        double zMin9 = k + dp1_xmin[num];
        double zMax9 = k + dp1_xmax[num];
        drawTop(tess, xMin9, xMax9, yMin9, yMax9, zMin9, zMax9, uMinT, uMaxT, vMinT, vMaxT);
        drawBottom(tess, xMin9, xMax9, yMin9, yMax9, zMin9, zMax9, uMinB, uMaxB, vMinB, vMaxB);
        drawSide(tess, xMin9, xMax9, yMin9, yMax9, zMin9, zMax9, uMin, uMax, vMin, vMax);
        drawCalyx(tess, xMin9, xMax9, yMin9, yMax9, zMin9, zMax9, uMinC, uMaxC, vMinC, vMaxC, tmpxz);
        double xMin10 = i + dp2_zmin[num];
        double xMax10 = i + dp2_zmax[num];
        double yMin10 = j + dp2_ymin[num];
        double yMax10 = j + dp2_ymax[num];
        double zMin10 = k + dp2_xmin[num];
        double zMax10 = k + dp2_xmax[num];
        drawTop(tess, xMin10, xMax10, yMin10, yMax10, zMin10, zMax10, uMinT, uMaxT, vMinT, vMaxT);
        drawBottom(tess, xMin10, xMax10, yMin10, yMax10, zMin10, zMax10, uMinB, uMaxB, vMinB, vMaxB);
        drawSide(tess, xMin10, xMax10, yMin10, yMax10, zMin10, zMax10, uMin, uMax, vMin, vMax);
        drawCalyx(tess, xMin10, xMax10, yMin10, yMax10, zMin10, zMax10, uMinC, uMaxC, vMinC, vMaxC, tmpxz);
        double xMin11 = i + dp3_zmin[num];
        double xMax11 = i + dp3_zmax[num];
        double yMin11 = j + dp3_ymin[num];
        double yMax11 = j + dp3_ymax[num];
        double zMin11 = k + dp3_xmin[num];
        double zMax11 = k + dp3_xmax[num];
        drawTop(tess, xMin11, xMax11, yMin11, yMax11, zMin11, zMax11, uMinT, uMaxT, vMinT, vMaxT);
        drawBottom(tess, xMin11, xMax11, yMin11, yMax11, zMin11, zMax11, uMinB, uMaxB, vMinB, vMaxB);
        drawSide(tess, xMin11, xMax11, yMin11, yMax11, zMin11, zMax11, uMin, uMax, vMin, vMax);
        drawCalyx(tess, xMin11, xMax11, yMin11, yMax11, zMin11, zMax11, uMinC, uMaxC, vMinC, vMaxC, tmpxz);
        double xMin12 = i + dp4_zmin[num];
        double xMax12 = i + dp4_zmax[num];
        double yMin12 = j + dp4_ymin[num];
        double yMax12 = j + dp4_ymax[num];
        double zMin12 = k + dp4_xmin[num];
        double zMax12 = k + dp4_xmax[num];
        drawTop(tess, xMin12, xMax12, yMin12, yMax12, zMin12, zMax12, uMinT, uMaxT, vMinT, vMaxT);
        drawBottom(tess, xMin12, xMax12, yMin12, yMax12, zMin12, zMax12, uMinB, uMaxB, vMinB, vMaxB);
        drawSide(tess, xMin12, xMax12, yMin12, yMax12, zMin12, zMax12, uMin, uMax, vMin, vMax);
        drawCalyx(tess, xMin12, xMax12, yMin12, yMax12, zMin12, zMax12, uMinC, uMaxC, vMinC, vMaxC, tmpxz);
    }

    private void drawRope(Tessellator tess, double xMin, double xMax, double yMin, double yMax, double zMin, double zMax, double uMinT, double uMaxT, double vMinT, double vMaxT) {
        tess.func_78374_a(xMin, yMin, zMax, uMinT, vMaxT);
        tess.func_78374_a(xMax, yMin, zMin, uMaxT, vMaxT);
        tess.func_78374_a(xMax, yMax, zMin, uMaxT, vMinT);
        tess.func_78374_a(xMin, yMax, zMax, uMinT, vMinT);
        tess.func_78374_a(xMax, yMin, zMin, uMaxT, vMaxT);
        tess.func_78374_a(xMin, yMin, zMax, uMinT, vMaxT);
        tess.func_78374_a(xMin, yMax, zMax, uMinT, vMinT);
        tess.func_78374_a(xMax, yMax, zMin, uMaxT, vMinT);
        tess.func_78374_a(xMin, yMin, zMin, uMinT, vMaxT);
        tess.func_78374_a(xMax, yMin, zMax, uMaxT, vMaxT);
        tess.func_78374_a(xMax, yMax, zMax, uMaxT, vMinT);
        tess.func_78374_a(xMin, yMax, zMin, uMinT, vMinT);
        tess.func_78374_a(xMax, yMin, zMax, uMaxT, vMaxT);
        tess.func_78374_a(xMin, yMin, zMin, uMinT, vMaxT);
        tess.func_78374_a(xMin, yMax, zMin, uMinT, vMinT);
        tess.func_78374_a(xMax, yMax, zMax, uMaxT, vMinT);
    }

    private void drawCalyx(Tessellator tess, double xMin, double xMax, double yMin, double yMax, double zMin, double zMax, double uMinT, double uMaxT, double vMinT, double vMaxT, int tmpxz) {
        if (tmpxz < 5) {
            tess.func_78374_a(xMin, yMax, zMax, uMinT, vMaxT);
            tess.func_78374_a(xMax, yMax, zMin, uMaxT, vMaxT);
            tess.func_78374_a(xMax, yMax + this.nc.P04, zMin, uMaxT, vMinT);
            tess.func_78374_a(xMin, yMax + this.nc.P04, zMax, uMinT, vMinT);
            tess.func_78374_a(xMax, yMax, zMin, uMaxT, vMaxT);
            tess.func_78374_a(xMin, yMax, zMax, uMinT, vMaxT);
            tess.func_78374_a(xMin, yMax + this.nc.P04, zMax, uMinT, vMinT);
            tess.func_78374_a(xMax, yMax + this.nc.P04, zMin, uMaxT, vMinT);
            return;
        }
        tess.func_78374_a(xMin, yMax, zMin, uMinT, vMaxT);
        tess.func_78374_a(xMax, yMax, zMax, uMaxT, vMaxT);
        tess.func_78374_a(xMax, yMax + this.nc.P04, zMax, uMaxT, vMinT);
        tess.func_78374_a(xMin, yMax + this.nc.P04, zMin, uMinT, vMinT);
        tess.func_78374_a(xMax, yMax, zMax, uMaxT, vMaxT);
        tess.func_78374_a(xMin, yMax, zMin, uMinT, vMaxT);
        tess.func_78374_a(xMin, yMax + this.nc.P04, zMin, uMinT, vMinT);
        tess.func_78374_a(xMax, yMax + this.nc.P04, zMax, uMaxT, vMinT);
    }

    private void drawTop(Tessellator tess, double xMin, double xMax, double yMin, double yMax, double zMin, double zMax, double uMinT, double uMaxT, double vMinT, double vMaxT) {
        tess.func_78374_a(xMin, yMax, zMax, uMinT, vMaxT);
        tess.func_78374_a(xMax, yMax, zMax, uMaxT, vMaxT);
        tess.func_78374_a(xMax, yMax, zMin, uMaxT, vMinT);
        tess.func_78374_a(xMin, yMax, zMin, uMinT, vMinT);
    }

    private void drawBottom(Tessellator tess, double xMin, double xMax, double yMin, double yMax, double zMin, double zMax, double uMinB, double uMaxB, double vMinB, double vMaxB) {
        tess.func_78374_a(xMin, yMin, zMin, uMinB, vMinB);
        tess.func_78374_a(xMax, yMin, zMin, uMaxB, vMinB);
        tess.func_78374_a(xMax, yMin, zMax, uMaxB, vMaxB);
        tess.func_78374_a(xMin, yMin, zMax, uMinB, vMaxB);
    }

    private void drawSide(Tessellator tess, double xMin, double xMax, double yMin, double yMax, double zMin, double zMax, double uMin, double uMax, double vMin, double vMax) {
        tess.func_78374_a(xMin, yMin, zMin, uMin, vMax);
        tess.func_78374_a(xMin, yMin, zMax, uMax, vMax);
        tess.func_78374_a(xMin, yMax, zMax, uMax, vMin);
        tess.func_78374_a(xMin, yMax, zMin, uMin, vMin);
        tess.func_78374_a(xMax, yMin, zMax, uMin, vMax);
        tess.func_78374_a(xMax, yMin, zMin, uMax, vMax);
        tess.func_78374_a(xMax, yMax, zMin, uMax, vMin);
        tess.func_78374_a(xMax, yMax, zMax, uMin, vMin);
        tess.func_78374_a(xMax, yMin, zMin, uMin, vMax);
        tess.func_78374_a(xMin, yMin, zMin, uMax, vMax);
        tess.func_78374_a(xMin, yMax, zMin, uMax, vMin);
        tess.func_78374_a(xMax, yMax, zMin, uMin, vMin);
        tess.func_78374_a(xMin, yMin, zMax, uMin, vMax);
        tess.func_78374_a(xMax, yMin, zMax, uMax, vMax);
        tess.func_78374_a(xMax, yMax, zMax, uMax, vMin);
        tess.func_78374_a(xMin, yMax, zMax, uMin, vMin);
    }

    private void renderPole(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        Block b = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
        if ((meta & 8) == 0 && b != block) {
            renderblocks.func_147757_a(ecru_BlockDriedPersimmon.tx_wood);
            block.func_149676_a(this.nc.P00, this.nc.P30, this.nc.P14, this.nc.P32, this.nc.P32, this.nc.P18);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            renderblocks.func_147771_a();
        }
        if ((meta & 8) == 8 && b != block) {
            renderblocks.func_147757_a(ecru_BlockDriedPersimmon.tx_wood);
            block.func_149676_a(this.nc.P14, this.nc.P30, this.nc.P00, this.nc.P18, this.nc.P32, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            renderblocks.func_147771_a();
        }
    }
}
