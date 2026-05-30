package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockOreFlower;
import ecru.MapleTree.block.ecru_BlockOreFlowerGold;
import ecru.MapleTree.block.ecru_BlockOreFlowerIron;
import ecru.MapleTree.block.ecru_BlockOreFlowerMarble;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderOreFlower implements ISimpleBlockRenderingHandler {
    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        iblockaccess.func_72805_g(blockX, blockY, blockZ);
        Block id = iblockaccess.func_147439_a(blockX, blockY, blockZ);
        if (id == mod_ecru_MapleTree.blockOreFlowerGold) {
            renderFlowerBox2(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        } else {
            renderFlowerBox1(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        }
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147782_a(0.0d, 0.0d, 0.5d, 1.0d, 1.0d, 0.5d);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(0.0d, 0.0d, 0.49d, 1.0d, 1.0d, 0.51d);
        if (block == mod_ecru_MapleTree.blockOreFlowerMarble) {
            renderInv_draw1(renderblocks, block, i);
        } else if (block == mod_ecru_MapleTree.blockOreFlowerRed) {
            renderInv_draw2(renderblocks, block, i);
        } else if (block == mod_ecru_MapleTree.blockOreFlowerIron) {
            renderInv_draw3(renderblocks, block, i);
        } else {
            renderInv_draw4(renderblocks, block, i);
        }
        renderblocks.func_147771_a();
    }

    private void renderInv_draw(RenderBlocks renderblocks, Block block, int i) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderblocks.func_147761_c(block, 0.0d, 0.0d, 0.0d, block.func_149691_a(2, i));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderblocks.func_147734_d(block, 0.0d, 0.0d, 0.0d, block.func_149691_a(3, i));
        tessellator.func_78381_a();
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
    }

    private void renderInv_draw1(RenderBlocks renderblocks, Block block, int i) {
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderblocks.func_147761_c(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreFlowerMarble.tx_flower[i]);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderblocks.func_147734_d(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreFlowerMarble.tx_flower[i]);
        tessellator.func_78381_a();
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
    }

    private void renderInv_draw2(RenderBlocks renderblocks, Block block, int i) {
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderblocks.func_147761_c(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreFlower.tx_flower[i]);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderblocks.func_147734_d(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreFlower.tx_flower[i]);
        tessellator.func_78381_a();
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
    }

    private void renderInv_draw3(RenderBlocks renderblocks, Block block, int i) {
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderblocks.func_147761_c(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreFlowerIron.tx_flower[i]);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderblocks.func_147734_d(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreFlowerIron.tx_flower[i]);
        tessellator.func_78381_a();
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
    }

    private void renderInv_draw4(RenderBlocks renderblocks, Block block, int i) {
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderblocks.func_147761_c(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreFlowerGold.tx_flower[i]);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderblocks.func_147734_d(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreFlowerGold.tx_flower[i]);
        tessellator.func_78381_a();
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderOreFlowerID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    private void renderFlowerBox1(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78380_c(block.func_149677_c(iblockaccess, blockX, blockY, blockZ));
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        int l = block.func_149720_d(iblockaccess, blockX, blockY, blockZ);
        float f1 = ((l >> 16) & 255) / 255.0f;
        float f2 = ((l >> 8) & 255) / 255.0f;
        float f3 = (l & 255) / 255.0f;
        if (EntityRenderer.field_78517_a) {
            float f4 = (((f1 * 30.0f) + (f2 * 59.0f)) + (f3 * 11.0f)) / 100.0f;
            float f5 = ((f1 * 30.0f) + (f2 * 70.0f)) / 100.0f;
            float f6 = ((f1 * 30.0f) + (f3 * 70.0f)) / 100.0f;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }
        tessellator.func_78386_a(1.0f * f1, 1.0f * f2, 1.0f * f3);
        double d0 = blockX;
        double d1 = blockY;
        double d2 = blockZ;
        renderblocks.func_147765_a(block.func_149691_a(0, iblockaccess.func_72805_g(blockX, blockY, blockZ)), d0, d1, d2, 1.0f);
        drawCrossedSquares1_(block, iblockaccess.func_72805_g(blockX, blockY, blockZ), d0, d1, d2, 1.0f, iblockaccess, renderblocks);
    }

    private void renderFlowerBox2(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78380_c(block.func_149677_c(iblockaccess, blockX, blockY, blockZ));
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        int l = block.func_149720_d(iblockaccess, blockX, blockY, blockZ);
        float f1 = ((l >> 16) & 255) / 255.0f;
        float f2 = ((l >> 8) & 255) / 255.0f;
        float f3 = (l & 255) / 255.0f;
        if (EntityRenderer.field_78517_a) {
            float f4 = (((f1 * 30.0f) + (f2 * 59.0f)) + (f3 * 11.0f)) / 100.0f;
            float f5 = ((f1 * 30.0f) + (f2 * 70.0f)) / 100.0f;
            float f6 = ((f1 * 30.0f) + (f3 * 70.0f)) / 100.0f;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }
        tessellator.func_78386_a(1.0f * f1, 1.0f * f2, 1.0f * f3);
        double d0 = blockX;
        double d1 = blockY;
        double d2 = blockZ;
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        IIcon icon = ecru_BlockOreFlowerGold.tx_flower[meta & 7];
        renderblocks.field_147840_d = icon;
        renderblocks.func_147765_a(icon, d0, d1, d2, 1.0f);
        renderblocks.func_147771_a();
        drawCrossedSquares2_(block, iblockaccess.func_72805_g(blockX, blockY, blockZ), d0, d1, d2, 1.0f, iblockaccess, renderblocks);
    }

    public void drawCrossedSquares1_(Block par1Block, int par2, double par3, double par5, double par7, float par9, IBlockAccess iblockaccess, RenderBlocks renderblocks) {
        IIcon icon;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78380_c(par1Block.func_149677_c(iblockaccess, (int) par3, (int) par5, (int) par7));
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        int meta = iblockaccess.func_72805_g((int) par3, (int) par5, (int) par7);
        Block id = iblockaccess.func_147439_a((int) par3, (int) par5, (int) par7);
        if (id == mod_ecru_MapleTree.blockOreFlowerMarble) {
            icon = ecru_BlockOreFlowerMarble.tx_flower[meta & 7];
        } else if (id == mod_ecru_MapleTree.blockOreFlowerRed) {
            icon = ecru_BlockOreFlower.tx_flower[meta & 7];
        } else if (id == mod_ecru_MapleTree.blockOreFlowerIron) {
            icon = ecru_BlockOreFlowerIron.tx_flower[meta & 7];
        } else {
            icon = ecru_BlockOreFlowerGold.tx_flower[meta & 7];
        }
        if (renderblocks.func_147744_b()) {
            icon = renderblocks.field_147840_d;
        }
        double d3 = icon.func_94209_e();
        double d4 = icon.func_94206_g();
        double d5 = icon.func_94212_f();
        double d6 = icon.func_94210_h();
        double d = 0.45d * par9;
        double d2 = par3 + 1.0d;
        double d7 = par7 + 1.0d;
        double z1min = par7 + 0.5d;
        double x1max = par3 + 1.0d;
        double y1max = par5 + 1.0d;
        double z1max = par7 + 0.5d;
        double x2min = par3 + 0.5d;
        double x2max = par3 + 0.5d;
        double y2max = par5 + 1.0d;
        double z2max = par7 + 1.0d;
        tessellator.func_78374_a(par3, y1max, z1min, d3, d4);
        tessellator.func_78374_a(par3, par5, z1min, d3, d6);
        tessellator.func_78374_a(x1max, par5, z1max, d5, d6);
        tessellator.func_78374_a(x1max, y1max, z1max, d5, d4);
        tessellator.func_78374_a(x1max, y1max, z1max, d3, d4);
        tessellator.func_78374_a(x1max, par5, z1max, d3, d6);
        tessellator.func_78374_a(par3, par5, z1min, d5, d6);
        tessellator.func_78374_a(par3, y1max, z1min, d5, d4);
        tessellator.func_78374_a(x2min, y2max, par7, d3, d4);
        tessellator.func_78374_a(x2min, par5, par7, d3, d6);
        tessellator.func_78374_a(x2max, par5, z2max, d5, d6);
        tessellator.func_78374_a(x2max, y2max, z2max, d5, d4);
        tessellator.func_78374_a(x2max, y2max, z2max, d3, d4);
        tessellator.func_78374_a(x2max, par5, z2max, d3, d6);
        tessellator.func_78374_a(x2min, par5, par7, d5, d6);
        tessellator.func_78374_a(x2min, y2max, par7, d5, d4);
    }

    public void drawCrossedSquares2_(Block par1Block, int par2, double par3, double par5, double par7, float par9, IBlockAccess iblockaccess, RenderBlocks renderblocks) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78380_c(par1Block.func_149677_c(iblockaccess, (int) par3, (int) par5, (int) par7));
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        int meta = iblockaccess.func_72805_g((int) par3, (int) par5, (int) par7);
        IIcon icon = ecru_BlockOreFlower.tx_num[meta & 7];
        double d3 = icon.func_94209_e();
        double d4 = icon.func_94206_g();
        double d5 = icon.func_94212_f();
        double d6 = icon.func_94210_h();
        double d = 0.45d * par9;
        double d2 = par3 + 1.0d;
        double d7 = par7 + 1.0d;
        double z1min = par7 + 0.5d;
        double x1max = par3 + 1.0d;
        double y1max = par5 + 1.0d;
        double z1max = par7 + 0.5d;
        double x2min = par3 + 0.5d;
        double x2max = par3 + 0.5d;
        double y2max = par5 + 1.0d;
        double z2max = par7 + 1.0d;
        tessellator.func_78374_a(par3, y1max, z1min, d3, d4);
        tessellator.func_78374_a(par3, par5, z1min, d3, d6);
        tessellator.func_78374_a(x1max, par5, z1max, d5, d6);
        tessellator.func_78374_a(x1max, y1max, z1max, d5, d4);
        tessellator.func_78374_a(x1max, y1max, z1max, d3, d4);
        tessellator.func_78374_a(x1max, par5, z1max, d3, d6);
        tessellator.func_78374_a(par3, par5, z1min, d5, d6);
        tessellator.func_78374_a(par3, y1max, z1min, d5, d4);
        tessellator.func_78374_a(x2min, y2max, par7, d3, d4);
        tessellator.func_78374_a(x2min, par5, par7, d3, d6);
        tessellator.func_78374_a(x2max, par5, z2max, d5, d6);
        tessellator.func_78374_a(x2max, y2max, z2max, d5, d4);
        tessellator.func_78374_a(x2max, y2max, z2max, d3, d4);
        tessellator.func_78374_a(x2max, par5, z2max, d3, d6);
        tessellator.func_78374_a(x2min, par5, par7, d5, d6);
        tessellator.func_78374_a(x2min, y2max, par7, d5, d4);
    }
}
