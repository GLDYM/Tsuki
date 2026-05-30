package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockPlanter;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderPlanter implements ISimpleBlockRenderingHandler {
    private static final int count = 10;

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        Tessellator tessellator = Tessellator.field_78398_a;
        if (renderblocks.field_147844_c) {
            int j = block.func_149741_i(i);
            float f1 = ((j >> 16) & 255) / 255.0f;
            float f3 = ((j >> 8) & 255) / 255.0f;
            float f7 = (j & 255) / 255.0f;
            GL11.glColor4f(f1 * 1.0f, f3 * 1.0f, f7 * 1.0f, 1.0f);
        }
        block.func_149683_g();
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderblocks.func_147768_a(block, 0.0d, 0.0d, 0.0d, block.func_149691_a(0, i));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderblocks.func_147806_b(block, 0.0d, 0.0d, 0.0d, ecru_BlockPlanter.tx_top2);
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
        renderblocks.func_147771_a();
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderPlanterID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public boolean renderWorldBlock(IBlockAccess blockAccess, int par2, int par3, int par4, Block par1BlockPlanter, int renderType, RenderBlocks renderblocks) {
        renderblocks.func_147784_q(par1BlockPlanter, par2, par3, par4);
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78380_c(par1BlockPlanter.func_149677_c(blockAccess, par2, par3, par4));
        int l = par1BlockPlanter.func_149720_d(blockAccess, par2, par3, par4);
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
        IIcon icon = par1BlockPlanter.func_149733_h(2);
        renderblocks.func_147764_f(par1BlockPlanter, (par2 - 1.0d) + 0.125f, par3, par4, icon);
        renderblocks.func_147798_e(par1BlockPlanter, (par2 + 1.0d) - 0.125f, par3, par4, icon);
        renderblocks.func_147734_d(par1BlockPlanter, par2, par3, (par4 - 1.0d) + 0.125f, icon);
        renderblocks.func_147761_c(par1BlockPlanter, par2, par3, (par4 + 1.0d) - 0.125f, icon);
        IIcon icon1 = ecru_BlockPlanter.func_94375_b("planter_inner");
        renderblocks.func_147806_b(par1BlockPlanter, par2, (par3 - 1.0d) + 0.25d, par4, icon1);
        renderblocks.func_147768_a(par1BlockPlanter, par2, (par3 + 1.0d) - 0.75d, par4, icon1);
        blockAccess.func_72805_g(par2, par3, par4);
        int num = blockAccess.func_72805_g(par2, par3, par4);
        if (num > 0 && num < 16) {
            IIcon icon2 = ecru_BlockPlanter.tx_field[num];
            renderblocks.func_147806_b(par1BlockPlanter, par2, (par3 - 1.0d) + 0.9375d, par4, icon2);
            return true;
        }
        return true;
    }
}
