package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockOnion;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderOnion implements ISimpleBlockRenderingHandler {
    private float P00 = 0.0f;
    private float P01 = 0.03125f;
    private float P02 = 0.0625f;
    private float P03 = 0.09375f;
    private float P04 = 0.125f;
    private float P05 = 0.15625f;
    private float P06 = 0.1875f;
    private float P07 = 0.21875f;
    private float P08 = 0.25f;
    private float P09 = 0.28125f;
    private float P10 = 0.3125f;
    private float P11 = 0.34375f;
    private float P12 = 0.375f;
    private float P13 = 0.40625f;
    private float P14 = 0.4375f;
    private float P15 = 0.46875f;
    private float P16 = 0.5f;
    private float P17 = 0.53125f;
    private float P18 = 0.5625f;
    private float P19 = 0.59375f;
    private float P20 = 0.625f;
    private float P21 = 0.65625f;
    private float P22 = 0.6875f;
    private float P23 = 0.71875f;
    private float P24 = 0.75f;
    private float P25 = 0.78125f;
    private float P26 = 0.8125f;
    private float P27 = 0.84375f;
    private float P28 = 0.875f;
    private float P29 = 0.90625f;
    private float P30 = 0.9375f;
    private float P31 = 0.96875f;
    private float P32 = 1.0f;

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        iblockaccess.func_72805_g(blockX, blockY, blockZ);
        render(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        renderblocks.func_147771_a();
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147771_a();
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderOnionID;
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
        if (meta >= 0 && meta <= 7) {
            drawOnion((ecru_BlockOnion) block, meta, d0, d1, d2, 1.0f, iblockaccess, renderblocks);
        }
    }

    public void drawOnion(ecru_BlockOnion par1Block, int meta, double i, double j, double k, float par9, IBlockAccess iblockaccess, RenderBlocks renderblocks) {
        double yMax;
        int tmpxz = Math.abs((((((int) i) % 10) + (((int) j) % 10)) + (((int) k) % 10)) % 10);
        int[] leafMinX = {12, 12, 12, 12, 12, 8, 4, 0};
        int[] leafMaxX = {16, 16, 16, 16, 16, 12, 8, 4};
        int[] leafMinZ = {10, 10, 10, 10, 10, 0, 0, 0};
        int[] leafMaxZ = {16, 16, 16, 16, 16, 16, 16, 16};
        int[] sideMinX = {14, 6, 10};
        int[] sideMaxX = {16, 10, 16};
        int[] sideMinZ = {13, 10, 0};
        int[] sideMaxZ = {15, 14, 6};
        int[] topMinX = {14, 6, 0};
        int[] topMaxX = {16, 10, 6};
        int[] topMinZ = {7, 0, 0};
        int[] topMaxZ = {9, 4, 6};
        int[] BottomMinX = {14, 6, 0};
        int[] BottomMaxX = {16, 10, 6};
        int[] BottomMinZ = {10, 5, 7};
        int[] BottomMaxZ = {12, 9, 13};
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78380_c(par1Block.func_149677_c(iblockaccess, (int) i, (int) j, (int) k));
        tess.func_78386_a(1.0f, 1.0f, 1.0f);
        IIcon icon = par1Block.getOnionIcon(meta);
        int num = par1Block.getOnionNum(meta);
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
        IIcon icon2 = par1Block.getLeafIcon(meta);
        int num2 = par1Block.getLeafNum(meta);
        double uMinL = icon2.func_94214_a(leafMinX[num2]);
        double uMaxL = icon2.func_94214_a(leafMaxX[num2]);
        double vMinL = icon2.func_94207_b(leafMinZ[num2]);
        double vMaxL = icon2.func_94207_b(leafMaxZ[num2]);
        switch (meta) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                if (meta == 0) {
                    yMax = j + this.P03;
                } else if (meta == 1) {
                    yMax = j + this.P07;
                } else if (meta == 2) {
                    yMax = j + this.P12;
                } else if (meta == 3 || meta == 4) {
                    yMax = j + this.P18;
                } else {
                    yMax = j + this.P03;
                }
                if (tmpxz < 0 || tmpxz >= 3) {
                    drawLeaf(tess, i + this.P03, i + this.P11, j + (-this.P02), yMax, k + this.P21, k + this.P29, uMinL, uMaxL, vMinL, vMaxL);
                }
                if (tmpxz <= 2 || tmpxz >= 5) {
                    drawLeaf(tess, i + this.P21, i + this.P29, j + this.P00, yMax, k + this.P20, k + this.P28, uMinL, uMaxL, vMinL, vMaxL);
                }
                if (tmpxz <= 4 || tmpxz >= 6) {
                    drawLeaf(tess, i + this.P05, i + this.P13, j + (-this.P02), yMax, k + this.P05, k + this.P13, uMinL, uMaxL, vMinL, vMaxL);
                }
                if (tmpxz <= 7 || tmpxz > 9) {
                    drawLeaf(tess, i + this.P19, i + this.P27, j + (-this.P02), yMax, k + this.P03, k + this.P11, uMinL, uMaxL, vMinL, vMaxL);
                    break;
                }
                break;
            case 5:
                if (tmpxz < 0 || tmpxz >= 3) {
                    double xMin = i + this.P05;
                    double xMax = i + this.P09;
                    double yMin = j + (-this.P03);
                    double yMax2 = j + this.P01;
                    double zMin = k + this.P23;
                    double zMax = k + this.P27;
                    drawTop(tess, xMin, xMax, yMin, yMax2, zMin, zMax, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin, xMax, yMin, yMax2, zMin, zMax, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin, xMax, yMin, yMax2, zMin, zMax, uMin, uMax, vMin, vMax);
                    drawLeaf(tess, i + this.P03, i + this.P11, j + this.P00, j + this.P32, k + this.P21, k + this.P29, uMinL, uMaxL, vMinL, vMaxL);
                }
                if (tmpxz <= 2 || tmpxz >= 5) {
                    double xMin2 = i + this.P23;
                    double xMax2 = i + this.P27;
                    double yMin2 = j + (-this.P04);
                    double yMax3 = j + this.P00;
                    double zMin2 = k + this.P22;
                    double zMax2 = k + this.P26;
                    drawTop(tess, xMin2, xMax2, yMin2, yMax3, zMin2, zMax2, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin2, xMax2, yMin2, yMax3, zMin2, zMax2, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin2, xMax2, yMin2, yMax3, zMin2, zMax2, uMin, uMax, vMin, vMax);
                    drawLeaf(tess, i + this.P21, i + this.P29, j + this.P00, j + this.P32, k + this.P20, k + this.P28, uMinL, uMaxL, vMinL, vMaxL);
                }
                if (tmpxz <= 4 || tmpxz >= 6) {
                    double xMin3 = i + this.P07;
                    double xMax3 = i + this.P11;
                    double yMin3 = j + (-this.P05);
                    double yMax4 = j + (-this.P01);
                    double zMin3 = k + this.P07;
                    double zMax3 = k + this.P11;
                    drawTop(tess, xMin3, xMax3, yMin3, yMax4, zMin3, zMax3, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin3, xMax3, yMin3, yMax4, zMin3, zMax3, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin3, xMax3, yMin3, yMax4, zMin3, zMax3, uMin, uMax, vMin, vMax);
                    drawLeaf(tess, i + this.P05, i + this.P13, j + this.P00, j + this.P32, k + this.P05, k + this.P13, uMinL, uMaxL, vMinL, vMaxL);
                }
                if (tmpxz <= 7 || tmpxz > 9) {
                    double xMin4 = i + this.P21;
                    double xMax4 = i + this.P25;
                    double yMin4 = j + (-this.P03);
                    double yMax5 = j + this.P01;
                    double zMin4 = k + this.P05;
                    double zMax4 = k + this.P09;
                    drawTop(tess, xMin4, xMax4, yMin4, yMax5, zMin4, zMax4, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin4, xMax4, yMin4, yMax5, zMin4, zMax4, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin4, xMax4, yMin4, yMax5, zMin4, zMax4, uMin, uMax, vMin, vMax);
                    drawLeaf(tess, i + this.P19, i + this.P27, j + this.P00, j + this.P32, k + this.P03, k + this.P11, uMinL, uMaxL, vMinL, vMaxL);
                    break;
                }
                break;
            case 6:
                if (tmpxz < 0 || tmpxz >= 3) {
                    double xMin5 = i + this.P03;
                    double xMax5 = i + this.P11;
                    double yMin5 = j + (-this.P06);
                    double yMax6 = j + this.P02;
                    double zMin5 = k + this.P21;
                    double zMax5 = k + this.P29;
                    drawTop(tess, xMin5, xMax5, yMin5, yMax6, zMin5, zMax5, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin5, xMax5, yMin5, yMax6, zMin5, zMax5, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin5, xMax5, yMin5, yMax6, zMin5, zMax5, uMin, uMax, vMin, vMax);
                    drawLeaf(tess, i + this.P03, i + this.P11, j + this.P00, j + this.P32, k + this.P21, k + this.P29, uMinL, uMaxL, vMinL, vMaxL);
                }
                if (tmpxz <= 2 || tmpxz >= 5) {
                    double xMin6 = i + this.P21;
                    double xMax6 = i + this.P29;
                    double yMin6 = j + (-this.P05);
                    double yMax7 = j + this.P03;
                    double zMin6 = k + this.P20;
                    double zMax6 = k + this.P28;
                    drawTop(tess, xMin6, xMax6, yMin6, yMax7, zMin6, zMax6, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin6, xMax6, yMin6, yMax7, zMin6, zMax6, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin6, xMax6, yMin6, yMax7, zMin6, zMax6, uMin, uMax, vMin, vMax);
                    drawLeaf(tess, i + this.P21, i + this.P29, j + this.P00, j + this.P32, k + this.P20, k + this.P28, uMinL, uMaxL, vMinL, vMaxL);
                }
                if (tmpxz <= 4 || tmpxz >= 6) {
                    double xMin7 = i + this.P05;
                    double xMax7 = i + this.P13;
                    double yMin7 = j + (-this.P07);
                    double yMax8 = j + this.P01;
                    double zMin7 = k + this.P05;
                    double zMax7 = k + this.P13;
                    drawTop(tess, xMin7, xMax7, yMin7, yMax8, zMin7, zMax7, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin7, xMax7, yMin7, yMax8, zMin7, zMax7, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin7, xMax7, yMin7, yMax8, zMin7, zMax7, uMin, uMax, vMin, vMax);
                    drawLeaf(tess, i + this.P05, i + this.P13, j + this.P00, j + this.P32, k + this.P05, k + this.P13, uMinL, uMaxL, vMinL, vMaxL);
                }
                if (tmpxz <= 7 || tmpxz > 9) {
                    double xMin8 = i + this.P19;
                    double xMax8 = i + this.P27;
                    double yMin8 = j + (-this.P06);
                    double yMax9 = j + this.P02;
                    double zMin8 = k + this.P03;
                    double zMax8 = k + this.P11;
                    drawTop(tess, xMin8, xMax8, yMin8, yMax9, zMin8, zMax8, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin8, xMax8, yMin8, yMax9, zMin8, zMax8, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin8, xMax8, yMin8, yMax9, zMin8, zMax8, uMin, uMax, vMin, vMax);
                    drawLeaf(tess, i + this.P19, i + this.P27, j + this.P00, j + this.P32, k + this.P03, k + this.P11, uMinL, uMaxL, vMinL, vMaxL);
                    break;
                }
                break;
            case 7:
                if (tmpxz < 0 || tmpxz >= 3) {
                    double xMin9 = i + this.P02;
                    double xMax9 = i + this.P12;
                    double yMin9 = j + (-this.P10);
                    double yMax10 = j + this.P02;
                    double zMin9 = k + this.P20;
                    double zMax9 = k + this.P30;
                    drawTop(tess, xMin9, xMax9, yMin9, yMax10, zMin9, zMax9, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin9, xMax9, yMin9, yMax10, zMin9, zMax9, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin9, xMax9, yMin9, yMax10, zMin9, zMax9, uMin, uMax, vMin, vMax);
                    drawLeaf(tess, i + this.P03, i + this.P11, j + this.P00, j + this.P32, k + this.P21, k + this.P29, uMinL, uMaxL, vMinL, vMaxL);
                }
                if (tmpxz <= 2 || tmpxz >= 5) {
                    double xMin10 = i + this.P20;
                    double xMax10 = i + this.P30;
                    double yMin10 = j + (-this.P09);
                    double yMax11 = j + this.P03;
                    double zMin10 = k + this.P19;
                    double zMax10 = k + this.P29;
                    drawTop(tess, xMin10, xMax10, yMin10, yMax11, zMin10, zMax10, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin10, xMax10, yMin10, yMax11, zMin10, zMax10, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin10, xMax10, yMin10, yMax11, zMin10, zMax10, uMin, uMax, vMin, vMax);
                    drawLeaf(tess, i + this.P21, i + this.P29, j + this.P00, j + this.P32, k + this.P20, k + this.P28, uMinL, uMaxL, vMinL, vMaxL);
                }
                if (tmpxz <= 4 || tmpxz >= 6) {
                    double xMin11 = i + this.P04;
                    double xMax11 = i + this.P14;
                    double yMin11 = j + (-this.P12);
                    double yMax12 = j + this.P00;
                    double zMin11 = k + this.P04;
                    double zMax11 = k + this.P14;
                    drawTop(tess, xMin11, xMax11, yMin11, yMax12, zMin11, zMax11, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin11, xMax11, yMin11, yMax12, zMin11, zMax11, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin11, xMax11, yMin11, yMax12, zMin11, zMax11, uMin, uMax, vMin, vMax);
                    drawLeaf(tess, i + this.P05, i + this.P13, j + this.P00, j + this.P32, k + this.P05, k + this.P13, uMinL, uMaxL, vMinL, vMaxL);
                }
                if (tmpxz <= 7 || tmpxz > 9) {
                    double xMin12 = i + this.P18;
                    double xMax12 = i + this.P28;
                    double yMin12 = j + (-this.P10);
                    double yMax13 = j + this.P02;
                    double zMin12 = k + this.P02;
                    double zMax12 = k + this.P12;
                    drawTop(tess, xMin12, xMax12, yMin12, yMax13, zMin12, zMax12, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin12, xMax12, yMin12, yMax13, zMin12, zMax12, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin12, xMax12, yMin12, yMax13, zMin12, zMax12, uMin, uMax, vMin, vMax);
                    drawLeaf(tess, i + this.P19, i + this.P27, j + this.P00, j + this.P32, k + this.P03, k + this.P11, uMinL, uMaxL, vMinL, vMaxL);
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

    private void drawLeaf(Tessellator tess, double xMin, double xMax, double yMin, double yMax, double zMin, double zMax, double uMin, double uMax, double vMin, double vMax) {
        tess.func_78374_a(xMin, yMax, zMin, uMin, vMin);
        tess.func_78374_a(xMin, yMin, zMin, uMin, vMax);
        tess.func_78374_a(xMax, yMin, zMax, uMax, vMax);
        tess.func_78374_a(xMax, yMax, zMax, uMax, vMin);
        tess.func_78374_a(xMax, yMax, zMax, uMin, vMin);
        tess.func_78374_a(xMax, yMin, zMax, uMin, vMax);
        tess.func_78374_a(xMin, yMin, zMin, uMax, vMax);
        tess.func_78374_a(xMin, yMax, zMin, uMax, vMin);
        tess.func_78374_a(xMin, yMax, zMax, uMin, vMin);
        tess.func_78374_a(xMin, yMin, zMax, uMin, vMax);
        tess.func_78374_a(xMax, yMin, zMin, uMax, vMax);
        tess.func_78374_a(xMax, yMax, zMin, uMax, vMin);
        tess.func_78374_a(xMax, yMax, zMin, uMin, vMin);
        tess.func_78374_a(xMax, yMin, zMin, uMin, vMax);
        tess.func_78374_a(xMin, yMin, zMax, uMax, vMax);
        tess.func_78374_a(xMin, yMax, zMax, uMax, vMin);
    }
}
