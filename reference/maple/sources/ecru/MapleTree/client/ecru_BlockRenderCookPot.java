package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockCookPot;
import ecru.MapleTree.block.ecru_BlockFallenLeavesFire;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderCookPot implements ISimpleBlockRenderingHandler {
    private static final ResourceLocation textures_cookPot = new ResourceLocation("mapletree", "textures/model/CookPot.png");
    ecru_numericConstant cn = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        renderCookPot2(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        return true;
    }

    private boolean renderCookPot2(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
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
        IIcon icon1 = ecru_BlockCookPot.tx_inner;
        renderblocks.func_147806_b(block, blockX, (blockY - 0.5d) + this.cn.P01, blockZ, icon1);
        iblockaccess.func_72805_g(blockX, blockY, blockZ);
        int wd = (iblockaccess.func_72805_g(blockX, blockY, blockZ) & 8) >> 3;
        IIcon icon2 = ecru_BlockCookPot.tx_water[wd];
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        double top = (meta & 3) * 0.09375d;
        if ((meta & 3) != 0) {
            renderblocks.func_147806_b(block, blockX, (blockY - 0.375d) + top, blockZ, icon2);
        }
        IIcon icon22 = ecru_BlockFallenLeavesFire.texId(3);
        renderblocks.field_147840_d = icon22;
        if ((meta & 4) == 0) {
            block.func_149676_a(this.cn.P01, this.cn.P16, this.cn.P16, this.cn.P02, this.cn.P31, this.cn.P17);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.cn.P01 - 0.015625f, this.cn.P14, this.cn.P14, this.cn.P03, this.cn.P17, this.cn.P19);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.cn.P30, this.cn.P16, this.cn.P16, this.cn.P31, this.cn.P31, this.cn.P17);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.cn.P29, this.cn.P14, this.cn.P14, this.cn.P31 + 0.015625f, this.cn.P17, this.cn.P19);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.cn.P01, this.cn.P31, this.cn.P16, this.cn.P31, this.cn.P32, this.cn.P17);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.cn.P14, this.cn.P30, this.cn.P15, this.cn.P19, this.cn.P32 + this.cn.P01, this.cn.P18);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.cn.P14, this.cn.P12, this.cn.P00, this.cn.P19, this.cn.P13, this.cn.P02);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.cn.P14, this.cn.P12, this.cn.P30, this.cn.P19, this.cn.P13, this.cn.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        } else {
            block.func_149676_a(this.cn.P16, this.cn.P16, this.cn.P01, this.cn.P17, this.cn.P31, this.cn.P02);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.cn.P14, this.cn.P14, this.cn.P01 - 0.015625f, this.cn.P19, this.cn.P17, this.cn.P03);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.cn.P16, this.cn.P16, this.cn.P30, this.cn.P17, this.cn.P31, this.cn.P31);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.cn.P14, this.cn.P14, this.cn.P29, this.cn.P19, this.cn.P17, this.cn.P31 + 0.015625f);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.cn.P16, this.cn.P31, this.cn.P01, this.cn.P17, this.cn.P32, this.cn.P31);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.cn.P15, this.cn.P30, this.cn.P14, this.cn.P18, this.cn.P32 + this.cn.P01, this.cn.P19);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.cn.P00, this.cn.P12, this.cn.P14, this.cn.P02, this.cn.P13, this.cn.P19);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.cn.P30, this.cn.P12, this.cn.P14, this.cn.P32, this.cn.P13, this.cn.P19);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
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
        renderblocks.func_147806_b(block, 0.0d, 0.0d, 0.0d, ecru_BlockCookPot.tx_top2);
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
        renderblocks.field_147840_d = ecru_BlockFallenLeavesFire.texId(3);
        renderblocks.func_147782_a(this.cn.P14, this.cn.P12, this.cn.P00, this.cn.P19, this.cn.P13, this.cn.P02);
        renderInv_draw(renderblocks, block, 0);
        renderblocks.func_147782_a(this.cn.P14, this.cn.P12, this.cn.P30, this.cn.P19, this.cn.P13, this.cn.P32);
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

    public int getRenderId() {
        return mod_ecru_MapleTree.renderCookPotID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}
