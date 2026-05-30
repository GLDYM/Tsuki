package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockOreBlobk;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderOreBlock implements ISimpleBlockRenderingHandler {
    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        if (renderType == mod_ecru_MapleTree.renderOreBlockID) {
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            renderOre(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
            return true;
        }
        return true;
    }

    private boolean renderOre(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        Block idXp = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ);
        Block idXm = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ);
        Block idZp = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1);
        Block idZm = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1);
        Block idYp = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
        Block idYm = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ);
        boolean nrXp = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ).func_149721_r();
        boolean nrXm = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ).func_149721_r();
        boolean nrZp = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1).func_149721_r();
        boolean nrZm = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1).func_149721_r();
        boolean nrYp = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ).func_149721_r();
        boolean nrYm = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ).func_149721_r();
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        Tessellator tessellator = Tessellator.field_78398_a;
        IIcon iIcon = ecru_BlockOreBlobk.tx_stone;
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        if (renderblocks.field_147840_d != null) {
            IIcon iIcon2 = renderblocks.field_147840_d;
        }
        int _meta = meta & 7;
        int _meta2 = (_meta < 0 || _meta > 2) ? 1 : _meta;
        if (idXp == Blocks.field_150350_a || !nrXp) {
            renderblocks.func_147764_f(block, blockX, blockY, blockZ, ecru_BlockOreBlobk.tx_ore[_meta2]);
        }
        if (idXm == Blocks.field_150350_a || !nrXm) {
            renderblocks.func_147798_e(block, blockX, blockY, blockZ, ecru_BlockOreBlobk.tx_ore[_meta2]);
        }
        if (idZp == Blocks.field_150350_a || !nrZp) {
            renderblocks.func_147734_d(block, blockX, blockY, blockZ, ecru_BlockOreBlobk.tx_ore[_meta2]);
        }
        if (idZm == Blocks.field_150350_a || !nrZm) {
            renderblocks.func_147761_c(block, blockX, blockY, blockZ, ecru_BlockOreBlobk.tx_ore[_meta2]);
        }
        if (idYp == Blocks.field_150350_a || !nrYp) {
            renderblocks.func_147806_b(block, blockX, blockY, blockZ, ecru_BlockOreBlobk.tx_ore[_meta2]);
        }
        if (idYm == Blocks.field_150350_a || !nrYm) {
            renderblocks.func_147768_a(block, blockX, blockY, blockZ, ecru_BlockOreBlobk.tx_ore[_meta2]);
            return true;
        }
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        Tessellator tessellator = Tessellator.field_78398_a;
        int meta = i & 7;
        int meta2 = (meta < 0 || meta > 2) ? 1 : meta;
        block.func_149683_g();
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderblocks.func_147768_a(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreBlobk.tx_stone);
        renderblocks.func_147768_a(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreBlobk.tx_ore[meta2]);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderblocks.func_147806_b(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreBlobk.tx_stone);
        renderblocks.func_147806_b(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreBlobk.tx_ore[meta2]);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderblocks.func_147761_c(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreBlobk.tx_stone);
        renderblocks.func_147761_c(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreBlobk.tx_ore[meta2]);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderblocks.func_147734_d(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreBlobk.tx_stone);
        renderblocks.func_147734_d(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreBlobk.tx_ore[meta2]);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderblocks.func_147798_e(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreBlobk.tx_stone);
        renderblocks.func_147798_e(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreBlobk.tx_ore[meta2]);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderblocks.func_147764_f(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreBlobk.tx_stone);
        renderblocks.func_147764_f(block, 0.0d, 0.0d, 0.0d, ecru_BlockOreBlobk.tx_ore[meta2]);
        tessellator.func_78381_a();
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
        renderblocks.func_147771_a();
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderOreBlockID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}
