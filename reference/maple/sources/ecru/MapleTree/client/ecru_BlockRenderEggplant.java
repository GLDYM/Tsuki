package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockEggplant;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderEggplant implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        iblockaccess.func_72805_g(blockX, blockY, blockZ);
        iblockaccess.func_147439_a(blockX, blockY, blockZ);
        render(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        renderblocks.func_147771_a();
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147771_a();
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderEggplantID;
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
        renderblocks.func_147765_a(block.func_149691_a(0, iblockaccess.func_72805_g(blockX, blockY, blockZ)), d0, d1, d2, 1.0f);
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        if (meta >= 5 && meta <= 7) {
            drawEggplant((ecru_BlockEggplant) block, meta, d0, d1, d2, 1.0f, iblockaccess, renderblocks);
        }
    }

    public void drawEggplant(ecru_BlockEggplant par1Block, int meta, double i, double j, double k, float par9, IBlockAccess iblockaccess, RenderBlocks renderblocks) {
        int tmpxz = Math.abs((((((int) i) % 10) * (((int) j) % 10)) * (((int) k) % 10)) % 10);
        int[] sideMinX = {5, 8, 11};
        int[] sideMaxX = {7, 11, 16};
        int[] sideMinZ = {0, 0, 0};
        int[] sideMaxZ = {4, 7, 10};
        int[] topMinX = {5, 8, 0};
        int[] topMaxX = {7, 11, 5};
        int[] topMinZ = {5, 8, 0};
        int[] topMaxZ = {7, 11, 5};
        int[] BottomMinX = {5, 8, 0};
        int[] BottomMaxX = {7, 11, 5};
        int[] BottomMinZ = {8, 12, 6};
        int[] BottomMaxZ = {10, 15, 11};
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78380_c(par1Block.func_149677_c(iblockaccess, (int) i, (int) j, (int) k));
        tess.func_78386_a(1.0f, 1.0f, 1.0f);
        IIcon icon = par1Block.getEggplantIcon(meta);
        int num = par1Block.getEggplantNum(meta);
        double uMin = icon.func_94214_a(sideMinX[num]);
        double uMax = icon.func_94214_a(sideMaxX[num]);
        double vMin = icon.func_94207_b(sideMinZ[num]);
        double vMax = icon.func_94207_b(sideMaxZ[num]);
        double uMinT = icon.func_94214_a(topMinX[num]);
        double uMaxT = icon.func_94214_a(topMaxX[num]);
        double vMinT = icon.func_94207_b(topMinZ[num]);
        double vMaxT = icon.func_94207_b(topMaxZ[num]);
        double uMinB = icon.func_94214_a(BottomMinX[num]);
        double uMaxB = icon.func_94214_a(BottomMaxX[num]);
        double vMinB = icon.func_94207_b(BottomMinZ[num]);
        double vMaxB = icon.func_94207_b(BottomMaxZ[num]);
        switch (num) {
            case 0:
                if (tmpxz < 0 || tmpxz >= 3) {
                    double xMin = i + this.nc.P04;
                    double xMax = i + this.nc.P08;
                    double yMin = j + this.nc.P23;
                    double yMax = j + this.nc.P29;
                    double zMin = k + this.nc.P24;
                    double zMax = k + this.nc.P28;
                    drawTop(tess, xMin, xMax, yMin, yMax, zMin, zMax, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin, xMax, yMin, yMax, zMin, zMax, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin, xMax, yMin, yMax, zMin, zMax, uMin, uMax, vMin, vMax);
                }
                if (tmpxz <= 2 || tmpxz >= 5) {
                    double xMin2 = i + this.nc.P25;
                    double xMax2 = i + this.nc.P29;
                    double yMin2 = j + this.nc.P17;
                    double yMax2 = j + this.nc.P23;
                    double zMin2 = k + this.nc.P20;
                    double zMax2 = k + this.nc.P25;
                    drawTop(tess, xMin2, xMax2, yMin2, yMax2, zMin2, zMax2, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin2, xMax2, yMin2, yMax2, zMin2, zMax2, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin2, xMax2, yMin2, yMax2, zMin2, zMax2, uMin, uMax, vMin, vMax);
                }
                if (tmpxz <= 4 || tmpxz >= 6) {
                    double xMin3 = i + this.nc.P08;
                    double xMax3 = i + this.nc.P12;
                    double yMin3 = j + this.nc.P20;
                    double yMax3 = j + this.nc.P26;
                    double zMin3 = k + this.nc.P08;
                    double zMax3 = k + this.nc.P12;
                    drawTop(tess, xMin3, xMax3, yMin3, yMax3, zMin3, zMax3, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin3, xMax3, yMin3, yMax3, zMin3, zMax3, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin3, xMax3, yMin3, yMax3, zMin3, zMax3, uMin, uMax, vMin, vMax);
                }
                if (tmpxz <= 7 || tmpxz > 9) {
                    double xMin4 = i + this.nc.P22;
                    double xMax4 = i + this.nc.P26;
                    double yMin4 = j + this.nc.P24;
                    double yMax4 = j + this.nc.P30;
                    double zMin4 = k + this.nc.P04;
                    double zMax4 = k + this.nc.P08;
                    drawTop(tess, xMin4, xMax4, yMin4, yMax4, zMin4, zMax4, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin4, xMax4, yMin4, yMax4, zMin4, zMax4, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin4, xMax4, yMin4, yMax4, zMin4, zMax4, uMin, uMax, vMin, vMax);
                    break;
                }
                break;
            case 1:
                if (tmpxz < 0 || tmpxz >= 3) {
                    double xMin5 = i + this.nc.P03;
                    double xMax5 = i + this.nc.P09;
                    double yMin5 = j + this.nc.P17;
                    double yMax5 = j + this.nc.P29;
                    double zMin5 = k + this.nc.P23;
                    double zMax5 = k + this.nc.P29;
                    drawTop(tess, xMin5, xMax5, yMin5, yMax5, zMin5, zMax5, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin5, xMax5, yMin5, yMax5, zMin5, zMax5, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin5, xMax5, yMin5, yMax5, zMin5, zMax5, uMin, uMax, vMin, vMax);
                }
                if (tmpxz <= 2 || tmpxz >= 5) {
                    double xMin6 = i + this.nc.P24;
                    double xMax6 = i + this.nc.P30;
                    double yMin6 = j + this.nc.P10;
                    double yMax6 = j + this.nc.P23;
                    double zMin6 = k + this.nc.P19;
                    double zMax6 = k + this.nc.P25;
                    drawTop(tess, xMin6, xMax6, yMin6, yMax6, zMin6, zMax6, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin6, xMax6, yMin6, yMax6, zMin6, zMax6, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin6, xMax6, yMin6, yMax6, zMin6, zMax6, uMin, uMax, vMin, vMax);
                }
                if (tmpxz <= 4 || tmpxz >= 6) {
                    double xMin7 = i + this.nc.P07;
                    double xMax7 = i + this.nc.P13;
                    double yMin7 = j + this.nc.P13;
                    double yMax7 = j + this.nc.P26;
                    double zMin7 = k + this.nc.P07;
                    double zMax7 = k + this.nc.P13;
                    drawTop(tess, xMin7, xMax7, yMin7, yMax7, zMin7, zMax7, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin7, xMax7, yMin7, yMax7, zMin7, zMax7, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin7, xMax7, yMin7, yMax7, zMin7, zMax7, uMin, uMax, vMin, vMax);
                }
                if (tmpxz <= 7 || tmpxz > 9) {
                    double xMin8 = i + this.nc.P21;
                    double xMax8 = i + this.nc.P27;
                    double yMin8 = j + this.nc.P17;
                    double yMax8 = j + this.nc.P30;
                    double zMin8 = k + this.nc.P03;
                    double zMax8 = k + this.nc.P09;
                    drawTop(tess, xMin8, xMax8, yMin8, yMax8, zMin8, zMax8, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin8, xMax8, yMin8, yMax8, zMin8, zMax8, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin8, xMax8, yMin8, yMax8, zMin8, zMax8, uMin, uMax, vMin, vMax);
                    break;
                }
                break;
            case 2:
                if (tmpxz < 0 || tmpxz >= 3) {
                    double xMin9 = i + this.nc.P02;
                    double xMax9 = i + this.nc.P10;
                    double yMin9 = j + this.nc.P09;
                    double yMax9 = j + this.nc.P29;
                    double zMin9 = k + this.nc.P22;
                    double zMax9 = k + this.nc.P30;
                    drawTop(tess, xMin9, xMax9, yMin9, yMax9, zMin9, zMax9, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin9, xMax9, yMin9, yMax9, zMin9, zMax9, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin9, xMax9, yMin9, yMax9, zMin9, zMax9, uMin, uMax, vMin, vMax);
                }
                if (tmpxz <= 2 || tmpxz >= 5) {
                    double xMin10 = i + this.nc.P23;
                    double xMax10 = i + this.nc.P31;
                    double yMin10 = j + this.nc.P03;
                    double yMax10 = j + this.nc.P23;
                    double zMin10 = k + this.nc.P18;
                    double zMax10 = k + this.nc.P26;
                    drawTop(tess, xMin10, xMax10, yMin10, yMax10, zMin10, zMax10, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin10, xMax10, yMin10, yMax10, zMin10, zMax10, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin10, xMax10, yMin10, yMax10, zMin10, zMax10, uMin, uMax, vMin, vMax);
                }
                if (tmpxz <= 4 || tmpxz >= 6) {
                    double xMin11 = i + this.nc.P06;
                    double xMax11 = i + this.nc.P14;
                    double yMin11 = j + this.nc.P06;
                    double yMax11 = j + this.nc.P26;
                    double zMin11 = k + this.nc.P06;
                    double zMax11 = k + this.nc.P14;
                    drawTop(tess, xMin11, xMax11, yMin11, yMax11, zMin11, zMax11, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin11, xMax11, yMin11, yMax11, zMin11, zMax11, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin11, xMax11, yMin11, yMax11, zMin11, zMax11, uMin, uMax, vMin, vMax);
                }
                if (tmpxz <= 7 || tmpxz > 9) {
                    double xMin12 = i + this.nc.P20;
                    double xMax12 = i + this.nc.P28;
                    double yMin12 = j + this.nc.P12;
                    double yMax12 = j + this.nc.P30;
                    double zMin12 = k + this.nc.P02;
                    double zMax12 = k + this.nc.P10;
                    drawTop(tess, xMin12, xMax12, yMin12, yMax12, zMin12, zMax12, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin12, xMax12, yMin12, yMax12, zMin12, zMax12, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin12, xMax12, yMin12, yMax12, zMin12, zMax12, uMin, uMax, vMin, vMax);
                    break;
                }
                break;
        }
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
}
