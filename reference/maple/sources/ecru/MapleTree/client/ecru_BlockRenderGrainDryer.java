package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockGrainDryer;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderGrainDryer implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        render(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks, meta);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    private void render(IBlockAccess iblockaccess, int i, int j, int k, Block block, int renderType, RenderBlocks renderblocks, int meta) {
        block.func_149676_a(this.nc.P00, this.nc.P30, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P01, this.nc.P10, this.nc.P01, this.nc.P31, this.nc.P30, this.nc.P31);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P02, this.nc.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P00, this.nc.P02, this.nc.P00, this.nc.P04, this.nc.P12, this.nc.P04);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P00, this.nc.P02, this.nc.P28, this.nc.P04, this.nc.P12, this.nc.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P28, this.nc.P02, this.nc.P00, this.nc.P32, this.nc.P12, this.nc.P04);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        block.func_149676_a(this.nc.P28, this.nc.P02, this.nc.P28, this.nc.P32, this.nc.P12, this.nc.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        renderblocks.field_147840_d = ecru_BlockGrainDryer.tx_bottom;
        block.func_149676_a(this.nc.P00 + 0.001f, this.nc.P02 + 0.001f, this.nc.P00 + 0.001f, this.nc.P32 + 0.001f, this.nc.P02 + 0.002f, this.nc.P32 - 0.001f);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, i, j, k);
        renderblocks.func_147771_a();
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147782_a(this.nc.P00, this.nc.P30, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P32);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P01, this.nc.P10, this.nc.P01, this.nc.P31, this.nc.P30, this.nc.P31);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P02, this.nc.P32);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P00, this.nc.P02, this.nc.P00, this.nc.P04, this.nc.P10, this.nc.P04);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P00, this.nc.P02, this.nc.P28, this.nc.P04, this.nc.P10, this.nc.P32);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P28, this.nc.P02, this.nc.P00, this.nc.P32, this.nc.P10, this.nc.P04);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P28, this.nc.P02, this.nc.P28, this.nc.P32, this.nc.P10, this.nc.P32);
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

    public int getRenderId() {
        return mod_ecru_MapleTree.renderGrainDryerID;
    }

    public boolean shouldRender3DInInventory(int meta) {
        return true;
    }
}
