package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockGrapeTub;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderGrapeTub implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        render(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    private void render(IBlockAccess iblockaccess, int i, int j, int k, Block block, int renderType, RenderBlocks renderblocks) {
        IIcon inner;
        float OFFSET3 = this.nc.P02;
        Block pos_n = iblockaccess.func_147439_a(i, j, k - 1);
        Block pos_s = iblockaccess.func_147439_a(i, j, k + 1);
        Block pos_w = iblockaccess.func_147439_a(i - 1, j, k);
        Block pos_e = iblockaccess.func_147439_a(i + 1, j, k);
        Block pos_ne = iblockaccess.func_147439_a(i + 1, j, k - 1);
        Block pos_nw = iblockaccess.func_147439_a(i - 1, j, k - 1);
        Block pos_se = iblockaccess.func_147439_a(i + 1, j, k + 1);
        Block pos_sw = iblockaccess.func_147439_a(i - 1, j, k + 1);
        if ((iblockaccess.func_72805_g(i, j, k) & 1) == 1) {
            inner = ecru_BlockGrapeTub.tx_inner2;
        } else {
            inner = ecru_BlockGrapeTub.tx_inner1;
        }
        renderblocks.func_147757_a(inner);
        block.func_149676_a(this.nc.P00 + OFFSET3, this.nc.P00, this.nc.P00 + OFFSET3, this.nc.P32 - OFFSET3, this.nc.P08, this.nc.P32 - OFFSET3);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        renderblocks.func_147771_a();
        if (pos_n != block && pos_n != mod_ecru_MapleTree.blockHumanPowerDrive) {
            block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P02);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        } else {
            renderblocks.func_147757_a(inner);
            block.func_149676_a(this.nc.P00 + 0.001f, this.nc.P00, this.nc.P00, this.nc.P32 - 0.001f, this.nc.P08, this.nc.P02);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            renderblocks.func_147771_a();
        }
        if (pos_s != block && pos_s != mod_ecru_MapleTree.blockHumanPowerDrive) {
            block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P30, this.nc.P32, this.nc.P32, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        } else {
            renderblocks.func_147757_a(inner);
            block.func_149676_a(this.nc.P00 + 0.001f, this.nc.P00, this.nc.P30, this.nc.P32 - 0.001f, this.nc.P08, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            renderblocks.func_147771_a();
        }
        if (pos_w != block && pos_w != mod_ecru_MapleTree.blockHumanPowerDrive) {
            block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P02, this.nc.P32, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        } else {
            renderblocks.func_147757_a(inner);
            block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00 + 0.001f, this.nc.P02, this.nc.P08, this.nc.P32 - 0.001f);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            renderblocks.func_147771_a();
        }
        if (pos_e != block && pos_e != mod_ecru_MapleTree.blockHumanPowerDrive) {
            block.func_149676_a(this.nc.P30, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        } else {
            renderblocks.func_147757_a(inner);
            block.func_149676_a(this.nc.P30, this.nc.P00, this.nc.P00 + 0.001f, this.nc.P32, this.nc.P08, this.nc.P32 - 0.001f);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
            renderblocks.func_147771_a();
        }
        if (pos_n == block && pos_e == block && pos_ne != block && pos_ne != mod_ecru_MapleTree.blockHumanPowerDrive) {
            block.func_149676_a(this.nc.P30, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P32 - 0.001f, this.nc.P02);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        }
        if (pos_n == block && pos_w == block && pos_nw != block && pos_nw != mod_ecru_MapleTree.blockHumanPowerDrive) {
            block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P02, this.nc.P32 - 0.001f, this.nc.P02);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        }
        if (pos_s == block && pos_e == block && pos_se != block && pos_se != mod_ecru_MapleTree.blockHumanPowerDrive) {
            block.func_149676_a(this.nc.P30, this.nc.P00, this.nc.P30, this.nc.P32, this.nc.P32 - 0.001f, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        }
        if (pos_s == block && pos_w == block && pos_sw != block && pos_sw != mod_ecru_MapleTree.blockHumanPowerDrive) {
            block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P30, this.nc.P02, this.nc.P32 - 0.001f, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        }
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147782_a(this.nc.P00, this.nc.P00 + 0.001f, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P32);
        renderInv_draw(renderblocks, block, i);
        if ((i & 1) == 1) {
            renderblocks.func_147757_a(ecru_BlockGrapeTub.tx_inner2);
        } else {
            renderblocks.func_147757_a(ecru_BlockGrapeTub.tx_inner1);
        }
        renderblocks.func_147782_a(this.nc.P00 + this.nc.P02, this.nc.P00, this.nc.P00 + this.nc.P02, this.nc.P32 - this.nc.P02, this.nc.P00 + 0.001f, this.nc.P32 - this.nc.P02);
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
        return mod_ecru_MapleTree.renderGrapeStompTubID;
    }

    public boolean shouldRender3DInInventory(int meta) {
        return true;
    }
}
