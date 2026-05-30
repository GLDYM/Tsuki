package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockVanilla;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderVanilla implements ISimpleBlockRenderingHandler {
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
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        renderSplint(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        renderBlockCropsImpl(block, meta, blockX, blockY, blockZ, renderblocks, iblockaccess);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    private void renderBlockCropsImpl(Block par1Block, int meta, double i, double j, double k, RenderBlocks renderblocks, IBlockAccess iblockaccess) {
        if ((meta & 7) == 0) {
            return;
        }
        Tessellator tessellator = Tessellator.field_78398_a;
        IIcon icon = par1Block.func_149691_a(0, meta & 7);
        tessellator.func_78380_c(par1Block.func_149677_c(iblockaccess, (int) i, (int) j, (int) k));
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        if (renderblocks.field_147840_d != null) {
            icon = renderblocks.field_147840_d;
        }
        double d3 = icon.func_94209_e();
        double d4 = icon.func_94206_g();
        double d5 = icon.func_94212_f();
        double d6 = icon.func_94210_h();
        double d7 = ((i + 0.5d) - 0.25d) - 0.001d;
        double d8 = i + 0.5d + 0.25d + 0.001d;
        double d9 = (k + 0.5d) - 0.5d;
        double d10 = k + 0.5d + 0.5d;
        tessellator.func_78374_a(d7, j + 1.0d, d9, d3, d4);
        tessellator.func_78374_a(d7, j + 0.0d, d9, d3, d6);
        tessellator.func_78374_a(d7, j + 0.0d, d10, d5, d6);
        tessellator.func_78374_a(d7, j + 1.0d, d10, d5, d4);
        tessellator.func_78374_a(d7, j + 1.0d, d10, d3, d4);
        tessellator.func_78374_a(d7, j + 0.0d, d10, d3, d6);
        tessellator.func_78374_a(d7, j + 0.0d, d9, d5, d6);
        tessellator.func_78374_a(d7, j + 1.0d, d9, d5, d4);
        tessellator.func_78374_a(d8, j + 1.0d, d10, d3, d4);
        tessellator.func_78374_a(d8, j + 0.0d, d10, d3, d6);
        tessellator.func_78374_a(d8, j + 0.0d, d9, d5, d6);
        tessellator.func_78374_a(d8, j + 1.0d, d9, d5, d4);
        tessellator.func_78374_a(d8, j + 1.0d, d9, d3, d4);
        tessellator.func_78374_a(d8, j + 0.0d, d9, d3, d6);
        tessellator.func_78374_a(d8, j + 0.0d, d10, d5, d6);
        tessellator.func_78374_a(d8, j + 1.0d, d10, d5, d4);
        double d72 = (i + 0.5d) - 0.5d;
        double d82 = i + 0.5d + 0.5d;
        double d92 = ((k + 0.5d) - 0.25d) - 0.001d;
        double d102 = k + 0.5d + 0.25d + 0.001d;
        tessellator.func_78374_a(d72, j + 1.0d, d92, d3, d4);
        tessellator.func_78374_a(d72, j + 0.0d, d92, d3, d6);
        tessellator.func_78374_a(d82, j + 0.0d, d92, d5, d6);
        tessellator.func_78374_a(d82, j + 1.0d, d92, d5, d4);
        tessellator.func_78374_a(d82, j + 1.0d, d92, d3, d4);
        tessellator.func_78374_a(d82, j + 0.0d, d92, d3, d6);
        tessellator.func_78374_a(d72, j + 0.0d, d92, d5, d6);
        tessellator.func_78374_a(d72, j + 1.0d, d92, d5, d4);
        tessellator.func_78374_a(d82, j + 1.0d, d102, d3, d4);
        tessellator.func_78374_a(d82, j + 0.0d, d102, d3, d6);
        tessellator.func_78374_a(d72, j + 0.0d, d102, d5, d6);
        tessellator.func_78374_a(d72, j + 1.0d, d102, d5, d4);
        tessellator.func_78374_a(d72, j + 1.0d, d102, d3, d4);
        tessellator.func_78374_a(d72, j + 0.0d, d102, d3, d6);
        tessellator.func_78374_a(d82, j + 0.0d, d102, d5, d6);
        tessellator.func_78374_a(d82, j + 1.0d, d102, d5, d4);
    }

    private int renderSplint(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        renderblocks.func_147757_a(ecru_BlockVanilla.tx_wood);
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        if ((meta & 8) == 8) {
            block.func_149676_a(this.P09, this.P00, this.P09, this.P10, this.P32, this.P10);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P09, this.P00, this.P22, this.P10, this.P32, this.P23);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P22, this.P00, this.P09, this.P23, this.P32, this.P10);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P22, this.P00, this.P22, this.P23, this.P32, this.P23);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P08, this.P20, this.P01, this.P09, this.P21, this.P31);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P23, this.P20, this.P01, this.P24, this.P21, this.P31);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P01, this.P21, this.P08, this.P31, this.P22, this.P09);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P01, this.P21, this.P23, this.P31, this.P22, this.P24);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        } else {
            block.func_149676_a(this.P05, this.P00, this.P05, this.P06, this.P32, this.P06);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P05, this.P00, this.P26, this.P06, this.P32, this.P27);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P26, this.P00, this.P05, this.P27, this.P32, this.P06);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P26, this.P00, this.P26, this.P27, this.P32, this.P27);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P04, this.P20, this.P01, this.P05, this.P21, this.P31);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P27, this.P20, this.P01, this.P28, this.P21, this.P31);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P01, this.P21, this.P04, this.P31, this.P22, this.P05);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P01, this.P21, this.P27, this.P31, this.P22, this.P28);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
        renderblocks.func_147771_a();
        return 0;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147757_a(ecru_BlockVanilla.tx_wood);
        if ((i & 8) == 8) {
            renderblocks.func_147782_a(this.P09, this.P00, this.P09, this.P11, this.P32, this.P11);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.P09, this.P00, this.P21, this.P11, this.P32, this.P23);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.P21, this.P00, this.P09, this.P23, this.P32, this.P11);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.P21, this.P00, this.P21, this.P23, this.P32, this.P23);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.P07, this.P20, this.P01, this.P09, this.P22, this.P31);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.P23, this.P20, this.P01, this.P25, this.P22, this.P31);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.P01, this.P22, this.P07, this.P31, this.P24, this.P09);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.P01, this.P22, this.P23, this.P31, this.P24, this.P25);
            renderInv_draw(renderblocks, block, i);
        } else {
            renderblocks.func_147782_a(this.P05, this.P00, this.P05, this.P07, this.P32, this.P07);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.P05, this.P00, this.P25, this.P07, this.P32, this.P27);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.P25, this.P00, this.P05, this.P27, this.P32, this.P07);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.P25, this.P00, this.P25, this.P27, this.P32, this.P27);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.P03, this.P20, this.P01, this.P05, this.P22, this.P31);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.P27, this.P20, this.P01, this.P29, this.P22, this.P31);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.P01, this.P22, this.P03, this.P31, this.P24, this.P05);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.P01, this.P22, this.P26, this.P31, this.P24, this.P28);
            renderInv_draw(renderblocks, block, i);
        }
        renderblocks.func_147771_a();
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

    public int getRenderId() {
        return mod_ecru_MapleTree.renderVanillaID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}
