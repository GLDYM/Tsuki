package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockCropsJapaneseRadish;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderJapaneseRadish implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        render(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        renderblocks.func_147771_a();
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderJapaneseRadishID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    private void render(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78380_c(block.func_149677_c(iblockaccess, blockX, blockY, blockZ));
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        double d0 = blockX;
        double d1 = blockY;
        double d2 = blockZ;
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        if (meta >= 0 && meta <= 7) {
            drawJapaneseRadish((ecru_BlockCropsJapaneseRadish) block, meta, d0, d1, d2, 1.0f, iblockaccess, renderblocks);
        }
    }

    public void drawJapaneseRadish(ecru_BlockCropsJapaneseRadish par1Block, int meta, double i, double j, double k, float par9, IBlockAccess iblockaccess, RenderBlocks renderblocks) {
        double yMax;
        int tmpxz = Math.abs((((((int) i) % 10) + (((int) j) % 10)) + (((int) k) % 10)) % 10);
        int[] leafMinX = {8, 8, 8, 8, 8, 8, 8, 0};
        int[] leafMaxX = {16, 16, 16, 16, 16, 16, 16, 8};
        int[] leafMinZ = {0, 0, 6, 6, 6, 6, 6, 0};
        int[] leafMaxZ = {6, 6, 16, 16, 16, 16, 16, 16};
        int[] sideMinX = {6, 6, 10};
        int[] sideMaxX = {10, 10, 16};
        int[] sideMinZ = {0, 0, 0};
        int[] sideMaxZ = {8, 8, 13};
        int[] topMinX = {0, 0, 0};
        int[] topMaxX = {4, 4, 6};
        int[] topMinZ = {12, 12, 0};
        int[] topMaxZ = {16, 16, 6};
        int[] BottomMinX = {5, 5, 0};
        int[] BottomMaxX = {9, 9, 6};
        int[] BottomMinZ = {12, 12, 6};
        int[] BottomMaxZ = {16, 16, 12};
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78380_c(par1Block.func_149677_c(iblockaccess, (int) i, (int) j, (int) k));
        tess.func_78386_a(1.0f, 1.0f, 1.0f);
        IIcon icon = par1Block.getJapaneseRadishIcon(meta);
        int num = par1Block.getJapaneseRadishNum(meta);
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
                    yMax = j + this.nc.P03;
                } else if (meta == 1) {
                    yMax = j + this.nc.P07;
                } else if (meta == 2) {
                    yMax = j + this.nc.P12;
                } else if (meta == 3 || meta == 4) {
                    yMax = j + this.nc.P18;
                } else {
                    yMax = j + this.nc.P03;
                }
                if (tmpxz < 0 || tmpxz >= 3) {
                    drawLeaf2(tess, i + this.nc.P03, i + this.nc.P11, j + (-this.nc.P02), yMax, k + this.nc.P21, k + this.nc.P29, uMinL, uMaxL, vMinL, vMaxL, this.nc.P01, this.nc.P03);
                }
                if (tmpxz <= 2 || tmpxz >= 5) {
                    drawLeaf2(tess, i + this.nc.P21, i + this.nc.P29, j + this.nc.P00, yMax, k + this.nc.P20, k + this.nc.P28, uMinL, uMaxL, vMinL, vMaxL, this.nc.P01, this.nc.P03);
                }
                if (tmpxz <= 4 || tmpxz >= 6) {
                    drawLeaf2(tess, i + this.nc.P05, i + this.nc.P13, j + (-this.nc.P02), yMax, k + this.nc.P05, k + this.nc.P13, uMinL, uMaxL, vMinL, vMaxL, this.nc.P01, this.nc.P03);
                }
                if (tmpxz <= 7 || tmpxz > 9) {
                    drawLeaf2(tess, i + this.nc.P19, i + this.nc.P27, j + (-this.nc.P02), yMax, k + this.nc.P03, k + this.nc.P11, uMinL, uMaxL, vMinL, vMaxL, this.nc.P01, this.nc.P03);
                    break;
                }
                break;
            case 5:
                if (tmpxz < 0 || tmpxz >= 3) {
                    double xMin = i + this.nc.P05;
                    double xMax = i + this.nc.P09;
                    double yMin = j + (-this.nc.P09);
                    double yMax2 = j + this.nc.P01;
                    double zMin = k + this.nc.P23;
                    double zMax = k + this.nc.P27;
                    drawTop(tess, xMin, xMax, yMin, yMax2, zMin, zMax, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin, xMax, yMin, yMax2, zMin, zMax, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin, xMax, yMin, yMax2, zMin, zMax, uMin, uMax, vMin, vMax);
                    drawLeaf2(tess, i + this.nc.P03, i + this.nc.P11, j + this.nc.P01, j + this.nc.P21, k + this.nc.P21, k + this.nc.P29, uMinL, uMaxL, vMinL, vMaxL, this.nc.P01, this.nc.P03);
                }
                if (tmpxz <= 2 || tmpxz >= 5) {
                    double xMin2 = i + this.nc.P23;
                    double xMax2 = i + this.nc.P27;
                    double yMin2 = j + (-this.nc.P10);
                    double yMax3 = j + this.nc.P00;
                    double zMin2 = k + this.nc.P22;
                    double zMax2 = k + this.nc.P26;
                    drawTop(tess, xMin2, xMax2, yMin2, yMax3, zMin2, zMax2, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin2, xMax2, yMin2, yMax3, zMin2, zMax2, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin2, xMax2, yMin2, yMax3, zMin2, zMax2, uMin, uMax, vMin, vMax);
                    drawLeaf2(tess, i + this.nc.P21, i + this.nc.P29, j + this.nc.P00, j + this.nc.P20, k + this.nc.P20, k + this.nc.P28, uMinL, uMaxL, vMinL, vMaxL, this.nc.P01, this.nc.P03);
                }
                if (tmpxz <= 4 || tmpxz >= 6) {
                    double xMin3 = i + this.nc.P07;
                    double xMax3 = i + this.nc.P11;
                    double yMin3 = j + (-this.nc.P11);
                    double yMax4 = j + (-this.nc.P01);
                    double zMin3 = k + this.nc.P07;
                    double zMax3 = k + this.nc.P11;
                    drawTop(tess, xMin3, xMax3, yMin3, yMax4, zMin3, zMax3, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin3, xMax3, yMin3, yMax4, zMin3, zMax3, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin3, xMax3, yMin3, yMax4, zMin3, zMax3, uMin, uMax, vMin, vMax);
                    drawLeaf2(tess, i + this.nc.P05, i + this.nc.P13, j + (-this.nc.P01), j + this.nc.P19, k + this.nc.P05, k + this.nc.P13, uMinL, uMaxL, vMinL, vMaxL, this.nc.P01, this.nc.P03);
                }
                if (tmpxz <= 7 || tmpxz > 9) {
                    double xMin4 = i + this.nc.P21;
                    double xMax4 = i + this.nc.P25;
                    double yMin4 = j + (-this.nc.P09);
                    double yMax5 = j + this.nc.P01;
                    double zMin4 = k + this.nc.P05;
                    double zMax4 = k + this.nc.P09;
                    drawTop(tess, xMin4, xMax4, yMin4, yMax5, zMin4, zMax4, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin4, xMax4, yMin4, yMax5, zMin4, zMax4, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin4, xMax4, yMin4, yMax5, zMin4, zMax4, uMin, uMax, vMin, vMax);
                    drawLeaf2(tess, i + this.nc.P19, i + this.nc.P27, j + this.nc.P01, j + this.nc.P21, k + this.nc.P03, k + this.nc.P11, uMinL, uMaxL, vMinL, vMaxL, this.nc.P01, this.nc.P03);
                    break;
                }
                break;
            case 6:
                if (tmpxz < 0 || tmpxz >= 3) {
                    double xMin5 = i + this.nc.P03;
                    double xMax5 = i + this.nc.P11;
                    double yMin5 = j + (-this.nc.P14);
                    double yMax6 = j + this.nc.P02;
                    double zMin5 = k + this.nc.P21;
                    double zMax5 = k + this.nc.P29;
                    drawTop(tess, xMin5, xMax5, yMin5, yMax6, zMin5, zMax5, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin5, xMax5, yMin5, yMax6, zMin5, zMax5, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin5, xMax5, yMin5, yMax6, zMin5, zMax5, uMin, uMax, vMin, vMax);
                    drawLeaf2(tess, i + this.nc.P03, i + this.nc.P11, j + this.nc.P02, j + this.nc.P25, k + this.nc.P21, k + this.nc.P29, uMinL, uMaxL, vMinL, vMaxL, this.nc.P02, this.nc.P03);
                }
                if (tmpxz <= 2 || tmpxz >= 5) {
                    double xMin6 = i + this.nc.P21;
                    double xMax6 = i + this.nc.P29;
                    double yMin6 = j + (-this.nc.P13);
                    double yMax7 = j + this.nc.P03;
                    double zMin6 = k + this.nc.P20;
                    double zMax6 = k + this.nc.P28;
                    drawTop(tess, xMin6, xMax6, yMin6, yMax7, zMin6, zMax6, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin6, xMax6, yMin6, yMax7, zMin6, zMax6, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin6, xMax6, yMin6, yMax7, zMin6, zMax6, uMin, uMax, vMin, vMax);
                    drawLeaf2(tess, i + this.nc.P21, i + this.nc.P29, j + this.nc.P03, j + this.nc.P26, k + this.nc.P20, k + this.nc.P28, uMinL, uMaxL, vMinL, vMaxL, this.nc.P02, this.nc.P03);
                }
                if (tmpxz <= 4 || tmpxz >= 6) {
                    double xMin7 = i + this.nc.P05;
                    double xMax7 = i + this.nc.P13;
                    double yMin7 = j + (-this.nc.P15);
                    double yMax8 = j + this.nc.P01;
                    double zMin7 = k + this.nc.P05;
                    double zMax7 = k + this.nc.P13;
                    drawTop(tess, xMin7, xMax7, yMin7, yMax8, zMin7, zMax7, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin7, xMax7, yMin7, yMax8, zMin7, zMax7, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin7, xMax7, yMin7, yMax8, zMin7, zMax7, uMin, uMax, vMin, vMax);
                    drawLeaf2(tess, i + this.nc.P05, i + this.nc.P13, j + this.nc.P01, j + this.nc.P24, k + this.nc.P05, k + this.nc.P13, uMinL, uMaxL, vMinL, vMaxL, this.nc.P02, this.nc.P03);
                }
                if (tmpxz <= 7 || tmpxz > 9) {
                    double xMin8 = i + this.nc.P19;
                    double xMax8 = i + this.nc.P27;
                    double yMin8 = j + (-this.nc.P14);
                    double yMax9 = j + this.nc.P02;
                    double zMin8 = k + this.nc.P03;
                    double zMax8 = k + this.nc.P11;
                    drawTop(tess, xMin8, xMax8, yMin8, yMax9, zMin8, zMax8, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin8, xMax8, yMin8, yMax9, zMin8, zMax8, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin8, xMax8, yMin8, yMax9, zMin8, zMax8, uMin, uMax, vMin, vMax);
                    drawLeaf2(tess, i + this.nc.P19, i + this.nc.P27, j + this.nc.P02, j + this.nc.P25, k + this.nc.P03, k + this.nc.P11, uMinL, uMaxL, vMinL, vMaxL, this.nc.P02, this.nc.P03);
                    break;
                }
                break;
            case 7:
                if (tmpxz < 0 || tmpxz >= 3) {
                    double xMin9 = i + this.nc.P02;
                    double xMax9 = i + this.nc.P12;
                    double yMin9 = j + (-this.nc.P22);
                    double yMax10 = j + this.nc.P02;
                    double zMin9 = k + this.nc.P20;
                    double zMax9 = k + this.nc.P30;
                    drawTop(tess, xMin9, xMax9, yMin9, yMax10, zMin9, zMax9, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin9, xMax9, yMin9, yMax10, zMin9, zMax9, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin9, xMax9, yMin9, yMax10, zMin9, zMax9, uMin, uMax, vMin, vMax);
                    drawLeaf2(tess, i + this.nc.P03, i + this.nc.P11, j + this.nc.P02, j + this.nc.P32, k + this.nc.P21, k + this.nc.P29, uMinL, uMaxL, vMinL, vMaxL, this.nc.P03, this.nc.P03);
                }
                if (tmpxz <= 2 || tmpxz >= 5) {
                    double xMin10 = i + this.nc.P20;
                    double xMax10 = i + this.nc.P30;
                    double yMin10 = j + (-this.nc.P21);
                    double yMax11 = j + this.nc.P03;
                    double zMin10 = k + this.nc.P19;
                    double zMax10 = k + this.nc.P29;
                    drawTop(tess, xMin10, xMax10, yMin10, yMax11, zMin10, zMax10, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin10, xMax10, yMin10, yMax11, zMin10, zMax10, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin10, xMax10, yMin10, yMax11, zMin10, zMax10, uMin, uMax, vMin, vMax);
                    drawLeaf2(tess, i + this.nc.P21, i + this.nc.P29, j + this.nc.P03, j + this.nc.P32, k + this.nc.P20, k + this.nc.P28, uMinL, uMaxL, vMinL, vMaxL, this.nc.P03, this.nc.P03);
                }
                if (tmpxz <= 4 || tmpxz >= 6) {
                    double xMin11 = i + this.nc.P04;
                    double xMax11 = i + this.nc.P14;
                    double yMin11 = j + (-this.nc.P24);
                    double yMax12 = j + this.nc.P00;
                    double zMin11 = k + this.nc.P04;
                    double zMax11 = k + this.nc.P14;
                    drawTop(tess, xMin11, xMax11, yMin11, yMax12, zMin11, zMax11, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin11, xMax11, yMin11, yMax12, zMin11, zMax11, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin11, xMax11, yMin11, yMax12, zMin11, zMax11, uMin, uMax, vMin, vMax);
                    drawLeaf2(tess, i + this.nc.P05, i + this.nc.P13, j + this.nc.P00, j + this.nc.P30, k + this.nc.P05, k + this.nc.P13, uMinL, uMaxL, vMinL, vMaxL, this.nc.P03, this.nc.P03);
                }
                if (tmpxz <= 7 || tmpxz > 9) {
                    double xMin12 = i + this.nc.P18;
                    double xMax12 = i + this.nc.P28;
                    double yMin12 = j + (-this.nc.P22);
                    double yMax13 = j + this.nc.P02;
                    double zMin12 = k + this.nc.P02;
                    double zMax12 = k + this.nc.P12;
                    drawTop(tess, xMin12, xMax12, yMin12, yMax13, zMin12, zMax12, uMinT, uMaxT, vMinT, vMaxT);
                    drawBottom(tess, xMin12, xMax12, yMin12, yMax13, zMin12, zMax12, uMinB, uMaxB, vMinB, vMaxB);
                    drawSide(tess, xMin12, xMax12, yMin12, yMax13, zMin12, zMax12, uMin, uMax, vMin, vMax);
                    drawLeaf2(tess, i + this.nc.P19, i + this.nc.P27, j + this.nc.P02, j + this.nc.P32, k + this.nc.P03, k + this.nc.P11, uMinL, uMaxL, vMinL, vMaxL, this.nc.P03, this.nc.P03);
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

    private void drawLeaf2(Tessellator tess, double minX, double maxX, double minY, double maxY, double minZ, double maxZ, double U1, double U2, double V1, double V2, double difference, double difference2) {
        tess.func_78374_a(minX - difference, maxY, maxZ, U1, V1);
        tess.func_78374_a(minX + difference2, minY, maxZ, U1, V2);
        tess.func_78374_a(minX + difference2, minY, minZ, U2, V2);
        tess.func_78374_a(minX - difference, maxY, minZ, U2, V1);
        tess.func_78374_a(minX - difference, maxY, minZ, U1, V1);
        tess.func_78374_a(minX + difference2, minY, minZ, U1, V2);
        tess.func_78374_a(minX + difference2, minY, maxZ, U2, V2);
        tess.func_78374_a(minX - difference, maxY, maxZ, U2, V1);
        tess.func_78374_a(maxX + difference, maxY, minZ, U1, V1);
        tess.func_78374_a(maxX - difference2, minY, minZ, U1, V2);
        tess.func_78374_a(maxX - difference2, minY, maxZ, U2, V2);
        tess.func_78374_a(maxX + difference, maxY, maxZ, U2, V1);
        tess.func_78374_a(maxX + difference, maxY, maxZ, U1, V1);
        tess.func_78374_a(maxX - difference2, minY, maxZ, U1, V2);
        tess.func_78374_a(maxX - difference2, minY, minZ, U2, V2);
        tess.func_78374_a(maxX + difference, maxY, minZ, U2, V1);
        tess.func_78374_a(minX, maxY, minZ - difference, U1, V1);
        tess.func_78374_a(minX, minY, minZ + difference2, U1, V2);
        tess.func_78374_a(maxX, minY, minZ + difference2, U2, V2);
        tess.func_78374_a(maxX, maxY, minZ - difference, U2, V1);
        tess.func_78374_a(maxX, maxY, minZ - difference, U1, V1);
        tess.func_78374_a(maxX, minY, minZ + difference2, U1, V2);
        tess.func_78374_a(minX, minY, minZ + difference2, U2, V2);
        tess.func_78374_a(minX, maxY, minZ - difference, U2, V1);
        tess.func_78374_a(maxX, maxY, maxZ + difference, U1, V1);
        tess.func_78374_a(maxX, minY, maxZ - difference2, U1, V2);
        tess.func_78374_a(minX, minY, maxZ - difference2, U2, V2);
        tess.func_78374_a(minX, maxY, maxZ + difference, U2, V1);
        tess.func_78374_a(minX, maxY, maxZ + difference, U1, V1);
        tess.func_78374_a(minX, minY, maxZ - difference2, U1, V2);
        tess.func_78374_a(maxX, minY, maxZ - difference2, U2, V2);
        tess.func_78374_a(maxX, maxY, maxZ + difference, U2, V1);
    }
}
