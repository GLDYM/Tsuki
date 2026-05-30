package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockFallenLeavesFire;
import ecru.MapleTree.block.ecru_BlockMortar;
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
public class ecru_BlockRenderMortar implements ISimpleBlockRenderingHandler {
    ecru_numericConstant cn = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        renderMortar(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        return true;
    }

    private boolean renderMortar(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78380_c(block.func_149677_c(iblockaccess, blockX, blockY, blockZ));
        int l = block.func_149720_d(iblockaccess, blockX, blockY, blockZ);
        float f1 = ((l >> 16) & 255) / 255.0f;
        float f2 = ((l >> 8) & 255) / 255.0f;
        float f3 = (l & 255) / 255.0f;
        if (EntityRenderer.field_78517_a) {
            float f5 = (((f1 * 30.0f) + (f2 * 59.0f)) + (f3 * 11.0f)) / 100.0f;
            float f4 = ((f1 * 30.0f) + (f2 * 70.0f)) / 100.0f;
            float f6 = ((f1 * 30.0f) + (f3 * 70.0f)) / 100.0f;
            f1 = f5;
            f2 = f4;
            f3 = f6;
        }
        tessellator.func_78386_a(1.0f * f1, 1.0f * f2, 1.0f * f3);
        double OFFSET = this.cn.P02;
        renderblocks.func_147768_a(block, blockX, blockY, blockZ, block.func_149691_a(0, 0));
        renderblocks.func_147806_b(block, blockX, blockY, blockZ, block.func_149691_a(1, 0));
        renderblocks.func_147761_c(block, blockX, blockY, blockZ + OFFSET, block.func_149691_a(2, 0));
        renderblocks.func_147734_d(block, blockX, blockY, blockZ - OFFSET, block.func_149691_a(3, 0));
        renderblocks.func_147798_e(block, blockX + OFFSET, blockY, blockZ, block.func_149691_a(4, 0));
        renderblocks.func_147764_f(block, blockX - OFFSET, blockY, blockZ, block.func_149691_a(5, 0));
        IIcon icon = block.func_149733_h(2);
        renderblocks.func_147764_f(block, (blockX - 1.0d) + 0.0625d + OFFSET, blockY, blockZ, icon);
        renderblocks.func_147798_e(block, ((blockX + 1.0d) - 0.0625d) - OFFSET, blockY, blockZ, icon);
        renderblocks.func_147734_d(block, blockX, blockY, (blockZ - 1.0d) + 0.0625d + OFFSET, icon);
        renderblocks.func_147761_c(block, blockX, blockY, ((blockZ + 1.0d) - 0.0625d) - OFFSET, icon);
        IIcon icon1 = ecru_BlockMortar.tx_inner;
        renderblocks.func_147806_b(block, blockX, (blockY - 0.5d) + this.cn.P01, blockZ, icon1);
        iblockaccess.func_72805_g(blockX, blockY, blockZ);
        IIcon icon2 = ecru_BlockFallenLeavesFire.texId(3);
        renderblocks.field_147840_d = icon2;
        renderblocks.func_147771_a();
        IIcon icon_inner = ecru_BlockMortar.tx_inner;
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78380_c(block.func_149677_c(iblockaccess, blockX, blockY, blockZ));
        tess.func_78386_a(1.0f, 1.0f, 1.0f);
        double uMin = icon_inner.func_94214_a(2.0d);
        double uMax = icon_inner.func_94214_a(14.0d);
        double vMin = icon_inner.func_94207_b(2.0d);
        double vMax = icon_inner.func_94207_b(8.0d);
        tess.func_78374_a(blockX + this.cn.P04, blockY + this.cn.P01, blockZ + this.cn.P16, uMin, vMax);
        tess.func_78374_a(blockX + this.cn.P28, blockY + this.cn.P01, blockZ + this.cn.P16, uMax, vMax);
        tess.func_78374_a(blockX + this.cn.P28, blockY + this.cn.P13, blockZ + this.cn.P04, uMax, vMin);
        tess.func_78374_a(blockX + this.cn.P04, blockY + this.cn.P13, blockZ + this.cn.P04, uMin, vMin);
        double uMin2 = icon_inner.func_94214_a(2.0d);
        double uMax2 = icon_inner.func_94214_a(14.0d);
        double vMin2 = icon_inner.func_94207_b(8.0d);
        double vMax2 = icon_inner.func_94207_b(14.0d);
        tess.func_78374_a(blockX + this.cn.P04, blockY + this.cn.P13, blockZ + this.cn.P28, uMin2, vMax2);
        tess.func_78374_a(blockX + this.cn.P28, blockY + this.cn.P13, blockZ + this.cn.P28, uMax2, vMax2);
        tess.func_78374_a(blockX + this.cn.P28, blockY + this.cn.P01, blockZ + this.cn.P16, uMax2, vMin2);
        tess.func_78374_a(blockX + this.cn.P04, blockY + this.cn.P01, blockZ + this.cn.P16, uMin2, vMin2);
        double uMin3 = icon_inner.func_94214_a(2.0d);
        double uMax3 = icon_inner.func_94214_a(8.0d);
        double vMin3 = icon_inner.func_94207_b(2.0d);
        double vMax3 = icon_inner.func_94207_b(14.0d);
        tess.func_78374_a(blockX + this.cn.P04, blockY + this.cn.P13, blockZ + this.cn.P28, uMin3, vMax3);
        tess.func_78374_a(blockX + this.cn.P16, blockY + this.cn.P01, blockZ + this.cn.P28, uMax3, vMax3);
        tess.func_78374_a(blockX + this.cn.P16, blockY + this.cn.P01, blockZ + this.cn.P04, uMax3, vMin3);
        tess.func_78374_a(blockX + this.cn.P04, blockY + this.cn.P13, blockZ + this.cn.P04, uMin3, vMin3);
        double uMin4 = icon_inner.func_94214_a(8.0d);
        double uMax4 = icon_inner.func_94214_a(14.0d);
        double vMin4 = icon_inner.func_94207_b(2.0d);
        double vMax4 = icon_inner.func_94207_b(14.0d);
        tess.func_78374_a(blockX + this.cn.P16, blockY + this.cn.P01, blockZ + this.cn.P28, uMin4, vMax4);
        tess.func_78374_a(blockX + this.cn.P28, blockY + this.cn.P13, blockZ + this.cn.P28, uMax4, vMax4);
        tess.func_78374_a(blockX + this.cn.P28, blockY + this.cn.P13, blockZ + this.cn.P04, uMax4, vMin4);
        tess.func_78374_a(blockX + this.cn.P16, blockY + this.cn.P01, blockZ + this.cn.P04, uMin4, vMin4);
        renderblocks.func_147771_a();
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        Tessellator tessellator = Tessellator.field_78398_a;
        if (renderblocks.field_147844_c) {
            int j = block.func_149741_i(i);
            float f1 = ((j >> 16) & 255) / 255.0f;
            float f3 = ((j >> 8) & 255) / 255.0f;
            float f7 = (j & 255) / 255.0f;
            GL11.glColor4f(f1 * 1.0f, f3 * 1.0f, f7 * 1.0f, 1.0f);
        }
        float OFFSET = this.cn.P02;
        block.func_149683_g();
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderblocks.func_147768_a(block, 0.0d, 0.0d, 0.0d, block.func_149691_a(0, i));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderblocks.func_147806_b(block, 0.0d, 0.0d, 0.0d, ecru_BlockMortar.tx_top2);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderblocks.func_147761_c(block, 0.0d, 0.0d, 0.0d + OFFSET, block.func_149691_a(2, i));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderblocks.func_147734_d(block, 0.0d, 0.0d, 0.0d - OFFSET, block.func_149691_a(3, i));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderblocks.func_147798_e(block, 0.0d + OFFSET, 0.0d, 0.0d, block.func_149691_a(4, i));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderblocks.func_147764_f(block, 0.0d - OFFSET, 0.0d, 0.0d, block.func_149691_a(5, i));
        tessellator.func_78381_a();
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
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
        return mod_ecru_MapleTree.renderMortarID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}
