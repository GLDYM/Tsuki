package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderTeuchiUdon implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        int m = (meta & 8) == 8 ? 3 : 0;
        switch (meta & 7) {
            case 0:
                setBlockBoundsDis(this.nc.P02, this.nc.P00, this.nc.P02, this.nc.P30, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                break;
            case 1:
                setBlockBoundsDis(this.nc.P02, this.nc.P00, this.nc.P02, this.nc.P04, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P05, this.nc.P00, this.nc.P02, this.nc.P30, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                break;
            case 2:
                setBlockBoundsDis(this.nc.P02, this.nc.P00, this.nc.P02, this.nc.P04, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P05, this.nc.P00, this.nc.P02, this.nc.P07, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P08, this.nc.P00, this.nc.P02, this.nc.P30, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                break;
            case 3:
                setBlockBoundsDis(this.nc.P02, this.nc.P00, this.nc.P02, this.nc.P04, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P05, this.nc.P00, this.nc.P02, this.nc.P07, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P08, this.nc.P00, this.nc.P02, this.nc.P11, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P12, this.nc.P00, this.nc.P02, this.nc.P30, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                break;
            case 4:
                setBlockBoundsDis(this.nc.P02, this.nc.P00, this.nc.P02, this.nc.P04, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P05, this.nc.P00, this.nc.P02, this.nc.P07, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P08, this.nc.P00, this.nc.P02, this.nc.P11, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P12, this.nc.P00, this.nc.P02, this.nc.P15, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P16, this.nc.P00, this.nc.P02, this.nc.P30, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                break;
            case 5:
                setBlockBoundsDis(this.nc.P02, this.nc.P00, this.nc.P02, this.nc.P04, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P05, this.nc.P00, this.nc.P02, this.nc.P07, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P08, this.nc.P00, this.nc.P02, this.nc.P11, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P12, this.nc.P00, this.nc.P02, this.nc.P15, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P16, this.nc.P00, this.nc.P02, this.nc.P19, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P20, this.nc.P00, this.nc.P02, this.nc.P30, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                break;
            case 6:
                setBlockBoundsDis(this.nc.P02, this.nc.P00, this.nc.P02, this.nc.P04, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P05, this.nc.P00, this.nc.P02, this.nc.P07, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P08, this.nc.P00, this.nc.P02, this.nc.P11, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P12, this.nc.P00, this.nc.P02, this.nc.P15, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P16, this.nc.P00, this.nc.P02, this.nc.P19, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P20, this.nc.P00, this.nc.P02, this.nc.P23, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P24, this.nc.P00, this.nc.P02, this.nc.P30, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                break;
            case 7:
                setBlockBoundsDis(this.nc.P02, this.nc.P00, this.nc.P02, this.nc.P04, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P05, this.nc.P00, this.nc.P02, this.nc.P07, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P08, this.nc.P00, this.nc.P02, this.nc.P11, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P12, this.nc.P00, this.nc.P02, this.nc.P15, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P16, this.nc.P00, this.nc.P02, this.nc.P19, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P20, this.nc.P00, this.nc.P02, this.nc.P23, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P24, this.nc.P00, this.nc.P02, this.nc.P27, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                setBlockBoundsDis(this.nc.P28, this.nc.P00, this.nc.P02, this.nc.P30, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                break;
            default:
                setBlockBoundsDis(this.nc.P02, this.nc.P00, this.nc.P02, this.nc.P30, this.nc.P08, this.nc.P30, m, block);
                renderblocks.func_147775_a(block);
                renderBlockUdon(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                break;
        }
        return true;
    }

    private boolean renderBlockUdon(RenderBlocks renderblocks, IBlockAccess blockAccess, Block par1Block, int par2, int par3, int par4, int flg) {
        int meta = blockAccess.func_72805_g(par2, par3, par4);
        int Direction = meta & 8;
        if (flg == 1) {
            Direction = ((Direction & 8) ^ (-1)) & 8;
        }
        if (Direction == 0) {
            renderblocks.field_147875_q = 2;
            renderblocks.field_147873_r = 1;
            renderblocks.field_147867_u = 1;
            renderblocks.field_147865_v = 2;
        } else if (Direction == 8) {
            renderblocks.field_147871_s = 1;
            renderblocks.field_147869_t = 2;
        }
        boolean var7 = renderblocks.func_147784_q(par1Block, par2, par3, par4);
        renderblocks.field_147871_s = 0;
        renderblocks.field_147875_q = 0;
        renderblocks.field_147873_r = 0;
        renderblocks.field_147869_t = 0;
        renderblocks.field_147867_u = 0;
        renderblocks.field_147865_v = 0;
        return var7;
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

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147782_a(this.nc.P02, this.nc.P00, this.nc.P02, this.nc.P30, this.nc.P08, this.nc.P30);
        renderInv_draw(renderblocks, block, 0);
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderTeuchiUdonID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    private void setBlockBoundsDis(float a, float b, float c, float d, float e, float f, int meta, Block block) {
        switch (meta) {
            case 0:
            default:
                block.func_149676_a(a, b, c, d, e, f);
                break;
            case 1:
                block.func_149676_a(this.nc.P32 - f < this.nc.P32 - c ? this.nc.P32 - f : this.nc.P32 - c, e < b ? e : b, a < d ? a : d, this.nc.P32 - c > this.nc.P32 - f ? this.nc.P32 - c : this.nc.P32 - f, b > e ? b : e, d > a ? d : a);
                break;
            case 2:
                block.func_149676_a(this.nc.P32 - d < this.nc.P32 - a ? this.nc.P32 - d : this.nc.P32 - a, e < b ? e : b, this.nc.P32 - f < this.nc.P32 - c ? this.nc.P32 - f : this.nc.P32 - c, this.nc.P32 - a > this.nc.P32 - d ? this.nc.P32 - a : this.nc.P32 - d, b > e ? b : e, this.nc.P32 - c > this.nc.P32 - f ? this.nc.P32 - c : this.nc.P32 - f);
                break;
            case 3:
                block.func_149676_a(c < f ? c : f, e < b ? e : b, this.nc.P32 - a < this.nc.P32 - d ? this.nc.P32 - a : this.nc.P32 - d, f > c ? f : c, b > e ? b : e, this.nc.P32 - d > this.nc.P32 - a ? this.nc.P32 - d : this.nc.P32 - a);
                break;
        }
    }
}
