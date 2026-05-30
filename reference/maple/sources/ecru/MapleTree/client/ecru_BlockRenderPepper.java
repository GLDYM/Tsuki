package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockPepper;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderPepper implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        renderSplint(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        renderBlockCropsVine(block, meta, blockX, blockY, blockZ, renderblocks, iblockaccess);
        renderBlockCropsPeppercorn(block, meta, blockX, blockY, blockZ, renderblocks, iblockaccess);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    private void renderBlockCropsPeppercorn(Block block, int meta, int i, int j, int k, RenderBlocks renderblocks, IBlockAccess iblockaccess) {
        int t;
        if (meta < 4) {
            return;
        }
        float s = meta == 4 ? this.nc.P04 : meta == 5 ? this.nc.P02 : 0.0f;
        if (meta >= 4 && meta <= 5) {
            t = 0;
        } else if (meta >= 6 && meta <= 8) {
            t = 1;
        } else if (meta == 9) {
            t = 2;
        } else if (meta >= 10 && meta <= 14) {
            t = 3;
        } else {
            t = 4;
        }
        renderblocks.func_147757_a(ecru_BlockPepper.tx_peppercorn[t]);
        block.func_149676_a(this.nc.P02, this.nc.P02 + s, this.nc.P04, this.nc.P04, this.nc.P10, this.nc.P06);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P08, this.nc.P24 + s, this.nc.P02, this.nc.P10, this.nc.P30, this.nc.P04);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P18, this.nc.P12 + s, this.nc.P06, this.nc.P20, this.nc.P18, this.nc.P08);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P22, this.nc.P20 + s, this.nc.P02, this.nc.P24, this.nc.P28, this.nc.P04);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P26, this.nc.P04 + s, this.nc.P10, this.nc.P28, this.nc.P10, this.nc.P12);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P28, this.nc.P02 + s, this.nc.P14, this.nc.P30, this.nc.P08, this.nc.P16);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P26, this.nc.P22 + s, this.nc.P20, this.nc.P28, this.nc.P30, this.nc.P22);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P26, this.nc.P14 + s, this.nc.P28, this.nc.P28, this.nc.P22, this.nc.P30);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P20, this.nc.P12 + s, this.nc.P26, this.nc.P22, this.nc.P18, this.nc.P28);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P14, this.nc.P02 + s, this.nc.P26, this.nc.P16, this.nc.P08, this.nc.P28);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P08, this.nc.P22 + s, this.nc.P26, this.nc.P10, this.nc.P30, this.nc.P28);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P04, this.nc.P04 + s, this.nc.P26, this.nc.P06, this.nc.P10, this.nc.P28);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P02, this.nc.P08 + s, this.nc.P22, this.nc.P04, this.nc.P16, this.nc.P24);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P04, this.nc.P18 + s, this.nc.P16, this.nc.P06, this.nc.P26, this.nc.P18);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P02, this.nc.P16 + s, this.nc.P10, this.nc.P04, this.nc.P22, this.nc.P12);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        renderblocks.func_147771_a();
    }

    private void renderBlockCropsVine(Block par1Block, int meta, double i, double j, double k, RenderBlocks renderblocks, IBlockAccess iblockaccess) {
        if (meta == 0) {
            return;
        }
        if (meta >= 3) {
            meta = 3;
        }
        Tessellator tessellator = Tessellator.field_78398_a;
        IIcon icon = ecru_BlockPepper.tx_vine[meta];
        tessellator.func_78380_c(par1Block.func_149677_c(iblockaccess, (int) i, (int) j, (int) k));
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        if (renderblocks.field_147840_d != null) {
            icon = renderblocks.field_147840_d;
        }
        double offset = this.nc.P02 + 0.001d;
        double d3 = icon.func_94209_e();
        double d4 = icon.func_94206_g();
        double d5 = icon.func_94212_f();
        double d6 = icon.func_94210_h();
        double d7 = ((i + 0.5d) - 0.25d) - offset;
        double d8 = i + 0.5d + 0.25d + offset;
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
        double d92 = ((k + 0.5d) - 0.25d) - offset;
        double d102 = k + 0.5d + 0.25d + offset;
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
        renderblocks.func_147757_a(ecru_BlockPepper.tx_wood);
        block.func_149676_a(this.nc.P09, this.nc.P00, this.nc.P09, this.nc.P23, this.nc.P32, this.nc.P23);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P08, this.nc.P20, this.nc.P01, this.nc.P09, this.nc.P21, this.nc.P31);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P23, this.nc.P20, this.nc.P01, this.nc.P24, this.nc.P21, this.nc.P31);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P01, this.nc.P21, this.nc.P08, this.nc.P31, this.nc.P22, this.nc.P09);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P01, this.nc.P21, this.nc.P23, this.nc.P31, this.nc.P22, this.nc.P24);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        renderblocks.func_147771_a();
        return 0;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147757_a(ecru_BlockPepper.tx_wood);
        renderblocks.func_147782_a(this.nc.P09, this.nc.P00, this.nc.P09, this.nc.P23, this.nc.P32, this.nc.P23);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P08, this.nc.P20, this.nc.P01, this.nc.P09, this.nc.P21, this.nc.P31);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P23, this.nc.P20, this.nc.P01, this.nc.P24, this.nc.P21, this.nc.P31);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P01, this.nc.P21, this.nc.P08, this.nc.P31, this.nc.P22, this.nc.P09);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P01, this.nc.P21, this.nc.P23, this.nc.P31, this.nc.P22, this.nc.P24);
        renderInv_draw(renderblocks, block, i);
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
        return mod_ecru_MapleTree.renderPepperID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}
