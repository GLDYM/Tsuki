package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockCropsCabbage;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderCabbage implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        renderCabbage(renderblocks, iblockaccess, blockX, blockY, blockZ, block, renderType);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147782_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P32);
        renderInv_draw(renderblocks, block, i);
    }

    private void renderInv_draw(RenderBlocks renderblocks, Block block, int i) {
        renderblocks.func_147770_b(0.0d, 0.0d, 0.0d, 1.0d, 0.7d, 1.0d);
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderblocks.func_147768_a(block, 0.0d, 0.0d, 0.0d, ecru_BlockCropsCabbage.tx_bottom);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderblocks.func_147806_b(block, 0.0d, 0.0d, 0.0d, ecru_BlockCropsCabbage.tx_top);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderblocks.func_147761_c(block, 0.0d, 0.0d, 0.0d, ecru_BlockCropsCabbage.tx_side1);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderblocks.func_147734_d(block, 0.0d, 0.0d, 0.0d, ecru_BlockCropsCabbage.tx_side2);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderblocks.func_147798_e(block, 0.0d, 0.0d, 0.0d, ecru_BlockCropsCabbage.tx_side3);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderblocks.func_147764_f(block, 0.0d, 0.0d, 0.0d, ecru_BlockCropsCabbage.tx_side4);
        tessellator.func_78381_a();
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
        renderblocks.func_147762_c();
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderCabbageID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    private void renderCabbage(RenderBlocks renderblocks, IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType) {
        IIcon icon_side1;
        IIcon icon_side2;
        IIcon icon_side3;
        IIcon icon_side4;
        double x = blockX;
        double y = blockY;
        double z = blockZ;
        double[] U1 = new double[7];
        double[] V1 = new double[7];
        double[] U2 = new double[7];
        double[] V2 = new double[7];
        int tmpxz = Math.abs((((blockX % 10) + (blockY % 10)) + (blockZ % 10)) % 10);
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ) & 7;
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78380_c(block.func_149677_c(iblockaccess, blockX, blockY, blockZ));
        tess.func_78386_a(1.0f, 1.0f, 1.0f);
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
        tess.func_78386_a(1.0f * f1, 1.0f * f2, 1.0f * f3);
        IIcon icon_top = ecru_BlockCropsCabbage.tx_top;
        IIcon icon_bottom = ecru_BlockCropsCabbage.tx_bottom;
        IIcon icon_side5 = ecru_BlockCropsCabbage.tx_side5;
        if (tmpxz >= 0 && tmpxz < 3) {
            icon_side1 = ecru_BlockCropsCabbage.tx_side2;
            icon_side2 = ecru_BlockCropsCabbage.tx_side1;
            icon_side3 = ecru_BlockCropsCabbage.tx_side3;
            icon_side4 = ecru_BlockCropsCabbage.tx_side4;
        } else if (tmpxz > 2 && tmpxz < 6) {
            icon_side1 = ecru_BlockCropsCabbage.tx_side4;
            icon_side2 = ecru_BlockCropsCabbage.tx_side3;
            icon_side3 = ecru_BlockCropsCabbage.tx_side2;
            icon_side4 = ecru_BlockCropsCabbage.tx_side1;
        } else if (tmpxz > 5 && tmpxz < 8) {
            icon_side1 = ecru_BlockCropsCabbage.tx_side1;
            icon_side2 = ecru_BlockCropsCabbage.tx_side2;
            icon_side3 = ecru_BlockCropsCabbage.tx_side4;
            icon_side4 = ecru_BlockCropsCabbage.tx_side3;
        } else {
            icon_side1 = ecru_BlockCropsCabbage.tx_side3;
            icon_side2 = ecru_BlockCropsCabbage.tx_side4;
            icon_side3 = ecru_BlockCropsCabbage.tx_side1;
            icon_side4 = ecru_BlockCropsCabbage.tx_side2;
        }
        tess.func_78386_a(1.0f, 1.0f, 1.0f);
        tess.func_78380_c(block.func_149677_c(iblockaccess, blockX, blockY, blockZ));
        U1[0] = icon_bottom.func_94209_e();
        V1[0] = icon_bottom.func_94206_g();
        U2[0] = icon_bottom.func_94212_f();
        V2[0] = icon_bottom.func_94210_h();
        U1[1] = icon_top.func_94209_e();
        V1[1] = icon_top.func_94206_g();
        U2[1] = icon_top.func_94212_f();
        V2[1] = icon_top.func_94210_h();
        U1[2] = icon_side1.func_94209_e();
        V1[2] = icon_side1.func_94206_g();
        U2[2] = icon_side1.func_94212_f();
        V2[2] = icon_side1.func_94210_h();
        U1[3] = icon_side2.func_94209_e();
        V1[3] = icon_side2.func_94206_g();
        U2[3] = icon_side2.func_94212_f();
        V2[3] = icon_side2.func_94210_h();
        U1[4] = icon_side3.func_94209_e();
        V1[4] = icon_side3.func_94206_g();
        U2[4] = icon_side3.func_94212_f();
        V2[4] = icon_side3.func_94210_h();
        U1[5] = icon_side4.func_94209_e();
        V1[5] = icon_side4.func_94206_g();
        U2[5] = icon_side4.func_94212_f();
        V2[5] = icon_side4.func_94210_h();
        U1[6] = icon_side5.func_94209_e();
        V1[6] = icon_side5.func_94206_g();
        U2[6] = icon_side5.func_94212_f();
        V2[6] = icon_side5.func_94210_h();
        switch (meta) {
            case 0:
                _renderCabbage(x + this.nc.P15, y + this.nc.P00, z + this.nc.P15, x + this.nc.P17, y + this.nc.P02, z + this.nc.P17, tess, U1, U2, V1, V2, tmpxz);
                break;
            case 1:
                _renderCabbage(x + this.nc.P14, y + this.nc.P00, z + this.nc.P14, x + this.nc.P18, y + this.nc.P02, z + this.nc.P18, tess, U1, U2, V1, V2, tmpxz);
                break;
            case 2:
                _renderCabbage(x + this.nc.P13, y + this.nc.P00, z + this.nc.P13, x + this.nc.P19, y + this.nc.P03, z + this.nc.P19, tess, U1, U2, V1, V2, tmpxz);
                break;
            case 3:
                _renderCabbage(x + this.nc.P12, y + this.nc.P00, z + this.nc.P12, x + this.nc.P20, y + this.nc.P06, z + this.nc.P20, tess, U1, U2, V1, V2, tmpxz);
                break;
            case 4:
                _renderCabbage(x + this.nc.P11, y + this.nc.P00, z + this.nc.P11, x + this.nc.P21, y + this.nc.P09, z + this.nc.P21, tess, U1, U2, V1, V2, tmpxz);
                break;
            case 5:
                _renderCabbage(x + this.nc.P09, y + this.nc.P00, z + this.nc.P09, x + this.nc.P23, y + this.nc.P12, z + this.nc.P23, tess, U1, U2, V1, V2, tmpxz);
                break;
            case 6:
                _renderCabbage(x + this.nc.P07, y + this.nc.P00, z + this.nc.P07, x + this.nc.P25, y + this.nc.P15, z + this.nc.P25, tess, U1, U2, V1, V2, tmpxz);
                break;
            case 7:
            default:
                _renderCabbage(x + this.nc.P06, y + this.nc.P00, z + this.nc.P06, x + this.nc.P26, y + this.nc.P15, z + this.nc.P26, tess, U1, U2, V1, V2, tmpxz);
                _renderCabbage2(x + this.nc.P06, y + this.nc.P00, z + this.nc.P06, x + this.nc.P26, y + this.nc.P15, z + this.nc.P26, tess, U1, U2, V1, V2);
                break;
        }
        block.func_149683_g();
    }

    private void _renderCabbage(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, Tessellator tess, double[] U1, double[] U2, double[] V1, double[] V2, int tmpxz) {
        if (tmpxz >= 0 && tmpxz < 3) {
            tess.func_78374_a(maxX, maxY, minZ, U1[1], V1[1]);
            tess.func_78374_a(minX, maxY, minZ, U1[1], V2[1]);
            tess.func_78374_a(minX, maxY, maxZ, U2[1], V2[1]);
            tess.func_78374_a(maxX, maxY, maxZ, U2[1], V1[1]);
        } else if (tmpxz > 2 && tmpxz < 6) {
            tess.func_78374_a(maxX, maxY, maxZ, U1[1], V1[1]);
            tess.func_78374_a(maxX, maxY, minZ, U1[1], V2[1]);
            tess.func_78374_a(minX, maxY, minZ, U2[1], V2[1]);
            tess.func_78374_a(minX, maxY, maxZ, U2[1], V1[1]);
        } else if (tmpxz > 5 && tmpxz < 8) {
            tess.func_78374_a(minX, maxY, maxZ, U1[1], V1[1]);
            tess.func_78374_a(maxX, maxY, maxZ, U1[1], V2[1]);
            tess.func_78374_a(maxX, maxY, minZ, U2[1], V2[1]);
            tess.func_78374_a(minX, maxY, minZ, U2[1], V1[1]);
        } else {
            tess.func_78374_a(minX, maxY, minZ, U1[1], V1[1]);
            tess.func_78374_a(minX, maxY, maxZ, U1[1], V2[1]);
            tess.func_78374_a(maxX, maxY, maxZ, U2[1], V2[1]);
            tess.func_78374_a(maxX, maxY, minZ, U2[1], V1[1]);
        }
        if (tmpxz >= 0 && tmpxz < 3) {
            tess.func_78374_a(maxX, minY, maxZ, U1[0], V1[0]);
            tess.func_78374_a(minX, minY, maxZ, U1[0], V2[0]);
            tess.func_78374_a(minX, minY, minZ, U2[0], V2[0]);
            tess.func_78374_a(maxX, minY, minZ, U2[0], V1[0]);
        } else if (tmpxz > 2 && tmpxz < 6) {
            tess.func_78374_a(minX, minY, maxZ, U1[0], V1[0]);
            tess.func_78374_a(minX, minY, minZ, U1[0], V2[0]);
            tess.func_78374_a(maxX, minY, minZ, U2[0], V2[0]);
            tess.func_78374_a(maxX, minY, maxZ, U2[0], V1[0]);
        } else if (tmpxz > 5 && tmpxz < 8) {
            tess.func_78374_a(minX, minY, minZ, U1[0], V1[0]);
            tess.func_78374_a(maxX, minY, minZ, U1[0], V2[0]);
            tess.func_78374_a(maxX, minY, maxZ, U2[0], V2[0]);
            tess.func_78374_a(minX, minY, maxZ, U2[0], V1[0]);
        } else {
            tess.func_78374_a(maxX, minY, minZ, U1[0], V1[0]);
            tess.func_78374_a(maxX, minY, maxZ, U1[0], V2[0]);
            tess.func_78374_a(minX, minY, maxZ, U2[0], V2[0]);
            tess.func_78374_a(minX, minY, minZ, U2[0], V1[0]);
        }
        tess.func_78374_a(minX, maxY, minZ, U1[2], V1[2]);
        tess.func_78374_a(minX, minY, minZ, U1[2], V2[2]);
        tess.func_78374_a(minX, minY, maxZ, U2[2], V2[2]);
        tess.func_78374_a(minX, maxY, maxZ, U2[2], V1[2]);
        tess.func_78374_a(maxX, maxY, maxZ, U1[3], V1[3]);
        tess.func_78374_a(maxX, minY, maxZ, U1[3], V2[3]);
        tess.func_78374_a(maxX, minY, minZ, U2[3], V2[3]);
        tess.func_78374_a(maxX, maxY, minZ, U2[3], V1[3]);
        tess.func_78374_a(maxX, maxY, minZ, U1[4], V1[4]);
        tess.func_78374_a(maxX, minY, minZ, U1[4], V2[4]);
        tess.func_78374_a(minX, minY, minZ, U2[4], V2[4]);
        tess.func_78374_a(minX, maxY, minZ, U2[4], V1[4]);
        tess.func_78374_a(minX, maxY, maxZ, U1[5], V1[5]);
        tess.func_78374_a(minX, minY, maxZ, U1[5], V2[5]);
        tess.func_78374_a(maxX, minY, maxZ, U2[5], V2[5]);
        tess.func_78374_a(maxX, maxY, maxZ, U2[5], V1[5]);
    }

    private void _renderCabbage2(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, Tessellator tess, double[] U1, double[] U2, double[] V1, double[] V2) {
        tess.func_78374_a(minX - this.nc.P04, maxY + this.nc.P02, maxZ + this.nc.P02, U1[6], V1[6]);
        tess.func_78374_a(minX, minY, maxZ, U1[6], V2[6]);
        tess.func_78374_a(minX, minY, minZ, U2[6], V2[6]);
        tess.func_78374_a(minX - this.nc.P04, maxY + this.nc.P02, minZ - this.nc.P02, U2[6], V1[6]);
        tess.func_78374_a(minX - this.nc.P04, maxY + this.nc.P02, minZ - this.nc.P02, U1[6], V1[6]);
        tess.func_78374_a(minX, minY, minZ, U1[6], V2[6]);
        tess.func_78374_a(minX, minY, maxZ, U2[6], V2[6]);
        tess.func_78374_a(minX - this.nc.P04, maxY + this.nc.P02, maxZ + this.nc.P02, U2[6], V1[6]);
        tess.func_78374_a(maxX + this.nc.P04, maxY + this.nc.P02, minZ - this.nc.P02, U1[6], V1[6]);
        tess.func_78374_a(maxX, minY, minZ, U1[6], V2[6]);
        tess.func_78374_a(maxX, minY, maxZ, U2[6], V2[6]);
        tess.func_78374_a(maxX + this.nc.P04, maxY + this.nc.P02, maxZ + this.nc.P02, U2[6], V1[6]);
        tess.func_78374_a(maxX + this.nc.P04, maxY + this.nc.P02, maxZ + this.nc.P02, U1[6], V1[6]);
        tess.func_78374_a(maxX, minY, maxZ, U1[6], V2[6]);
        tess.func_78374_a(maxX, minY, minZ, U2[6], V2[6]);
        tess.func_78374_a(maxX + this.nc.P04, maxY + this.nc.P02, minZ - this.nc.P02, U2[6], V1[6]);
        tess.func_78374_a(minX - this.nc.P02, maxY + this.nc.P02, minZ - this.nc.P04, U1[6], V1[6]);
        tess.func_78374_a(minX, minY, minZ, U1[6], V2[6]);
        tess.func_78374_a(maxX, minY, minZ, U2[6], V2[6]);
        tess.func_78374_a(maxX + this.nc.P02, maxY + this.nc.P02, minZ - this.nc.P04, U2[6], V1[6]);
        tess.func_78374_a(maxX + this.nc.P02, maxY + this.nc.P02, minZ - this.nc.P04, U1[6], V1[6]);
        tess.func_78374_a(maxX, minY, minZ, U1[6], V2[6]);
        tess.func_78374_a(minX, minY, minZ, U2[6], V2[6]);
        tess.func_78374_a(minX - this.nc.P02, maxY + this.nc.P02, minZ - this.nc.P04, U2[6], V1[6]);
        tess.func_78374_a(maxX + this.nc.P02, maxY + this.nc.P02, maxZ + this.nc.P04, U1[6], V1[6]);
        tess.func_78374_a(maxX, minY, maxZ, U1[6], V2[6]);
        tess.func_78374_a(minX, minY, maxZ, U2[6], V2[6]);
        tess.func_78374_a(minX - this.nc.P02, maxY + this.nc.P02, maxZ + this.nc.P04, U2[6], V1[6]);
        tess.func_78374_a(minX - this.nc.P02, maxY + this.nc.P02, maxZ + this.nc.P04, U1[6], V1[6]);
        tess.func_78374_a(minX, minY, maxZ, U1[6], V2[6]);
        tess.func_78374_a(maxX, minY, maxZ, U2[6], V2[6]);
        tess.func_78374_a(maxX + this.nc.P02, maxY + this.nc.P02, maxZ + this.nc.P04, U2[6], V1[6]);
    }
}
