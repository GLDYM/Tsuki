package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockGrainHopper;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderGrainHopper implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        render(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderInv(block, i, modelID, renderblocks);
        renderblocks.func_147771_a();
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderGrainHopperID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    private void renderInv(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147782_a(this.nc.P00, this.nc.P30, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P32);
        renderInv_draw(renderblocks, block, 0);
        renderblocks.func_147782_a(this.nc.P00, this.nc.P06, this.nc.P00, this.nc.P32, this.nc.P08, this.nc.P32);
        renderInv_draw(renderblocks, block, 0);
        renderblocks.func_147782_a(this.nc.P04, this.nc.P04, this.nc.P04, this.nc.P28, this.nc.P06, this.nc.P28);
        renderInv_draw(renderblocks, block, 0);
        renderblocks.func_147782_a(this.nc.P06, this.nc.P00, this.nc.P06, this.nc.P26, this.nc.P04, this.nc.P26);
        renderInv_draw(renderblocks, block, 0);
        renderblocks.field_147840_d = ecru_BlockGrainHopper.tx_glass;
        renderblocks.func_147782_a(this.nc.P00, this.nc.P08, this.nc.P00, this.nc.P32, this.nc.P30 + 0.001f, this.nc.P32);
        renderInv_draw(renderblocks, block, 0);
        renderblocks.field_147840_d = ecru_BlockGrainHopper.tx_bottom;
        renderblocks.func_147782_a(this.nc.P00 + 0.001f, this.nc.P08, this.nc.P00 + 0.001f, this.nc.P32 - 0.001f, this.nc.P08 + 0.001f, this.nc.P32 - 0.001f);
        renderInv_draw(renderblocks, block, 0);
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

    private boolean render(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        iblockaccess.func_72805_g(blockX, blockY, blockZ);
        renderblocks.field_147840_d = ecru_BlockGrainHopper.tx_glass;
        block.func_149676_a(this.nc.P00, this.nc.P08, this.nc.P00, this.nc.P32, this.nc.P30, this.nc.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        renderblocks.func_147771_a();
        block.func_149676_a(this.nc.P00, this.nc.P30, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P00, this.nc.P06, this.nc.P00, this.nc.P32, this.nc.P08, this.nc.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P04, this.nc.P04, this.nc.P04, this.nc.P28, this.nc.P06, this.nc.P28);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P06, this.nc.P00, this.nc.P06, this.nc.P26, this.nc.P04, this.nc.P26);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        renderblocks.field_147840_d = ecru_BlockGrainHopper.tx_bottom;
        block.func_149676_a(this.nc.P00 + 0.001f, this.nc.P08, this.nc.P00 + 0.001f, this.nc.P32 - 0.001f, this.nc.P08 + 0.001f, this.nc.P32 - 0.001f);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        renderblocks.func_147771_a();
        return true;
    }
}
