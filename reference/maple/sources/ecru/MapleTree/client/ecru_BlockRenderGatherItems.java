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
public class ecru_BlockRenderGatherItems implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        render(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    private boolean render(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        if ((meta & 4) == 0) {
            block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P04, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderBlockLog(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
            return true;
        }
        switch (meta & 3) {
            case 0:
                block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P28, this.nc.P32, this.nc.P32, this.nc.P32);
                break;
            case 1:
                block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P04, this.nc.P32, this.nc.P32);
                break;
            case 2:
                block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P04);
                break;
            case 3:
                block.func_149676_a(this.nc.P28, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P32);
                break;
        }
        renderblocks.func_147775_a(block);
        renderBlockLog(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderInv(block, i, modelID, renderblocks);
    }

    private boolean renderBlockLog(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        if ((meta & 4) == 0) {
            int ll = meta & 3;
            switch (ll) {
                case 0:
                    renderblocks.field_147867_u = 3;
                    break;
                case 1:
                    renderblocks.field_147867_u = 2;
                    break;
                case 2:
                    renderblocks.field_147867_u = 0;
                    break;
                case 3:
                    renderblocks.field_147867_u = 1;
                    break;
            }
        } else {
            int ll2 = meta & 3;
            switch (ll2) {
                case 0:
                    renderblocks.field_147871_s = 1;
                    renderblocks.field_147869_t = 2;
                    break;
                case 1:
                    renderblocks.field_147875_q = 2;
                    renderblocks.field_147873_r = 1;
                    renderblocks.field_147867_u = 1;
                    renderblocks.field_147865_v = 2;
                    break;
                case 2:
                    renderblocks.field_147871_s = 2;
                    renderblocks.field_147869_t = 1;
                    renderblocks.field_147867_u = 3;
                    renderblocks.field_147865_v = 3;
                    break;
                case 3:
                    renderblocks.field_147875_q = 1;
                    renderblocks.field_147873_r = 2;
                    renderblocks.field_147867_u = 2;
                    renderblocks.field_147865_v = 1;
                    break;
            }
        }
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        renderblocks.field_147875_q = 0;
        renderblocks.field_147873_r = 0;
        renderblocks.field_147869_t = 0;
        renderblocks.field_147871_s = 0;
        renderblocks.field_147867_u = 0;
        renderblocks.field_147865_v = 0;
        return true;
    }

    private void renderInv(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147782_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P04, this.nc.P32);
        renderInv_draw(renderblocks, block, 0);
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
        return mod_ecru_MapleTree.renderGatherItemsID;
    }

    public boolean shouldRender3DInInventory(int meta) {
        return true;
    }
}
