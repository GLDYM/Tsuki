package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderPetal implements ISimpleBlockRenderingHandler {
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
        render(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        block.func_149676_a(0.15625f, 0.15625f, 0.15625f, 0.84375f, 0.84375f, 0.84375f);
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderInv(block, i, modelID, renderblocks);
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderPetalID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    private boolean render(IBlockAccess iblockaccess, int i, int j, int k, Block block, int renderType, RenderBlocks renderblocks) {
        Material mmxp = iblockaccess.func_147439_a(i + 1, j, k).func_149688_o();
        Material mmxm = iblockaccess.func_147439_a(i - 1, j, k).func_149688_o();
        Material mmzp = iblockaccess.func_147439_a(i, j, k + 1).func_149688_o();
        Material mmzm = iblockaccess.func_147439_a(i, j, k - 1).func_149688_o();
        Material mmyp = iblockaccess.func_147439_a(i, j + 1, k).func_149688_o();
        Material mmym = iblockaccess.func_147439_a(i, j - 1, k).func_149688_o();
        int chk = 0;
        if (mmxp == Material.field_151584_j) {
            chk = 0 + 1;
            block.func_149676_a(this.P25, this.P24, this.P01, this.P31, this.P31, this.P07);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P19, this.P22, this.P12, this.P27, this.P31, this.P20);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P23, this.P20, this.P24, this.P30, this.P28, this.P31);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P19, this.P13, this.P16, this.P25, this.P20, this.P22);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P22, this.P09, this.P00, this.P27, this.P15, this.P05);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P20, this.P08, this.P08, this.P26, this.P15, this.P14);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P18, this.P03, this.P15, this.P26, this.P12, this.P22);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P22, this.P01, this.P24, this.P28, this.P08, this.P30);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P23, this.P01, this.P01, this.P29, this.P08, this.P07);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        }
        if (mmxm == Material.field_151584_j) {
            chk++;
            block.func_149676_a(this.P01, this.P24, this.P01, this.P07, this.P31, this.P07);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P05, this.P22, this.P12, this.P13, this.P31, this.P20);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P02, this.P20, this.P24, this.P09, this.P28, this.P31);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P02, this.P16, this.P05, this.P08, this.P23, this.P11);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P07, this.P13, this.P16, this.P13, this.P20, this.P22);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P05, this.P09, this.P00, this.P10, this.P15, this.P05);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P06, this.P08, this.P08, this.P12, this.P15, this.P14);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P06, this.P03, this.P15, this.P14, this.P12, this.P22);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P04, this.P01, this.P24, this.P10, this.P08, this.P30);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P03, this.P01, this.P01, this.P09, this.P08, this.P07);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        }
        if (mmzp == Material.field_151584_j) {
            chk++;
            block.func_149676_a(this.P01, this.P24, this.P25, this.P07, this.P31, this.P31);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P12, this.P22, this.P19, this.P20, this.P31, this.P27);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P24, this.P20, this.P23, this.P31, this.P28, this.P30);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P05, this.P16, this.P24, this.P11, this.P23, this.P30);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P25, this.P09, this.P22, this.P32, this.P17, this.P29);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P00, this.P09, this.P22, this.P05, this.P15, this.P27);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P08, this.P08, this.P20, this.P14, this.P15, this.P26);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P15, this.P03, this.P20, this.P22, this.P12, this.P28);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P09, this.P00, this.P25, this.P13, this.P05, this.P29);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        }
        if (mmzm == Material.field_151584_j) {
            chk++;
            block.func_149676_a(this.P01, this.P24, this.P01, this.P07, this.P31, this.P07);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P12, this.P22, this.P05, this.P20, this.P31, this.P13);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P24, this.P20, this.P02, this.P31, this.P28, this.P09);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P05, this.P16, this.P02, this.P11, this.P23, this.P08);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P25, this.P09, this.P03, this.P32, this.P17, this.P10);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P08, this.P08, this.P06, this.P14, this.P15, this.P12);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P15, this.P03, this.P04, this.P22, this.P12, this.P12);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P01, this.P01, this.P03, this.P07, this.P08, this.P09);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P09, this.P00, this.P03, this.P13, this.P05, this.P07);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        }
        if (mmym == Material.field_151584_j) {
            chk++;
            block.func_149676_a(this.P24, this.P00, this.P01, this.P31, this.P06, this.P07);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P22, this.P04, this.P12, this.P31, this.P12, this.P20);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P20, this.P01, this.P24, this.P28, this.P08, this.P31);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P16, this.P01, this.P05, this.P23, this.P07, this.P11);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P09, this.P02, this.P25, this.P17, this.P09, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P09, this.P04, this.P00, this.P15, this.P09, this.P05);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P08, this.P05, this.P08, this.P15, this.P11, this.P14);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P03, this.P03, this.P15, this.P12, this.P11, this.P22);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P01, this.P06, this.P24, this.P08, this.P12, this.P30);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P01, this.P02, this.P01, this.P08, this.P08, this.P07);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        }
        if (mmyp == Material.field_151584_j) {
            chk++;
            block.func_149676_a(this.P22, this.P20, this.P12, this.P31, this.P28, this.P20);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P20, this.P24, this.P24, this.P28, this.P31, this.P31);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P16, this.P25, this.P05, this.P23, this.P31, this.P11);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P13, this.P20, this.P16, this.P20, this.P26, this.P22);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P08, this.P21, this.P08, this.P15, this.P27, this.P14);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P03, this.P21, this.P15, this.P12, this.P29, this.P22);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P01, this.P20, this.P24, this.P08, this.P26, this.P30);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P00, this.P26, this.P09, this.P05, this.P30, this.P13);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        }
        if (chk == 0) {
            block.func_149676_a(this.P24, this.P00, this.P01, this.P31, this.P06, this.P07);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P22, this.P04, this.P12, this.P31, this.P12, this.P20);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P20, this.P01, this.P24, this.P28, this.P08, this.P31);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P16, this.P01, this.P05, this.P23, this.P07, this.P11);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P13, this.P06, this.P16, this.P20, this.P12, this.P22);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P09, this.P02, this.P25, this.P17, this.P09, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P09, this.P04, this.P00, this.P15, this.P09, this.P05);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P08, this.P05, this.P08, this.P15, this.P11, this.P14);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P03, this.P03, this.P15, this.P12, this.P11, this.P22);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P01, this.P06, this.P24, this.P08, this.P12, this.P30);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P01, this.P02, this.P01, this.P08, this.P08, this.P07);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            block.func_149676_a(this.P00, this.P02, this.P09, this.P05, this.P06, this.P13);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            return true;
        }
        return true;
    }

    private void renderInv(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147782_a(this.P24, this.P00, this.P01, this.P31, this.P06, this.P07);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.P22, this.P04, this.P12, this.P31, this.P12, this.P20);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.P20, this.P01, this.P24, this.P28, this.P08, this.P31);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.P16, this.P01, this.P05, this.P23, this.P07, this.P11);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.P13, this.P06, this.P16, this.P20, this.P12, this.P22);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.P09, this.P02, this.P25, this.P17, this.P09, this.P32);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.P09, this.P04, this.P00, this.P15, this.P09, this.P05);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.P08, this.P05, this.P08, this.P15, this.P11, this.P14);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.P03, this.P03, this.P15, this.P12, this.P11, this.P22);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.P01, this.P06, this.P24, this.P08, this.P12, this.P30);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.P01, this.P02, this.P01, this.P08, this.P08, this.P07);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.P00, this.P02, this.P09, this.P05, this.P06, this.P13);
        renderInv_draw(renderblocks, block, i);
    }

    private void renderInv_draw(RenderBlocks renderblocks, Block block, int i) {
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderblocks.func_147768_a(block, 0.0d, 0.0d, 0.0d, block.func_149691_a(0, i));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderblocks.func_147806_b(block, 0.0d, 0.0d, 0.0d, block.func_149691_a(1, i));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderblocks.func_147761_c(block, 0.0d, 0.0d, 0.0d, block.func_149691_a(2, i));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderblocks.func_147734_d(block, 0.0d, 0.0d, 0.0d, block.func_149691_a(3, i));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderblocks.func_147798_e(block, 0.0d, 0.0d, 0.0d, block.func_149691_a(4, i));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderblocks.func_147764_f(block, 0.0d, 0.0d, 0.0d, block.func_149691_a(5, i));
        tessellator.func_78381_a();
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
    }
}
